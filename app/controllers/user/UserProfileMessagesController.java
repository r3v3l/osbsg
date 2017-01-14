package controllers.user;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserProfileMessagesController extends Controller {

    public CoreResponseController responseController = new CoreResponseController();

    public Result youMustLogin(){
        return responseController.createMessage(
                "formDanger", "You must login first. Please try again.", "/user/login"
        );
    }

    public Result userProfileFormError(){
        return responseController.createMessage(
                "formDanger", "Errors occured. Please try again", "/user/profile"
        );
    }

    public Result userProfieHasBeenUpdated(){
        return responseController.createMessage(
                "formSuccess", "User profile has been updated.", "/user/profile"
        );
    }

    public Result userProfilePhotoFormError(){
        return responseController.createMessage(
                "photoFormDanger", "Errors occured. Please try again", "/user/profile"
        );
    }

    public Result userProfilePhotoHasBeenUpdated(){
        return responseController.createMessage(
                "photoFormSuccess", "User profile photo has been updated.", "/user/profile"
        );
    }

}
