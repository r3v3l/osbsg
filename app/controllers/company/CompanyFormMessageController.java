package controllers.company;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 09.01.17.
 */
public class CompanyFormMessageController extends Controller {
    
    public HashMap<String, String> generateMessages(){
        
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

        if(flash("lawFormDanger") != null){
            messages.put("lawFormDanger", flash("lawFormDanger"));
        }

        if(flash("lawFormWarning") != null){
            messages.put("lawFormWarning", flash("lawFormWarning"));
        }

        if(flash("lawFormSuccess") != null){
            messages.put("lawFormSuccess", flash("lawFormSuccess"));
        }

        if(flash("countryDanger") != null){
            messages.put("countryDanger", flash("countryDanger"));
        }

        if(flash("countryWarning") != null){
            messages.put("countryWarning", flash("countryWarning"));
        }

        if(flash("countrySuccess") != null){
            messages.put("countrySuccess", flash("countrySuccess"));
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

        if(flash("streetDanger") != null){
            messages.put("streetDanger", flash("streetDanger"));
        }

        if(flash("streetWarning") != null){
            messages.put("streetWarning", flash("streetWarning"));
        }

        if(flash("streetSuccess") != null){
            messages.put("streetSuccess", flash("streetSuccess"));
        }

        if(flash("cityDanger") != null){
            messages.put("cityDanger", flash("cityDanger"));
        }

        if(flash("cityWarning") != null){
            messages.put("cityWarning", flash("cityWarning"));
        }

        if(flash("citySuccess") != null){
            messages.put("citySuccess", flash("citySuccess"));
        }

        if(flash("postalCodeDanger") != null){
            messages.put("postalCodeDanger", flash("postalCodeDanger"));
        }

        if(flash("postalCodeWarning") != null){
            messages.put("postalCodeWarning", flash("postalCodeWarning"));
        }

        if(flash("postalCodeSuccess") != null){
            messages.put("postalCodeSuccess", flash("postalCodeSuccess"));
        }

        if(flash("taxNumberDanger") != null){
            messages.put("taxNumberDanger", flash("taxNumberDanger"));
        }

        if(flash("taxNumberWarning") != null){
            messages.put("taxNumberWarning", flash("taxNumberWarning"));
        }

        if(flash("taxNumberSuccess") != null){
            messages.put("taxNumberSuccess", flash("taxNumberSuccess"));
        }
        
        return messages;
        
    }
    
}
