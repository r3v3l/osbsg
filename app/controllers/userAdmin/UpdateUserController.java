package controllers.userAdmin;

import controllers.user.EmailController;
import models.core.*;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.core.RolesService;

import java.util.HashMap;

/**
 * Created by astolarski on 09.01.17.
 */
public class UpdateUserController extends Controller {

    private UserModel userModel = new UserModel();
    private RoleModel roleModel = new RoleModel();
    private StatusModel statusModel = new StatusModel();
    private UserAdminMessagesController userAdminMessagesController = new UserAdminMessagesController();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result editUser(String email){

        if(session().get("email") == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(user == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }

        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(userModel.findByEmail(session().get("email"))) == false){
            return userAdminMessagesController.generateNotAccessMessage();
        }
        UserModel currentUser = userModel.findByEmail(email);
        if(currentUser == null){
            return userAdminMessagesController.generateUserErrors();
        }
        UserFormMessageController userFormMessageController = new UserFormMessageController();
        return ok
                (views.html.userAdmin.updateUser.render(
                        userFormMessageController.generateMessages(), currentUser, statusModel.findAll(),
                        roleModel.findAll(), createStatues(currentUser), createRoles(currentUser), user,
                        contactModel.findLastFive(), businessCardModel.findLastFive(currentUser)
                )
        );
    }

    @RequireCSRFCheck
    public Result updateUser(String email){
        if(session().get("email") == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }
        if(userModel.findByEmail(session().get("email")) == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }

        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(userModel.findByEmail(session().get("email"))) == false){
            return userAdminMessagesController.generateNotAccessMessage();
        }
        UserModel currentUser = userModel.findByEmail(email);
        if(currentUser == null){
            return userAdminMessagesController.generateUserErrors();
        }

        Form<UserAdminFormController.CompleteUser> completeUserForm = Form.form(
                UserAdminFormController.CompleteUser.class
        );
        if(completeUserForm.hasErrors()){
            return userAdminMessagesController.generateFormErrors();
        }

        UserAdminFormController.CompleteUser completeUser = completeUserForm.get();
        if((
                completeUser.email != null) && !completeUser.email.equals(currentUser.email)
                && (userModel.findByEmail(completeUser.email) != null)
                ){
            return userAdminMessagesController.generateUpdateUserEmailErrors(currentUser);
        }
        if(
                (completeUser.password != null) && !completeUser.password.equals("") &&
                        (completeUser.confirmPassword != null) && !completeUser.confirmPassword.equals("") &&
                        !completeUser.equals(completeUser.confirmPassword)
                ){
            return userAdminMessagesController.generateUpdateUserPasswordErrors(currentUser);
        }

        CreateAndUpdateController createAndUpdateController = new CreateAndUpdateController();
        createAndUpdateController.updateUser(currentUser, completeUser);

        UserModel updatedUser = userModel.findByEmail(completeUser.email);
        if(updatedUser != null){
            currentUser = updatedUser;
        }
        EmailController emailController = new EmailController();
        emailController.sendForgotPasswordMessage(completeUser.email, completeUser.password);
        return userAdminMessagesController.generateSuccessUpdate(completeUser);
    }

    private HashMap<String, String> createStatues(UserModel currentUser){

        HashMap<String, String> statuses = new HashMap<String, String>();
        for(StatusModel status: currentUser.statuses){
            statuses.put(status.name, status.name);
        }
        return statuses;

    }

    private HashMap<String, String> createRoles(UserModel currentUser){
        HashMap<String, String> roles = new HashMap<String, String>();
        for(RoleModel role: currentUser.roles){
            roles.put(role.name, role.name);
        }
        return roles;
    }

}
