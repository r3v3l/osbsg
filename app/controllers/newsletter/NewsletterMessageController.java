package controllers.newsletter;

import controllers.core.CoreResponseController;
import models.core.UserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 11.01.17.
 */
public class NewsletterMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result youMustLoginFirst(){
        return responseController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result invalidUserRole(UserModel currentUser){
        return responseController.createMessage(
                "formWarning", "User " +currentUser.email+
                        " can not access this page. Please try again.", "/user"
        );
    }

    public Result formErrors(){
        return responseController.createMessage(
                "newsletterFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result emailExists(String email){
        return responseController.createMessage(
                "newsletterFormWarning", "Email " +email+ " exists. Thanks.",
                request().getHeader("referer")
        );
    }

    public Result emailCreated(String email){
        return responseController.createMessage(
                "newsletterFormSuccess", "Email " +email+ " has been added to newsletter.",
                request().getHeader("referer")
        );
    }

}
