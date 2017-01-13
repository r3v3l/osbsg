package controllers.businessCard;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 11.01.17.
 */
public class BusinessCardFormMessagesController extends Controller {

    public HashMap<String, String> createMessages(){

        HashMap<String, String> messages = new HashMap<String, String>();

        if(flash("formDanger") != null){
            messages.put("formDanger", flash("formDanger"));
        }

        if(flash("formWarning") != null){
            messages.put("formwarning", flash("formWarning"));
        }

        if(flash("formSuccess") != null){
            messages.put("formSuccess", flash("formSuccess"));
        }

        if(flash("firstnameDanger") != null){
            messages.put("firstnameDanger", flash("firstnameDanger"));
        }

        if(flash("firstnameWarning") != null){
            messages.put("firstnameWarning", flash("firstnameWarning"));
        }

        if(flash("firstnameSuccess") != null){
            messages.put("firstnameSuccess", flash("firstnameSuccess"));
        }

        if(flash("lastnameDanger") != null){
            messages.put("lastnameDanger", flash("lastnameDanger"));
        }

        if(flash("lastnameWarning") != null){
            messages.put("lastnameWarning", flash("lastnameWarning"));
        }

        if(flash("lastnameSuccess") != null){
            messages.put("lastnameSuccess", flash("lastnameSuccess"));
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

        return messages;

    }

}
