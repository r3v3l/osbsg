package controllers.userAdmin;

import controllers.ApplicationController;
import models.UserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserAdminMessagesController extends Controller {

    private ApplicationController createUserController = new ApplicationController();

    public Result generateNotAccessMessage(){
        return createUserController.createMessage(
                "formWarning", "You don't have permissions to access this page.", "/user"
        );
    }

    public Result generateLoginFirstMessage(){
        return createUserController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result generateSuccessUpdate(UserAdminFormController.CompleteUser completeUser){
        return createUserController.createMessage(
                "formSuccess", "User " +completeUser.email + " has been updated.", "/user-admin"
        );
    }

    public Result generateUpdateUserPasswordErrors(UserModel currentUser){
        return createUserController.createMessage(
                "formWarning", "Passwords mismatch. Please try again.",
                "/user/admin.edit-user/" + currentUser.email
        );
    }

    public Result generateUpdateUserEmailErrors(UserModel currentUser){
        return createUserController.createMessage(
                "formWarning", "Email exists. Please try again.",
                "/user/admin.edit-user/" + currentUser.email
        );
    }

    public Result generateFormErrors(){
        return createUserController.createMessage(
                "formDanger", "User not found. Please try again.", "/user-admin"
        );
    }

    public Result generateUserErrors(){
        return createUserController.createMessage(
                "formDanger", "User not found. Please try again.", "/user-admin"
        );
    }

    public Result generateDeleteUserError(){
        return createUserController.createMessage(
                "formDanger", "User has not been deleted. Please try again.", "/user-admin"
        );
    }

    public Result generateDeleteUserSuccess(){
        return createUserController.createMessage(
                "formSuccess", "User has been deleted.", "/user-admin"
        );
    }

    public Result createUserFormError(){
        return createUserController.createMessage(
                "formDanger", "Errors occurred. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserEmailError(){
        return createUserController.createMessage(
                "formWarning", "Email exists. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserPasswordError(){
        return createUserController.createMessage(
                "formWarning", "Passwords mismatch. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserError(){
        return createUserController.createMessage(
                "formDanger","Errors occured. Plesae try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserSuccess(){
        return createUserController.createMessage(
                "formSuccess", "User has been created.", "/user-admin/add-new-user"
        );
    }

}
