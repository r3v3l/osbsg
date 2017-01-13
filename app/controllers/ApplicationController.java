package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import models.RoleModel;
import models.StatusModel;
import models.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.data.validation.Constraints;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created by adrian on 08.12.16.
 */
public class ApplicationController extends Controller {

    private String userRoleName = "user";
    private String inactiveStatusName = "inactive";

    public static class User {

        @Constraints.Required
        @Constraints.Email
        public String email;

    }

    public static class SignUp extends User {

        @Constraints.Required
        @Constraints.MinLength(12)
        public String password;

        @Constraints.Required
        public String confirmPassword;

    }

    public static class SignIn extends User {

        @Constraints.Required
        public String password;

    }

    public static Result buildResponse(String type, String message, String responseType){
        ActionLogController.save(buildJsonResponse(type, message).toString());
        switch (responseType){
            case "bad":
                return badRequest(buildJsonResponse(type, message));
            case "good":
                return ok(buildJsonResponse(type, message));
            default:
                return ok(buildJsonResponse(type, message));
        }
    }

    public static ObjectNode buildJsonResponse(String type, String message){
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        wrapper.put(type, msg);
        return wrapper;
    }

    public static Result createResponse(String type, String messageName, String message, String redirect){
        ActionLogController.save(message);
        flash(messageName, message);
        switch (type){
            case "redirect":
                return redirect(redirect);
            default:
                return redirect(redirect);
        }
    }

    public Result signup(){

        Form<SignUp> signUpForm = Form.form(SignUp.class).bindFromRequest();
        if(signUpForm.hasErrors()){
            ActionLogController.save(signUpForm.errorsAsJson().toString());
            return badRequest(signUpForm.errorsAsJson());
        }

        SignUp newUser = signUpForm.get();
        UserModel userModel = new UserModel();
        UserModel existingUser = userModel.findByEmail(newUser.email);
        if(existingUser != null){
            return buildResponse("warning", "User exists. Please try again.", "bad");
        }
        if(!newUser.password.equals(newUser.confirmPassword)) {
            return buildResponse("warning", "Passwords mismatch.", "bad");
        }
        UserModel user = new UserModel();
        user.setEmail(newUser.email);
        user.setPassword(newUser.password);
        user.statuses.add(getStatus(inactiveStatusName));
        user.roles.add(getRole(userRoleName));
        user.creationDate = new Date();
        user.updatedDate = new Date();
        user.save();
        if(userModel.findByEmailAndPassword(newUser.email, newUser.password) == null) {
            return buildResponse("danger", "User has not been created. Please try again.", "bad");
        }

        return buildResponse("success", "User created successfully.", "good");

    }

    public Result signin(){
        Form<SignIn> signInForm = Form.form(SignIn.class).bindFromRequest();
        if(signInForm.hasErrors()){
            ActionLogController.save(signInForm.errorsAsJson().toString());
            return badRequest(signInForm.errorsAsJson());
        }
        SignIn signIn = signInForm.get();
        UserModel userModel = new UserModel();
        UserModel existingUser = userModel.findByEmailAndPassword(signIn.email, signIn.password);
        if(existingUser != null){
            session().put("email", signIn.email);
            return buildResponse("success", "User logged.", "good");
        }else {
            return buildResponse("warning", "Invalid email or password.", "bad");
        }
    }

    public Result forgot(){
        Form<User> userForm = Form.form(User.class).bindFromRequest();
        if(userForm.hasErrors()){
            ActionLogController.save(userForm.errorsAsJson().toString());
            return badRequest(userForm.errorsAsJson());
        }
        User user = userForm.get();
        UserModel userModel = new UserModel();
        UserModel existingUser = userModel.findByEmail(user.email);
        if(existingUser == null){
            return buildResponse("warning", "Invalid email. User not found.", "bad");
        }
        String password = "passwordMock";
        existingUser.setPassword(password);
        existingUser.updatedDate = new Date();
        existingUser.save();
        if(existingUser.findByEmailAndPassword(user.email, password) != null){
            return buildResponse("success", "Password changed.", "good");
        }else {
            return buildResponse("danger", "Password has not been changed. Please try again.", "bad");
        }
    }

    public RoleModel getRole(String name){
        RoleModel roleModel = new RoleModel();
        return roleModel.findByName(name);
    }

    public StatusModel getStatus(String name){
        StatusModel statusModel = new StatusModel();
        return statusModel.findByName(name);
    }

    @NotNull
    public Result createMessage(String variable, String message, String redirect) {
        return ApplicationController.createResponse(
                "redirect", variable, message, redirect
        );
    }

}
