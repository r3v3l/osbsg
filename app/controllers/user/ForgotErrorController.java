package controllers.user;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 07.01.17.
 */
public class ForgotErrorController extends Controller {

    public HashMap<String, String> getMessages(){

        HashMap<String, String> messages = new HashMap<>();

        if(flash("emailDanger") != null){
            messages.put("emailDanger", flash("emailDanger"));
        }

        if(flash("emailWarning") != null){
            messages.put("emailWarning", flash("emailWarning"));
        }

        if(flash("emailSuccess") != null){
            messages.put("emailSuccess", flash("emailSuccess"));
        }

        if(flash("formDanger") != null){
            messages.put("formDanger", flash("formDanger"));
        }

        if(flash("formWarning") != null){
            messages.put("formWarning", flash("formWarning"));
        }

        if(flash("formSuccess") != null){
            messages.put("formSuccess", flash("formSuccess"));
        }

        return messages;
    }

}
