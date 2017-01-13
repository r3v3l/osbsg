package controllers.user;

import controllers.ApplicationController;
import models.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.GeneratePasswordService;

import java.util.Date;

/**
 * Created by astolarski on 07.01.17.
 */
public class FormindController extends Controller {

    private UserModel userModel = new UserModel();

    @AddCSRFToken
    public Result forgot(){
        ForgotErrorController forgotErrorController = new ForgotErrorController();
        return ok(
                views.html.user.forgot.render(
                        forgotErrorController.getMessages()
                )
        );
    }

    @RequireCSRFCheck
    public Result remind(){
        Form<UserAccessFormController.Forgot> forgotForm = Form.form(UserAccessFormController.Forgot.class).bindFromRequest();
        if(forgotForm.hasErrors()){
            return createMessage("formDanger", "Errors occurred. Please try again.");
        }
        UserAccessFormController.Forgot forgot = forgotForm.get();

        UserModel currentUser = userModel.findByEmail(forgot.email);
        if(currentUser == null){
            return createMessage("formWarning", "User not found. Please try again.");
        }

        GeneratePasswordService generatePasswordService = new GeneratePasswordService();
        String password = generatePasswordService.generatePassword();

        updateUser(currentUser, password);

        if(userModel.findByEmailAndPassword(forgot.email, password) == null){
            return createMessage("formError", "Errors occurred. Please try again.");
        }

        sendNewPassword(forgot, password);

        return createMessage("formSuccess", "New password has been generated. Check your e-mail.");
    }

    private void updateUser(UserModel currentUser, String password) {
        currentUser.setPassword(password);
        currentUser.updatedDate = new Date();
        currentUser.update();
    }

    private void sendNewPassword(UserAccessFormController.Forgot forgot, String password) {
        EmailController emailController = new EmailController();
        emailController.sendForgotPasswordMessage(forgot.email, password);
    }

    @NotNull
    private Result createMessage(String variable, String message) {
        return ApplicationController.createResponse(
                "redirect", variable, message,
                "/user/forgot-password"
        );
    }

}
