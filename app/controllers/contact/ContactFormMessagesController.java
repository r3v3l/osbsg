package controllers.contact;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactFormMessagesController extends Controller {

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

        if(flash("nameDanger") != null){
            messages.put("nameDanger", flash("nameDanger"));
        }

        if(flash("nameWarning") != null){
            messages.put("nameWarning", flash("nameWarning"));
        }

        if(flash("nameSuccess") != null){
            messages.put("nameSuccess", flash("nameSuccess"));
        }

        if(flash("emailDanger") != null){
            messages.put("emailDanger", flash("emailDanger"));
        }

        if(flash("emailWarning") != null){
            messages.put("emailWarning", flash("emailWarning"));
        }

        if(flash("emailSuccess") != null){
            messages.put("emailSuccess", flash("emailSuccess"));
        }

        if(flash("phoneDanger") != null){
            messages.put("phoneDanger", flash("phoneDanger"));
        }

        if(flash("phoneWarning") != null){
            messages.put("phoneWarning", flash("phoneWarning"));
        }

        if(flash("phoneSuccess") != null){
            messages.put("phoneSuccess", flash("phoneSuccess"));
        }

        if(flash("messageDanger") != null){
            messages.put("messageDanger", flash("messageDanger"));
        }

        if(flash("messageWarning") != null){
            messages.put("messageWarning", flash("messageWarning"));
        }

        if(flash("messageSuccess") != null){
            messages.put("messageSuccess", flash("messageSuccess"));
        }

        return messages;
    }

}
