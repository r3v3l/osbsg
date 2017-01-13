package controllers;

import models.RoleModel;
import models.StatusModel;
import models.UserModel;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.RolesService;

import java.util.Date;

/**
 * Created by adrian on 02.01.17.
 */
@Security.Authenticated(SecuredController.class)
public class RoleController extends Controller {

    private UserModel userModel = new UserModel();
    private RoleModel roleModel = new RoleModel();
    private StatusModel statusModel = new StatusModel();

    public static class Role {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String roleName;

        public String active;
        public String inactive;

    }

    public Result getAll(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        return ok(Json.toJson(roleModel.findAll()));
    }

    public Result getRole(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        if(roleModel.findByName(name) == null){
            return ApplicationController.buildResponse("warning", "Invalid role name. Please try again.", "bad");
        }
        return ok(Json.toJson(roleModel.findByName(name)));
    }

    public Result createRole(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        Form<Role> roleForm = Form.form(Role.class).bindFromRequest();
        if(roleForm.hasErrors()){
            ActionLogController.save(roleForm.errorsAsJson().toString());
        }
        Role role = roleForm.get();
        if(roleModel.findByName(role.roleName) != null){
            return ApplicationController.buildResponse(
                    "warning", "Role " +role.roleName+ " exists. Please try again.", "bad"
            );
        }
        RoleModel newRole = new RoleModel();
        newRole.name = role.roleName;
        if(role.active.equals("on")){
            newRole.statuses.add(statusModel.findByName("active"));
        } else if(role.inactive.equals("on")){
            newRole.statuses.add(statusModel.findByName("inactive"));
        }else {
            return ApplicationController.buildResponse("warning", "Role must have at least one status.", "bad");
        }
        newRole.creationDate = new Date();
        newRole.updatedDate = new Date();
        newRole.save();
        if(roleModel.findByName(role.roleName) == null){
            return ApplicationController.buildResponse(
                    "danger", "Role " +role.roleName+ " has not been created. Please try again.", "bad"
            );
        }
        return ApplicationController.buildResponse("success", "Role " +role.roleName+ " has been created.", "good");
    }

    public Result updateRole(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        RoleModel currentRole = roleModel.findByName(name);
        if(currentRole == null){
            return ApplicationController.buildResponse("danger", "Role " +name+ " not found. Please try again.", "bad");
        }
        Form<Role> roleForm = Form.form(Role.class).bindFromRequest();
        if(roleForm.hasErrors()){
            ActionLogController.save(roleForm.errorsAsJson().toString());
            return badRequest(roleForm.errorsAsJson());
        }
        Role role = roleForm.get();
        if(roleModel.findByName(name) != null){
            return ApplicationController.buildResponse(
                    "warning", "Role " +role.roleName+ " exists. Please try again.", "bad"
            );
        }
        currentRole.name = role.roleName;
        currentRole.statuses.clear();
        if(role.active.equals("on")){
            currentRole.statuses.add(statusModel.findByName("active"));
        }
        if(role.inactive.equals("on")){
            currentRole.statuses.add(statusModel.findByName("inactive"));
        }
        currentRole.updatedDate = new Date();
        currentRole.update();
        if(roleModel.findByName(role.roleName) == null){
            return ApplicationController.buildResponse(
                    "danger", "Role " +name+ " has not been updated. Please try again.", "bad"
            );
        }
        return ApplicationController.buildResponse("success", "Role " +role.roleName+ " has been updated.", "good");
    }

    public Result deleteRole(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        RoleModel role = roleModel.findByName(name);
        if(role == null){
            return ApplicationController.buildResponse("warning", "Role " +name+ " not found. Please try again.", "bad");
        }
        role.delete();
        if(roleModel.findByName(name) != null){
            return ApplicationController.buildResponse("danger", "Role " +name+ " has not been deleted.", "bad");
        }
        return ApplicationController.buildResponse("success", "Role " +name+ " has been deleted.", "good");
    }

    public boolean isAdmin(){
        RolesService rolesService = new RolesService();
        return rolesService.isAdmin(userModel.findByEmail(session().get("username")));
    }

}
