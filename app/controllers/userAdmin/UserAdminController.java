package controllers.userAdmin;

import models.BusinessCardModel;
import models.ContactModel;
import models.UserModel;
import play.filters.csrf.AddCSRFToken;
import play.mvc.Controller;
import play.mvc.Result;
import services.RolesService;

/**
 * Created by astolarski on 08.01.17.
 */
public class UserAdminController extends Controller {

    private UserModel userModel = new UserModel();
    private UserAdminMessagesController userAdminMessagesController = new UserAdminMessagesController();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result getUsers(){

        if(session().get("email") == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return userAdminMessagesController.generateLoginFirstMessage();
        }

        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(userModel.findByEmail(session().get("email"))) == false){
            return userAdminMessagesController.generateNotAccessMessage();
        }
        UserFormMessageController userFormMessageController = new UserFormMessageController();
        return ok(
                views.html.userAdmin.users.render(
                        userModel.findAll(), userFormMessageController.generateMessages(), currentUser,
                        contactModel.findLastFive(), businessCardModel.findLastFive(currentUser)
                )
        );

    }

    public Result deleteUser(String email){
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
        currentUser.delete();
        if(userModel.findByEmail(email) != null){
            return userAdminMessagesController.generateDeleteUserError();
        }
        return userAdminMessagesController.generateDeleteUserSuccess();
    }

}
