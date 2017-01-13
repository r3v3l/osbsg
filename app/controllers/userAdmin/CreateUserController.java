package controllers.userAdmin;

import controllers.ApplicationController;
import controllers.user.EmailController;
import models.*;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;


/**
 * Created by astolarski on 08.01.17.
 */
public class CreateUserController extends Controller {

    private RoleModel roleModel = new RoleModel();
    private StatusModel statusModel = new StatusModel();
    private UserModel userModel = new UserModel();
    private UserAdminMessagesController userAdminMessagesController = new UserAdminMessagesController();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result addUser(){

        if(session().get("email") == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }

        UserFormMessageController userFormMessageController = new UserFormMessageController();
        return ok(
                views.html.userAdmin.addUser.render(
                        userFormMessageController.generateMessages(), roleModel.findAll(), statusModel.findAll(),
                        currentUser, contactModel.findLastFive(), businessCardModel.findLastFive(currentUser)
                )
        );

    }

    @RequireCSRFCheck
    public Result createUser(){
        Form<UserAdminFormController.CompleteUser> completeUserForm = Form.form(
                UserAdminFormController.CompleteUser.class
        );
        if(completeUserForm.hasErrors()){
            return userAdminMessagesController.createUserFormError();
        }

        UserAdminFormController.CompleteUser completeUser = completeUserForm.get();

        if(userModel.findByEmail(completeUser.email) != null){
            return userAdminMessagesController.createUserEmailError();
        }

        if(!completeUser.password.equals(completeUser.confirmPassword)){
            return userAdminMessagesController.createUserPasswordError();
        }

        CreateAndUpdateController createAndUpdateController = new CreateAndUpdateController();
        createAndUpdateController.createUser(completeUser);

        if(userModel.findByEmailAndPassword(completeUser.email, completeUser.password) == null){
            return userAdminMessagesController.createUserError();
        }

        EmailController emailController = new EmailController();
        emailController.sendForgotPasswordMessage(completeUser.email, completeUser.password);
        return userAdminMessagesController.createUserSuccess();
    }

}
