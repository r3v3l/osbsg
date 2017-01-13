package controllers.user;

import controllers.ApplicationController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserProfileMessagesController extends Controller {

    public ApplicationController applicationController = new ApplicationController();

    public Result youMustLogin(){
        return applicationController.createMessage(
                "formDanger", "You must login first. Please try again.", "/user/login"
        );
    }

    public Result userProfileFormError(){
        return applicationController.createMessage(
                "formDanger", "Errors occured. Please try again", "/user/profile"
        );
    }

    public Result userProfieHasBeenUpdated(){
        return applicationController.createMessage(
                "formSuccess", "User profile has been updated.", "/user/profile"
        );
    }

    public Result userProfilePhotoFormError(){
        return applicationController.createMessage(
                "photoFormDanger", "Errors occured. Please try again", "/user/profile"
        );
    }

    public Result userProfilePhotoHasBeenUpdated(){
        return applicationController.createMessage(
                "photoFormSuccess", "User profile photo has been updated.", "/user/profile"
        );
    }

}
