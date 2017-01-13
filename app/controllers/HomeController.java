package controllers;

import controllers.contact.ContactFormMessagesController;
import play.filters.csrf.AddCSRFToken;
import play.mvc.*;

import views.html.*;

/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {

    private ContactFormMessagesController contactFormMessagesController = new ContactFormMessagesController();
    @AddCSRFToken
    public Result index() {
        return ok(index.render(contactFormMessagesController.createMessages()));
    }

}
