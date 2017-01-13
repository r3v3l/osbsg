package controllers.user;

import controllers.ApplicationController;
import models.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 07.01.17.
 */
public class SignInController extends Controller {

    private UserModel userModel = new UserModel();

    @AddCSRFToken
    public Result signIn(){
        SignInErrorController signInErrorController = new SignInErrorController();
        return ok(
                views.html.user.signIn.render(
                        signInErrorController.getMessages()
                )
        );

    }

    @RequireCSRFCheck
    public Result auth(){

        Form<UserAccessFormController.SignIn> signInForm = Form.form(UserAccessFormController.SignIn.class).bindFromRequest();
        if(signInForm.hasErrors()){
            return createMessage("formError", "Errors occurred. Please try again.");
        }
        UserAccessFormController.SignIn signIn = signInForm.get();
        UserModel currentUser = userModel.findByEmailAndPassword(signIn.email, signIn.password);
        if(currentUser == null){
            return createMessage("formDanger", "Invalid e-mail or password.");
        }
        session().put("email", signIn.email);
        return redirect("/dashboard");
    }

    public Result logout(){
        session().remove("email");
        session().clear();
        return redirect("/user/login");
    }

    @NotNull
    private Result createMessage(String messageName, String message) {
        return ApplicationController.createResponse(
                "redirect", messageName, message,
                "/user/login"
        );
    }

}
