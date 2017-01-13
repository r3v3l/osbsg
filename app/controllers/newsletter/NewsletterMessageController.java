package controllers.newsletter;

import controllers.ApplicationController;
import models.UserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 11.01.17.
 */
public class NewsletterMessageController extends Controller {

    private ApplicationController applicationController = new ApplicationController();

    public Result youMustLoginFirst(){
        return applicationController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result invalidUserRole(UserModel currentUser){
        return applicationController.createMessage(
                "formWarning", "User " +currentUser.email+
                        " can not access this page. Please try again.", "/user"
        );
    }

    public Result formErrors(){
        return applicationController.createMessage(
                "newsletterFormDanger", "Errors occurred. Please try again.",
                request().getHeader("refere")
        );
    }

    public Result emailExists(String email){
        return applicationController.createMessage(
                "newsletterFormWarning", "Email " +email+ " exists. Thanks.",
                request().getHeader("referer")
        );
    }

    public Result emailCreated(String email){
        return applicationController.createMessage(
                "newsletterFormSuccess", "Email " +email+ " has been added to newsletter.",
                request().getHeader("referer")
        );
    }

}
