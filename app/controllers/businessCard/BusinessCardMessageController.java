package controllers.businessCard;

import play.mvc.Controller;
import play.mvc.Result;
import controllers.core.CoreResponseController;

/**
 * Created by astolarski on 12.01.17.
 */
public class BusinessCardMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result youMustLoginFirst(){
        return responseController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result doNotAccess(){
        return responseController.createMessage("formWarning",
                "You don't have a permissions to open this page.", "/user"
        );
    }

    public Result canNotCreateCard(){
        return responseController.createMessage(
                "formDanger", "Business can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenCreated(){
        return responseController.createMessage(
                "formSuccess", "Businesss card has been created.",
                "/business-card/get-all"
        );
    }

    public Result canNotUpdated(){
        return responseController.createMessage(
                "formDanger", "Business card can not be created.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenUpdated(){
        return responseController.createMessage(
                "formSuccess", "Business card has been updated",
                request().getHeader("referer")
        );
    }

    public Result cardNotFound(){
        return responseController.createMessage(
                "formWarning", "Business card not found.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenDeleted(){
        return responseController.createMessage(
                "formSuccss", "Business card has been deleted.", request().getHeader("referer")
        );
    }

}
