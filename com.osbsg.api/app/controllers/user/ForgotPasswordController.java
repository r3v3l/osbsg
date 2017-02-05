package controllers.user;

import controllers.user.api.forgot.ForgotController;
import controllers.user.forgot.ForgotFormController;
import helpers.user.PasswordGenerator;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by adrian on 05.02.17.
 */
public class ForgotPasswordController extends Controller {

    @AddCSRFToken
    public Result forgot(){
        return ok(
                views.html.user.forgot.forgot.render(
                        flash("danger"), flash("warning"), flash("success")
                )
        );
    }

    @RequireCSRFCheck
    public Result remind(){

        Form<ForgotFormController.Forgot> forgotForm = Form.form(ForgotFormController.Forgot.class).bindFromRequest();
        if(forgotForm.hasErrors()){
            flash("danger", "Przykro nam, wystąpiły błędy.");
            return redirect("/forgot");
        }
        ForgotFormController.Forgot forgot = forgotForm.get();

        ForgotController forgotController = new ForgotController();
        if(!forgotController.checkEmail(forgot.email)){
            flash("warning", "Wybrany adres e-mail nie istnieje w aplikacji.");
            return redirect("/forgot");
        }

        String password = PasswordGenerator.GenerateRandomString(
                12, 255, 1, 1, 1, 1
        );

        forgotController.changePassword(forgot.email, password);

        if(!forgotController.checkNewPassword(forgot.email, password)){
            flash("warning", "Hasło nie może zostać zmienione.");
            return redirect("/forgot");
        }

        forgotController.sendEmail(forgot.email, password);

        flash("success", "Hasło zostało zmienione i wysłane na podany adres e-mail.");
        return ok("/forgot");
    }

}
