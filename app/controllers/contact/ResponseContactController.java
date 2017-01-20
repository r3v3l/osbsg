package controllers.contact;

import models.core.BusinessCardModel;
import models.core.ContactModel;
import models.core.ResponseContactModel;
import models.core.UserModel;
import play.data.Form;
import play.data.validation.Constraints;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.ContactRepo;
import repositories.ResponseRepo;
import services.core.RolesService;

import java.util.Date;

/**
 * Created by astolarski on 10.01.17.
 */
public class ResponseContactController extends Controller {

    private UserModel userModel = new UserModel();
    private ContactMessageController contactMessageController = new ContactMessageController();
    private ContactFormMessagesController contactFormMessagesController = new ContactFormMessagesController();
    private RolesService rolesService = new RolesService();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    public static class ContactResponse {

        @Constraints.Required
        public String response;

    }

    @AddCSRFToken
    public Result getContact(Long id){

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

        ContactModel currentContact = contactModel.findById(id);
        if (currentContact == null) {
            return contactMessageController.contactNotFound(currentContact);
        }
        return ok(
                views.html.contacts.response.render(
                        currentContact, currentUser, contactFormMessagesController.createMessages(),
                        contactModel.findLastFive(), businessCardModel.findLastFive(currentUser)
                )
        );
    }

    @RequireCSRFCheck
    public Result generateResponse(Long id){
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

        ContactModel currentContact = contactModel.findById(id);
        if(currentContact == null){
            return contactMessageController.contactNotFound(currentContact);
        }

        Form<ContactResponse> contactResponseForm = Form.form(ContactResponse.class).bindFromRequest();
        if(contactResponseForm.hasErrors()){
            return contactMessageController.responseFormErrors();
        }

        ContactResponse contactResponse = contactResponseForm.get();
        createResponse(currentContact, contactResponse);
        currentContactUpdate(currentContact);

        sendEmail(currentContact, contactResponse);

        return contactMessageController.contactHasBeenUpdated(currentContact);

    }

    private void sendEmail(ContactModel currentContact, ContactResponse contactResponse) {
        ContactEmailController contactEmailController = new ContactEmailController();
        contactEmailController.sendMessage(session().get("email"), currentContact.email, contactResponse.response);
    }

    private void currentContactUpdate(ContactModel currentContact) {
        ContactRepo contactRepo = new ContactRepo();
        contactRepo.currentContactUpdate(currentContact);
    }

    private void createResponse(ContactModel currentContact, ContactResponse contactResponse) {
        ResponseRepo responseRepo = new ResponseRepo();
        responseRepo.createResponse(currentContact, contactResponse);
    }

}
