package controllers.user.api.forgot;

import controllers.user.forgot.ForgotFormController;
import controllers.user.signin.SignInController;
import helpers.user.PasswordGenerator;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import service.user.CheckNewUser;
import service.user.CheckUserSignUpEmail;
import service.user.SendNewPassword;
import service.user.UpdatePassword;

/**
 * Created by adrian on 31.01.17.
 */
public class ForgotController extends Controller {

    public Result forgot(){

        Form<ForgotFormController.Forgot> forgotForm = Form.form(ForgotFormController.Forgot.class).bindFromRequest();
        if(forgotForm.hasErrors()){
            return badRequest(forgotForm.errorsAsJson());
        }
        ForgotFormController.Forgot forgot = forgotForm.get();
        if(!checkEmail(forgot.email)){
            return response("error", "Wybrany adres e-mail nie istnieje w aplikacji.");
        }

        String password = PasswordGenerator.GenerateRandomString(
                12, 255, 1, 1, 1, 1
        );

        changePassword(forgot.email, password);

        if(!checkNewPassword(forgot.email, password)){
            return response("error", "Hasło nie może zostać zmienione.");
        }

        sendEmail(forgot.email, password);

        return response("success", "Hasło zostało zmienione i wysłane na podany adres e-mail.");

    }

    public void sendEmail(String email, String password){
        SendNewPassword sendNewPassword = new SendNewPassword();
        sendNewPassword.sendEmail(email, password);
    }

    public boolean checkNewPassword(String email, String password){
        CheckNewUser checkNewUser = new CheckNewUser();
        return checkNewUser.check(email, password);
    }

    public void changePassword(String email, String password){
        UpdatePassword updatePassword = new UpdatePassword();
        updatePassword.updatePassword(email, password);
    }

    public boolean checkEmail(String email){
        CheckUserSignUpEmail checkUserSignUpEmail = new CheckUserSignUpEmail();
        return checkUserSignUpEmail.checkEmail(email);
    }

    public Result response(String type, String message){
        SignInController signInController = new SignInController();
        return signInController.response(type, message);
    }

}
