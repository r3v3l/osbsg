package controllers.businessCard;

import controllers.ApplicationController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 12.01.17.
 */
public class BusinessCardMessageController extends Controller {

    private ApplicationController applicationController = new ApplicationController();

    public Result youMustLoginFirst(){
        return applicationController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result doNotAccess(){
        return applicationController.createMessage("formWarning",
                "You don't have a permissions to open this page.", "/user"
        );
    }

    public Result canNotCreateCard(){
        return applicationController.createMessage(
                "formDanger", "Business can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenCreated(){
        return applicationController.createMessage(
                "formSuccess", "Businesss card has been created.",
                "/business-card/get-all"
        );
    }

    public Result canNotUpdated(){
        return applicationController.createMessage(
                "formDanger", "Business card can not be created.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenUpdated(){
        return applicationController.createMessage(
                "formSuccess", "Business card has been updated",
                request().getHeader("referer")
        );
    }

    public Result cardNotFound(){
        return applicationController.createMessage(
                "formWarning", "Business card not found.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenDeleted(){
        return applicationController.createMessage(
                "formSuccss", "Business card has been deleted.", request().getHeader("referer")
        );
    }

}
