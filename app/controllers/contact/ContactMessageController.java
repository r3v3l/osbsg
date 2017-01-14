package controllers.contact;

import controllers.core.CoreResponseController;
import models.core.ContactModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result mustLoginFirst(){
            return responseController.createMessage(
                    "formDanger", "You must log in first.", request().getHeader("referer")
            );
    }

    public Result invalidRole(){
        return responseController.createMessage(
                "formDanger", "You must log in first.", request().getHeader("referer")
        );
    }

    public Result success(){
        return responseController.createMessage(
                "formSuccess", "We have our message. Thanks.", "/"
        );
    }

    public Result contactNotFound(ContactModel contactModel){
        return responseController.createMessage(
                "formDanger", "Contact " +contactModel.name+ " not found. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result responseFormErrors(){
        return responseController.createMessage(
                "formDanger", "Errors occured. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result contactHasBeenUpdated(ContactModel contactModel){
        return responseController.createMessage(
                "formSuccess", "Contact " +contactModel.name+ " has been updated.",
                request().getHeader("referer")
        );
    }

}
