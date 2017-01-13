package controllers;

import models.UserModel;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;

import java.util.Date;

/**
 * Created by adrian on 12.12.16.
 */
@Security.Authenticated(SecuredController.class)
public class UserController extends Controller {

    public static class Email {

        @Constraints.Required
        @Constraints.Email
        public String email;

    }

    public static class Password {

        @Constraints.Required
        public String oldPassword;

        @Constraints.Required
        @Constraints.MinLength(12)
        public String password;

        @Constraints.Required
        public String confirmPassword;

    }

    private UserModel userModel;

    public Result getUser(){
        UserModel currentUser = userModel.findByEmail(session().get("username"));
        if(currentUser == null){
            ActionLogController.save(ApplicationController.buildJsonResponse("error", "User not found.").toString());
            return badRequest(ApplicationController.buildJsonResponse("error", "User not found."));
        }
        ActionLogController.save(Json.toJson(currentUser).toString());
        return ok(Json.toJson(currentUser));
    }

    public Result changeEmail(){
        UserModel user = userModel.findByEmail(session().get("username"));
        if(user == null){
            return ApplicationController.buildResponse("danger", "Current user not found. Please re-login.", "bad");
        }
        Form<Email> emailForm = Form.form(Email.class).bindFromRequest();
        if(emailForm.hasErrors()){
            ActionLogController.save(emailForm.errorsAsJson().toString());
            return badRequest(emailForm.errorsAsJson());
        }
        Email email = emailForm.get();
        if(email.email.equals(session().get("username"))){
            return ApplicationController.buildResponse("warning", "New and ald e-mail are identical.", "bad");
        }
        if(userModel.findByEmail(email.email) != null){
            return ApplicationController.buildResponse("warning", "Email exists. Please try again.", "bad");
        }
        user.setEmail(email.email);
        user.updatedDate = new Date();
        user.save();
        if(userModel.findByEmail(email.email) == null){
            return ApplicationController.buildResponse("danger", "Email has not been changed.", "bad");
        }else {
            session().clear();
            session().put("email", email.email);
            ActionLogController.save(Json.toJson(userModel.findByEmail(email.email)).toString());
            return ok(Json.toJson(userModel.findByEmail(email.email)));
        }
    }

    public Result changePassword(){

        UserModel user = userModel.findByEmail(session().get("username"));
        if(user == null){
            return ApplicationController.buildResponse("danger", "User not found. Please re-login.", "bad");
        }
        Form<Password> passwordForm = Form.form(Password.class).bindFromRequest();
        if(passwordForm.hasErrors()){
            return badRequest(passwordForm.errorsAsJson());
        }
        Password password = passwordForm.get();
        if(userModel.findByEmailAndPassword(session().get("usernname"), password.oldPassword) == null){
            return ApplicationController.buildResponse("warning", "Invalid old password.", "bad");
        }
        if(!password.password.equals(password.confirmPassword)){
            return ApplicationController.buildResponse(
                    "warning", "New password and password confirmation mismatch.", "bad"
            );
        }
        user.setPassword(password.password);
        user.updatedDate = new Date();
        user.save();
        if(userModel.findByEmailAndPassword(session().get("username"), password.password) == null){
            return ApplicationController.buildResponse("danger", "Password has not been changed.", "bad");
        }else {
            return ApplicationController.buildResponse("success", "Password changed.", "good");
        }
    }

}
