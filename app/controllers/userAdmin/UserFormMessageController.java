package controllers.userAdmin;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 08.01.17.
 */
public class UserFormMessageController extends Controller {

    public HashMap<String, String> generateMessages(){

        HashMap<String, String> mesages = new HashMap<>();

        if(flash("emailDanger") != null){
            mesages.put("emailDanger", flash("emailDanger"));
        }

        if(flash("emailWarning") != null){
            mesages.put("emailWarning", flash("emailWarning"));
        }

        if(flash("emailSuccess") != null){
            mesages.put("emailSuccess", flash("emailSuccess"));
        }

        if(flash("userFormDanger") != null){
            mesages.put("userFormDanger", flash("userFormDanger"));
        }

        if(flash("userFormWarning") != null){
            mesages.put("userFormWarning", flash("userFormWarning"));
        }

        if(flash("userFormSuccess") != null){
            mesages.put("userFormSuccess", flash("userFormSuccess"));
        }

        if(flash("passwordDanger") != null){
            mesages.put("passwordDanger", flash("passwordDanger"));
        }

        if(flash("passwordWarning") != null){
            mesages.put("passwordWarning", flash("passwordWarning"));
        }

        if(flash("passwordSuccess") != null){
            mesages.put("passwordSuccess", flash("passwordSuccess"));
        }

        if(flash("confirmPasswordDanger") != null){
            mesages.put("confirmPasswordDanger", flash("confirmPasswordDanger"));
        }

        if(flash("confirmPasswordWarning") != null){
            mesages.put("confirmPasswordWarning", flash("confirmPasswordWarning"));
        }

        if(flash("confirmPasswordSuccess") != null){
            mesages.put("confirmPasswordSuccess", flash("confirmPasswordSuccess"));
        }

        if(flash("rolesDanger") != null){
            mesages.put("rolesDanger", flash("rolesDanger"));
        }

        if(flash("rolesWarning") != null){
            mesages.put("rolesWarning", flash("rolesWarning"));
        }

        if(flash("rolesSuccess") != null){
            mesages.put("rolesSuccess", flash("rolesSuccess"));
        }

        if(flash("rolesDanger") != null){
            mesages.put("rolesDanger", flash("rolesDanger"));
        }

        if(flash("statusesWarning") != null){
            mesages.put("statusesWarning", flash("statusesWarning"));
        }

        if(flash("statusesSuccess") != null){
            mesages.put("statusesSuccess", flash("statusesSuccess"));
        }

        return mesages;

    }

}
