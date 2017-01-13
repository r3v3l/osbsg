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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 02.01.17.
 */
@Security.Authenticated(SecuredController.class)
public class UserAdminController extends Controller {

    public static class User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String password;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String confirmPassword;

        public String active;
        public String inactive;
        public String frozen;
        public String banned;

        public String guest;
        public String appUser;
        public String customer;
        public String advertiser;
        public String author;
        public String editor;
        public String moderator;
        public String accountManager;
        public String administrator;

    }

    private UserModel userModel = new UserModel();
    private StatusModel statusModel = new StatusModel();
    private RoleModel roleModel = new RoleModel();

    public Result getAll(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        ActionLogController.save(Json.toJson(userModel.findAll()).toString());
        return ok(Json.toJson(userModel.findAll()));
    }

    public Result getPage(int page, int size){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        ActionLogController.save(Json.toJson(userModel.findPage(page, size)).toString());
        return ok(Json.toJson(userModel.findPage(page, size)));
    }

    public Result getRowCount(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        ActionLogController.save(
                ApplicationController.buildJsonResponse("size", Integer.toString(userModel.rowCount())).toString()
        );
        return ok(ApplicationController.buildJsonResponse("size", Integer.toString(userModel.rowCount())));
    }

    public Result getUser(String email){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        UserModel currentUser = userModel.findByEmail(email);
        if(currentUser == null){
            return ApplicationController.buildResponse("warning", "User " +email+ " not found.", "good");
        }
        ActionLogController.save(Json.toJson(currentUser).toString());
        return ok(Json.toJson(currentUser));

    }

    public Result createUser(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        if(userForm.hasErrors()){
            ActionLogController.save(userForm.errorsAsJson().toString());
            return badRequest(userForm.errorsAsJson());
        }
        User user = userForm.get();
        if(userModel.findByEmail(user.email) != null){
            return ApplicationController.buildResponse(
                    "warning", "User " +user.email+ " exists. Please try again.", "good"
            );
        }
        if(!user.password.equals(user.confirmPassword)){
            return ApplicationController.buildResponse("warning", "Passwords mismatch. Please try again.", "good");
        }
        UserModel newUser = new UserModel();
        newUser.setEmail(user.email);
        newUser.setPassword(user.password);
        newUser.statuses = getStatuses(user);
        newUser.roles = getRoles(user);
        newUser.creationDate = new Date();
        newUser.updatedDate = new Date();
        newUser.save();
        if(userModel.findByEmailAndPassword(user.email, user.password) == null){
            return ApplicationController.buildResponse("danger", "User has not been created. Please try again.", "good");
        }
        return ApplicationController.buildResponse("success", "User has been created.", "good");
    }

    public Result updateUser(String email){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        UserModel currentUser = userModel.findByEmail(email);
        if(currentUser == null){
            return ApplicationController.buildResponse("danger", "User not exists. Please try again.", "good");
        }
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        if(userForm.hasErrors()){
            ActionLogController.save(userForm.errorsAsJson().toString());
            return badRequest(userForm.errorsAsJson());
        }
        User user = userForm.get();
        if((userModel.findByEmail(user.email) != null) && (!currentUser.email.equals(email))){
            return ApplicationController.buildResponse(
                    "warning", "User " +user.email+ " exists. Please try again.", "good"
            );
        }
        if(!user.password.equals(user.confirmPassword)){
            return ApplicationController.buildResponse("warning", "Passwords mismatch. Please try again.", "good");
        }
        currentUser.setEmail(user.email);
        currentUser.setPassword(user.password);
        currentUser.statuses.clear();
        currentUser.statuses = getStatuses(user);
        currentUser.roles.clear();
        currentUser.roles = getRoles(user);
        currentUser.updatedDate = new Date();
        currentUser.update();
        if(userModel.findByEmailAndPassword(user.email, user.password) == null){
            return ApplicationController.buildResponse("danger", "User has not been created. Please try again.", "good");
        }
        return ApplicationController.buildResponse("success", "User has been created.", "good");
    }

    public Result deleteUser(String email){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "good");
        }
        UserModel currentUser = userModel.findByEmail(email);
        if(currentUser == null){
            return ApplicationController.buildResponse("warning", "User not exists. Please try again.", "good");
        }
        currentUser.delete();
        if(userModel.findByEmail(email) != null){
            return ApplicationController.buildResponse("danger", "User has not been deleted. Please try again.", "good");
        }
        return ApplicationController.buildResponse("success", "User has been deleted. Please try again.", "good");
    }

    public List<StatusModel> getStatuses(User user){
        List<StatusModel> statuses = new ArrayList<>();
        if(user.active.equals("on")){
            statuses.add(statusModel.findByName("active"));
        }
        if(user.inactive.equals("on")){
            statuses.add(statusModel.findByName("inactive"));
        }
        if(user.frozen.equals("on")){
            statuses.add(statusModel.findByName("frozen"));
        }
        if(user.banned.equals("on")){
            statuses.add(statusModel.findByName("banned"));
        }
        return statuses;
    }

    public List<RoleModel> getRoles(User user){
        List<RoleModel> roles = new ArrayList<>();
        if(user.guest.equals("on")){
            roles.add(roleModel.findByName("guest"));
        }
        if(user.appUser.equals("on")){
            roles.add(roleModel.findByName("user"));
        }
        if(user.advertiser.equals("on")){
            roles.add(roleModel.findByName("advertiser"));
        }
        if(user.customer.equals("on")){
            roles.add(roleModel.findByName("customer"));
        }
        if(user.author.equals("on")){
            roles.add(roleModel.findByName("author"));
        }
        if(user.editor.equals("on")){
            roles.add(roleModel.findByName("editor"));
        }
        if(user.moderator.equals("on")){
            roles.add(roleModel.findByName("moderator"));
        }
        if(user.accountManager.equals("on")){
            roles.add(roleModel.findByName("accountManager"));
        }
        if(user.administrator.equals("on")){
            roles.add(roleModel.findByName("administrator"));
        }
        return roles;
    }

    public boolean isAdmin(){
        RolesService rolesService = new RolesService();
        return rolesService.isAdmin(userModel.findByEmail(session().get("username")));
    }

}
