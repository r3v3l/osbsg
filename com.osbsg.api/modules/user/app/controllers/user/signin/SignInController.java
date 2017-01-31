package controllers.user.signin;

import com.fasterxml.jackson.databind.node.ObjectNode;
import controllers.core.MainController;
import controllers.user.signin.SignInFormController.SignIn;
import models.core.CoreUserModel;
import play.data.Form;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import repository.user.GetUserByEmailAndPassword;
import service.user.CheckUserSignInEmail;
import service.user.I_EmailCheckable;

/**
 * Created by adrian on 23.01.17.
 */
public class SignInController extends Controller implements I_EmailCheckable {

    public Result signIn(){
        Form<SignIn> signInForm = Form.form(SignIn.class).bindFromRequest();
        if(signInForm.hasErrors()){
            return badRequest(signInForm.errorsAsJson());
        }
        SignIn signIn = signInForm.get();
        if(checkEmail(signIn.email)){
            return response("error", "Adres email " +signIn.email+ " nie został odnaleziony.");
        }
        GetUserByEmailAndPassword getUserByEmailAndPassword = new GetUserByEmailAndPassword();
        CoreUserModel coreUserModel = getUserByEmailAndPassword.findByEmailAndPassword(signIn.email, signIn.password);
        if(coreUserModel == null){
            return response("error", "Nieprawidłowe hasło.");
        }

        session().put("email", coreUserModel.email);
        return buildLoginResponse(coreUserModel.email);

    }

    public Result buildLoginResponse(String email){
        ObjectNode wrapper = Json.newObject();
        ObjectNode message = Json.newObject();
        message.put("message", "Użytkownik został zalogowany.");
        message.put("email", email);
        wrapper.put("success", message);
        return ok(wrapper);
    }

    @Override
    public boolean checkEmail(String email) {
        CheckUserSignInEmail checkUserSignInEmail = new CheckUserSignInEmail();
        return checkUserSignInEmail.checkEmail(email);
    }

    public Result response(String type, String message){
        MainController mainController = new MainController();
        return ok(mainController.buildJsonResponse(type, message));
    }

}
