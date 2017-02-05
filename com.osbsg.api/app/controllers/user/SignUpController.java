package controllers.user;

import controllers.user.signup.SignUpFormController;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import service.user.CheckUserSignUpEmail;
import service.user.CreateNewUser;

/**
 * Created by adrian on 05.02.17.
 */
public class SignUpController extends Controller {

    @AddCSRFToken
    public Result signUp(){
        return ok(
                views.html.user.signup.signUp.render(
                        flash("danger"), flash("warning"), flash("success")
                )
        );
    }

    @RequireCSRFCheck
    public Result createUser(){
        Form<SignUpFormController.SignUp> signUpForm = Form.form(SignUpFormController.SignUp.class).bindFromRequest();
        if(signUpForm.hasErrors()){
            flash("danger", "Przykro nam, wystapiły błędy, spróbuj ponownie.");
            return redirect("/signUp");
        }

        SignUpFormController.SignUp signUp = signUpForm.get();
        if(userExists(signUp.email)){
            flash("warning", "Użytkownik o wybranym adresie e-mail istnieje. Wpisz inny adres e-mail.");
            return redirect("/signUp");
        }

        if(!signUp.password.equals(signUp.confirmPassword)){
            flash("warning", "Hasła nie są identyczne.");
            return redirect("/signUp");
        }

        createNew(signUp);

        if(!userExists(signUp.email)){
            flash("danger", "Nie udało się utworzyć użytkownika. Spróbuj ponownie.");
            return redirect("/signUp");
        }

        flash("success", "Użytkownik został utworzony pomyslnie.");
        return redirect("/signUp");
    }

    private boolean userExists(String email){
        CheckUserSignUpEmail checkUserSignUpEmail = new CheckUserSignUpEmail();
        return checkUserSignUpEmail.checkEmail(email);
    }

    private void createNew(SignUpFormController.SignUp signUp){
        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.create(signUp);
    }

}
