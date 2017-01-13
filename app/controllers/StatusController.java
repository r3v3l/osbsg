package controllers;

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
 * Created by adrian on 22.12.16.
 */
@Security.Authenticated(SecuredController.class)
public class StatusController extends Controller {

    public static class Status {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String name;

    }

    private UserModel userModel = new UserModel();
    private StatusModel statusModel = new StatusModel();

    public Result getAll(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        StatusModel statusModel = new StatusModel();
        return ok(Json.toJson(statusModel.findAll()));
    }

    public Result getStatus(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        StatusModel currentStatus = statusModel.findByName(name);
        if(currentStatus == null){
            return ApplicationController.buildResponse("warning", "Status " +name+ " not found.", "bad");
        }
        ActionLogController.save(Json.toJson(currentStatus).toString());
        return ok(Json.toJson(currentStatus));
    }

    public Result create(){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        Form<Status> statusForm = Form.form(Status.class).bindFromRequest();
        if(statusForm.hasErrors()){
            ActionLogController.save(statusForm.errorsAsJson().toString());
            return badRequest(statusForm.errorsAsJson());
        }
        Status status = statusForm.get();
        if(statusModel.findByName(status.name) != null){
            return ApplicationController.buildResponse("warning", "Status exists. Please try again.", "bad");
        }
        StatusModel newStatus = new StatusModel();
        newStatus.name = status.name.toLowerCase();
        newStatus.updatedDate = new Date();
        newStatus.creationDate = new Date();
        newStatus.save();
        if(statusModel.findByName(status.name) == null){
            return ApplicationController.buildResponse(
                    "danger", "Status has not been created. Please try again.", "good"
            );
        }
        return ApplicationController.buildResponse("success", "Status has been created.", "good");
    }

    public Result update(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        StatusModel currentStatus = statusModel.findByName(name);
        if(currentStatus != null){
            return ApplicationController.buildResponse("warning", "Stauts" +name+ " not exists.", "bad");
        }
        Form<Status> statusForm = Form.form(Status.class).bindFromRequest();
        if(statusForm.hasErrors()){
            ActionLogController.save(statusForm.errorsAsJson().toString());
            return badRequest(statusForm.errorsAsJson());
        }
        Status status = statusForm.get();
        if(status.name.equals(name)){
            return ApplicationController.buildResponse("warning", "Old and new status name are identical.", "bad");
        }
        if(statusModel.findByName(status.name) != null){
            return ApplicationController.buildResponse(
                    "warning", "Status " +status.name+ " exists. Please try again.", "bad"
            );
        }
        currentStatus.name = status.name;
        currentStatus.updatedDate = new Date();
        currentStatus.update();
        if(statusModel.findByName(status.name) == null){
            return ApplicationController.buildResponse(
                    "danger", "Status has not been created. Please try again.", "bad"
            );
        }
        return ApplicationController.buildResponse("success", "Status " +status.name+ "has been updated.", "good");
    }

    public Result delete(String name){
        if(!isAdmin()){
            return ApplicationController.buildResponse("danger", "Invalid user role. Please try again.", "bad");
        }
        StatusModel currentStatus = statusModel.findByName(name);
        if(currentStatus == null){
            return ApplicationController.buildResponse(
                    "warning", "Status " +name+ " not exists. Please try again.", "bad"
            );
        }
        currentStatus.delete();
        if(statusModel.findByName(name) != null){
            return ApplicationController.buildResponse(
                    "danger", "Status " +name+ " has not been deleted. Please try again.", "bad"
            );
        }
        return ApplicationController.buildResponse("success", "Status " +name+ " has been deleted.", "good");
    }

    public boolean isAdmin(){
        RolesService rolesService = new RolesService();
        return rolesService.isAdmin(userModel.findByEmail(session().get("username")));
    }

}
