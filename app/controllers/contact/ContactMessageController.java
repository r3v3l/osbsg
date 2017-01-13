package controllers.contact;

import controllers.ApplicationController;
import models.ContactModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactMessageController extends Controller {

    private ApplicationController applicationController = new ApplicationController();

    public Result mustLoginFirst(){
            return applicationController.createMessage(
                    "formDanger", "You must log in first.", request().getHeader("referer")
            );
    }

    public Result invalidRole(){
        return applicationController.createMessage(
                "formDanger", "You must log in first.", request().getHeader("referer")
        );
    }

    public Result success(){
        return applicationController.createMessage(
                "formSuccess", "We have our message. Thanks.", "/"
        );
    }

    public Result contactNotFound(ContactModel contactModel){
        return applicationController.createMessage(
                "formDanger", "Contact " +contactModel.name+ " not found. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result responseFormErrors(){
        return applicationController.createMessage(
                "formDanger", "Errors occured. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result contactHasBeenUpdated(ContactModel contactModel){
        return applicationController.createMessage(
                "formSuccess", "Contact " +contactModel.name+ " has been updated.",
                request().getHeader("referer")
        );
    }

}
