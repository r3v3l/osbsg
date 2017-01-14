package controllers.newsletter;

import models.core.BusinessCardModel;
import models.core.ContactModel;
import models.core.NewsletterModel;
import models.core.UserModel;
import play.data.Form;
import play.data.validation.Constraints;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.core.RolesService;

import java.util.Date;

/**
 * Created by astolarski on 11.01.17.
 */
public class NewsletterController extends Controller {

    private RolesService rolesService = new RolesService();
    private UserModel userModel = new UserModel();
    private NewsletterModel newsletterModel = new NewsletterModel();
    private NewsletterMessageController newsletterMessageController = new NewsletterMessageController();
    private ContactModel contactModel = new ContactModel();
    private NewsletterFormMessageController newsletterFormMessageController = new NewsletterFormMessageController();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    public static class Email {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

    }

    @RequireCSRFCheck
    public Result createEmail(){

        Form<Email> emailForm = Form.form(Email.class).bindFromRequest();
        if(emailForm.hasErrors()){
            return newsletterMessageController.formErrors();
        }

        Email email = emailForm.get();
        if(newsletterModel.findByEmail(email.email) != null){
            return newsletterMessageController.emailExists(email.email);
        }

        NewsletterModel currentEmail = new NewsletterModel();
        currentEmail.email = email.email;
        currentEmail.creationDate = new Date();
        currentEmail.updateDate = new Date();
        currentEmail.save();

        if(newsletterModel.findByEmail(email.email) == null){
            return newsletterMessageController.formErrors();
        }
        return newsletterMessageController.emailCreated(email.email);
    }

    @AddCSRFToken
    public Result getAll(){

        if(session().get("email") == null){
            return newsletterMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return newsletterMessageController.youMustLoginFirst();
        }
        if(!rolesService.isAdmin(currentUser)){
            return newsletterMessageController.invalidUserRole(currentUser);
        }

        return ok(
                views.html.newsletter.newsletter.render(
                        currentUser, newsletterModel.findAll(), contactModel.findLastFive(),
                        newsletterFormMessageController.createMessages(), businessCardModel.findLastFive(currentUser)
                )
        );

    }

    @AddCSRFToken
    public Result getPage(int page, int size){
        if(session().get("email") == null){
            return newsletterMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return newsletterMessageController.youMustLoginFirst();
        }
        if(!rolesService.isAdmin(currentUser)){
            return newsletterMessageController.invalidUserRole(currentUser);
        }

        return ok(
                views.html.newsletter.newsletter.render(
                        currentUser, newsletterModel.findPage(page, size), contactModel.findLastFive(),
                        newsletterFormMessageController.createMessages(), businessCardModel.findLastFive(currentUser)
                )
        );
    }



}
