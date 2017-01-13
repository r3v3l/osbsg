package controllers.dashboard;

import controllers.ApplicationController;
import models.BusinessCardModel;
import models.ContactModel;
import models.UserModel;
import play.filters.csrf.AddCSRFToken;
import play.mvc.Controller;
import play.mvc.Result;
import services.RolesService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by astolarski on 08.01.17.
 */
public class DashboardController extends Controller {


    private UserModel userModel = new UserModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result dashboard(){
        if(session().get("email") == null){
            return createMessage("formWarning", "You must login first.");
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return createMessage("formWarning", "You must login first.");
        }
        return ok(
                views.html.dashboard.dashboard.render(
                        currentUser, getContacts(currentUser), businessCardModel.findLastFive(currentUser)
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

     @NotNull
        private Result createMessage(String messageName, String message) {
            return ApplicationController.createResponse(
                    "redirect", messageName, message,
                    "/user/login"
            );
        }
}
