package repositories;

import controllers.newsletter.NewsletterController;
import models.core.NewsletterModel;

import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class NewsletterRepo {

    public void createNewsletterEmail(NewsletterController.Email email){
        NewsletterModel currentEmail = new NewsletterModel();
        currentEmail.email = email.email;
        currentEmail.creationDate = new Date();
        currentEmail.updateDate = new Date();
        currentEmail.save();
    }

}
