package controllers.userAdmin;

import controllers.core.CoreResponseController;
import models.core.UserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserAdminMessagesController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result generateNotAccessMessage(){
        return responseController.createMessage(
                "formWarning", "You don't have permissions to access this page.", "/user"
        );
    }

    public Result generateLoginFirstMessage(){
        return responseController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result generateSuccessUpdate(UserAdminFormController.CompleteUser completeUser){
        return responseController.createMessage(
                "formSuccess", "User " +completeUser.email + " has been updated.", "/user-admin"
        );
    }

    public Result generateUpdateUserPasswordErrors(UserModel currentUser){
        return responseController.createMessage(
                "formWarning", "Passwords mismatch. Please try again.",
                "/user/admin.edit-user/" + currentUser.email
        );
    }

    public Result generateUpdateUserEmailErrors(UserModel currentUser){
        return responseController.createMessage(
                "formWarning", "Email exists. Please try again.",
                "/user/admin.edit-user/" + currentUser.email
        );
    }

    public Result generateFormErrors(){
        return responseController.createMessage(
                "formDanger", "User not found. Please try again.", "/user-admin"
        );
    }

    public Result generateUserErrors(){
        return responseController.createMessage(
                "formDanger", "User not found. Please try again.", "/user-admin"
        );
    }

    public Result generateDeleteUserError(){
        return responseController.createMessage(
                "formDanger", "User has not been deleted. Please try again.", "/user-admin"
        );
    }

    public Result generateDeleteUserSuccess(){
        return responseController.createMessage(
                "formSuccess", "User has been deleted.", "/user-admin"
        );
    }

    public Result createUserFormError(){
        return responseController.createMessage(
                "formDanger", "Errors occurred. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserEmailError(){
        return responseController.createMessage(
                "formWarning", "Email exists. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserPasswordError(){
        return responseController.createMessage(
                "formWarning", "Passwords mismatch. Please try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserError(){
        return responseController.createMessage(
                "formDanger","Errors occured. Plesae try again.", "/user-admin/add-new-user"
        );
    }

    public Result createUserSuccess(){
        return responseController.createMessage(
                "formSuccess", "User has been created.", "/user-admin/add-new-user"
        );
    }

}
