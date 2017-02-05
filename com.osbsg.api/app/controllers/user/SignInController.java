package controllers.user;

import controllers.user.signin.SignInFormController;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import repository.user.GetUserByEmailAndPassword;

/**
 * Created by adrian on 05.02.17.
 */
public class SignInController extends Controller {

    @AddCSRFToken
    public Result sign(){
        return ok(
                views.html.user.signin.signIn.render(
                        flash("danger"), flash("warning"), flash("success")
                )
        );
    }

    @RequireCSRFCheck
    public Result auth(){

        Form<SignInFormController.SignIn> signInForm = Form.form(SignInFormController.SignIn.class).bindFromRequest();
        if(signInForm.hasErrors()){
            flash("danger", "Podczas wysyłania formularza wystapiły błędy.");
            return redirect("/sginIn");
        }
        SignInFormController.SignIn signIn = signInForm.get();
        if(!getUser(signIn.email, signIn.password)){
            flash("warning", "Użytkownik o wybranym adresie e-mail oraz haśle nie istnieje. Spróbuj ponownie.");
            return redirect("/signIn");
        }
        session().put("email", signIn.email);
        flash("success", "Dziękujemy za zalogowanie się.");
        return redirect("/signIn");
    }

    private boolean getUser(String email, String password){
        GetUserByEmailAndPassword getUserByEmailAndPassword = new GetUserByEmailAndPassword();
        if(getUserByEmailAndPassword.findByEmailAndPassword(email, password) != null){
            return true;
        }
        return false;
    }

}
