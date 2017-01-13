package controllers.user;

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

        if(flash("emailFormDanger") != null){
            mesages.put("emailFormDanger", flash("emailFormDanger"));
        }

        if(flash("emailFormWarning") != null){
            mesages.put("emailFormWarning", flash("emailFormWarning"));
        }

        if(flash("emailFormSuccess") != null){
            mesages.put("emailFormSuccess", flash("emailFormSuccess"));
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

        if(flash("passwordFormDanger") != null){
            mesages.put("passwordFormDanger", flash("passwordFormDanger"));
        }

        if(flash("passwordFormWarning") != null){
            mesages.put("passwordFormWarning", flash("passwordFormWarning"));
        }

        if(flash("passwordFormSuccess") != null){
            mesages.put("passwordFormSuccess", flash("passwordFormSuccess"));
        }

        return mesages;

    }

}
