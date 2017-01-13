package controllers.user;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 07.01.17.
 */
public class SignUpErrorController extends Controller {

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

        if(flash("passwordDanger") != null){
            messages.put("passwordDanger", flash("passwordDanger"));
        }

        if(flash("passwordWarning") != null){
            messages.put("passwordWarning", flash("passwordWarning"));
        }

        if(flash("passwordSuccess") != null){
            messages.put("passwordSuccess", flash("passwordSuccess"));
        }

        if(flash("confirmPasswordDanger") != null){
            messages.put("confirmPasswordDanger", flash("confirmPasswordDanger"));
        }

        if(flash("confirmPasswordWarning") != null){
            messages.put("confirmPasswordWarning", flash("confirmPasswordWarning"));
        }

        if(flash("confirmPasswordSuccess") != null){
            messages.put("confirmPasswordSuccess", flash("confirmPasswordSuccess"));
        }

        if(flash("acceptTermOfUseDanger") != null){
            messages.put("acceptTermOfUseDanger", flash("acceptTermOfUseDanger"));
        }

        if(flash("acceptTermOfUseWarning") != null){
            messages.put("acceptTermOfUseWarning", flash("acceptTermOfUseWarning"));
        }

        if(flash("acceptTermOfUseSuccess") != null){
            messages.put("acceptTermOfUseSuccess", flash("acceptTermOfUseSuccess"));
        }

        if(flash("acceptPrivacyPolicyDanger") != null){
            messages.put("acceptPrivacyPolicyDanger", flash("acceptPrivacyPolicyDanger"));
        }

        if(flash("acceptPrivacyPolicyWarning") != null){
            messages.put("acceptPrivacyPolicyWarning", flash("acceptPrivacyPolicyWarning"));
        }

        if(flash("acceptPrivacyPolicySuccess") != null){
            messages.put("acceptPrivacyPolicySuccess", flash("acceptPrivacyPolicySuccess"));
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
