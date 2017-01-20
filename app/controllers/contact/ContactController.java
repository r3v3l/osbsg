package controllers.contact;

import models.core.BusinessCardModel;
import models.core.ContactModel;
import models.core.UserModel;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ContactRepo;
import services.core.RolesService;

import java.util.List;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactController extends Controller {

    private UserModel userModel = new UserModel();
    private RolesService rolesService = new RolesService();
    private ContactMessageController contactMessageController = new ContactMessageController();
    private ContactFormMessagesController contactFormMessagesController = new ContactFormMessagesController();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @RequireCSRFCheck
    public Result addMessage(){

        Form<ContactFormController.Contact> contactForm = Form.form(ContactFormController.Contact.class)
                .bindFromRequest();
        if(contactForm.hasErrors()){

        }
        createContact(contactForm.get());
        return contactMessageController.success();

    }

    private void createContact(ContactFormController.Contact contact){
        ContactRepo contactRepo = new ContactRepo();
        contactRepo.createContact(contact);
    }

    @AddCSRFToken
    public Result getMessages(){

        if(session().get("email") == null){
            return contactMessageController.mustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return contactMessageController.mustLoginFirst();
        }
        if(!rolesService.isAdmin(currentUser)){
            return contactMessageController.invalidRole();
        }

        return ok(
                views.html.contacts.all.render(
                        currentUser, getContacts(currentUser), contactFormMessagesController.createMessages(),
                        businessCardModel.findLastFive(currentUser)
                )
        );

    }

    @AddCSRFToken
    public Result getPage(int page, int size){

        if(session().get("email") == null){
            return contactMessageController.mustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return contactMessageController.mustLoginFirst();
        }
        if(!rolesService.isAdmin(currentUser)){
            return contactMessageController.invalidRole();
        }

        return ok();
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

}
