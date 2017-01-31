package controllers.user.signup;

import controllers.user.signin.SignInController;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import service.user.CheckNewUser;
import service.user.CheckUserSignUpEmail;
import service.user.CreateNewUser;

/**
 * Created by adrian on 31.01.17.
 */
public class SignUpController extends Controller {

    public Result signUp(){
        Form<SignUpFormController.SignUp> signUpForm = Form.form(SignUpFormController.SignUp.class).bindFromRequest();
        if(signUpForm.hasErrors()){
            return badRequest(signUpForm.errorsAsJson());
        }

        SignUpFormController.SignUp signUp = signUpForm.get();
        return createNewUser(signUp);
    }

    private Result createNewUser(SignUpFormController.SignUp signUp) {
        SignInController signInController = new SignInController();
        if(checkEmail(signUp.email)){
            return signInController.response("error", "Wybrany adres e-mail już istnieje. Spróbuj ponownie");
        }
        if(!signUp.password.equals(signUp.confirmPassword)){
            return signInController.response("error", "Wprowadzone hasła nie są identyczne. Spróbuj ponownie.");
        }

        createUser(signUp);

        if(!checkUser(signUp)){
            return signInController.response("error", "Użytkownik nie został utworzony.");
        }
        return signInController.response("success", "Użytkownik został utworzony pomyślnie.");
    }

    private boolean checkEmail(String email){
        CheckUserSignUpEmail checkUserSignUpEmail = new CheckUserSignUpEmail();
        return checkUserSignUpEmail.checkEmail(email);
    }

    private void createUser(SignUpFormController.SignUp signUp){
        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.create(signUp);
    }

    private boolean checkUser(SignUpFormController.SignUp signUp){
        CheckNewUser checkNewUser = new CheckNewUser();
        return checkNewUser.check(signUp.email, signUp.password);
    }

}
