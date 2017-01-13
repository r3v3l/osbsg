package controllers.newsletter;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 11.01.17.
 */
public class NewsletterFormMessageController extends Controller {

    public HashMap<String, String> createMessages(){

        HashMap<String, String> messages = new HashMap<String, String>();

        if(flash("formDanger") != null){
            messages.put("formDanger", flash("formDanger"));
        }

        if(flash("formWarning") != null){
            messages.put("formWarning", flash("formWarning"));
        }

        if(flash("formSuccess") != null){
            messages.put("formSuccess", flash("formSuccess"));
        }

        if(flash("newsletterFormDanger") != null){
            messages.put("newsletterFormDanger", flash("newsletterFormDanger"));
        }

        if(flash("newsletterFormWarning") != null){
            messages.put("newsletterFormWarning", flash("newsletterFormWarning"));
        }

        if(flash("newsletterFormSuccess") != null){
            messages.put("newsletterFormSuccess", flash("newsletterFormSuccess"));
        }

        if(flash("newsletterEmailDanger") != null){
            messages.put("newsletterEmailDanger", flash("newsletterEmailDanger"));
        }

        if(flash("newsletterEmailWarning") != null){
            messages.put("newsletterEmailWarning", flash("newsletterEmailWarning"));
        }

        if(flash("newsletterEmailSuccess") != null){
            messages.put("newsletterEmailSuccess", flash("newsletterEmailSuccess"));
        }

        return messages;

    }

}
