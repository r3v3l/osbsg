package controllers.user;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserProfileFormMessageController extends Controller {

    public HashMap<String, String> createMessages(){

        HashMap<String, String> messages = new HashMap<>();

        if(flash("formDanger") != null){
            messages.put("formDanger", flash("formDanger"));
        }

        if(flash("formWarning") != null){
            messages.put("formWarning", flash("formWarning"));
        }

        if(flash("formSuccess") != null){
            messages.put("formSuccess", flash("formSuccess"));
        }

        if(flash("photoFormDanger") != null){
            messages.put("photoFormDanger", flash("photoFormDanger"));
        }

        if(flash("photoFormWarning") != null){
            messages.put("photoFormWarning", flash("photoFormWarning"));
        }

        if(flash("photoFormSuccess") != null){
            messages.put("photoFormSuccess", flash("photoFormSuccess"));
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

        if(flash("bioDanger") != null){
            messages.put("bioDanger", flash("bioDanger"));
        }

        if(flash("bioWarning") != null){
            messages.put("bioWarning", flash("bioWarning"));
        }

        if(flash("bioSuccess") != null){
            messages.put("bioSuccess", flash("bioSuccess"));
        }

        if(flash("photoDanger") != null){
            messages.put("photoDanger", flash("photoDanger"));
        }

        if(flash("photoWarning") != null){
            messages.put("photoWarning", flash("photoWarning"));
        }

        if(flash("photoSuccess") != null){
            messages.put("photoSuccess", flash("photoSuccess"));
        }

        return messages;

    }

}
