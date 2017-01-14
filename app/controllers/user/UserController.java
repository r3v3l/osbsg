package controllers.user;

import controllers.core.CoreResponseController;
import models.core.BusinessCardModel;
import models.core.ContactModel;
import models.core.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.core.RolesService;

import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 08.01.17.
 */
public class UserController extends Controller {

    private UserModel userModel = new UserModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result getUser(){
        if(session().get("email") == null){
            return createMessage("formWarning", "You must login first.");
        }
        if(userModel.findByEmail(session().get("email")) == null){
            return createMessage("formWarning", "You must login first.");
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        UserFormMessageController userFormMessageController = new UserFormMessageController();
        return ok(
                views.html.user.user.render(
                        currentUser, userFormMessageController.generateMessages(), getContacts(currentUser),
                        businessCardModel.findLastFive(currentUser)
                )
        );
    }

    private List<ContactModel> getContacts(UserModel currentUser){
        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(currentUser)){
            ContactModel contactModel = new ContactModel();
            return contactModel.findLastFive();
        } else {
            return null;
        }
    }

    @RequireCSRFCheck
    public Result updateEmail(){

        if(session().get("email") == null){
            return createMessage("formWarning", "You must login first.");
        }

        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return createMessage("formWarning", "You must login first.");
        }

        Form<UserFromController.UserEmail> userEmailForm = Form.form(UserFromController.UserEmail.class);
        if(userEmailForm.hasErrors()){
            return createUserMessage("emailFormDanger", "Errors occurred. Please try again.");
        }

        UserFromController.UserEmail userEmail = userEmailForm.get();
        if(userEmail.email.equals(session().get("email"))){
            return createUserMessage("emailFormWarning", "Old and new e-mail are identical.");
        }else if(userModel.findByEmail(userEmail.email) != null){
            return createUserMessage("emailFormWarning", "E-mail exists. Please try again.");
        }else {
            updateUser(currentUser, userEmail);
        }

        if(userModel.findByEmail(userEmail.email) == null){
            return createUserMessage("emailFormDanger", "Errors occurred. Please try again.");
        }

        updateSession(userEmail);

        return createUserMessage("emailFormSuccess", "E-mail has been updated.");
    }

    @RequireCSRFCheck
    public Result updatePassword(){

        if(session().get("email") == null){
            return createMessage("formWarning", "You must login first.");
        }

        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return createMessage("formWarning", "You must login first.");
        }

        Form<UserFromController.UserPassword> userPasswordForm = Form.form(UserFromController.UserPassword.class)
                .bindFromRequest();
        if(userPasswordForm.hasErrors()){
            return createUserMessage("passwordFormDanger", "Errors occurred. Please try again.");
        }

        UserFromController.UserPassword userPassword = userPasswordForm.get();

        if(!userPassword.password.equals(userPassword.confirmPassword)){
            return createUserMessage("passwordFormWarning", "Passwords mismatch.");
        }

        if(userModel.findByEmailAndPassword(session().get("email"), userPassword.password) != null){
            return createUserMessage("passwordFormWarning", "Old and new password are identical.");
        }

        updatePassword(currentUser, userPassword);

        if(userModel.findByEmailAndPassword(session().get("email"), userPassword.password) == null){
            return createUserMessage("passwordFormDanger", "Errors occurred. Please try again.");
        }

        updatePasswordEmail(userPassword);

        return createUserMessage("passwordFormSuccess", "Password has been updated.");

    }

    private void updatePasswordEmail(UserFromController.UserPassword userPassword) {
        EmailController emailController = new EmailController();
        emailController.sendForgotPasswordMessage(session().get("email"), userPassword.password);
    }

    private void updatePassword(UserModel currentUser, UserFromController.UserPassword userPassword) {
        currentUser.setPassword(userPassword.password);
        currentUser.updatedDate = new Date();
        currentUser.update();
    }

    private void updateSession(UserFromController.UserEmail userEmail) {
        session().clear();
        session().put("email", userEmail.email);

        EmailController emailController = new EmailController();
        emailController.sendRegistrationEmail(userEmail.email);
    }

    private void updateUser(UserModel currentUser, UserFromController.UserEmail userEmail) {
        currentUser.setEmail(userEmail.email);
        currentUser.updatedDate = new Date();
        currentUser.save();
    }

    @NotNull
    private Result createMessage(String messageName, String message) {
        return CoreResponseController.createResponse(
                "redirect", messageName, message,
                "/user/login"
        );
    }

    @NotNull
    private Result createUserMessage(String messageName, String message) {
        return CoreResponseController.createResponse(
                "redirect", messageName, message,
                "/user"
        );
    }

}
