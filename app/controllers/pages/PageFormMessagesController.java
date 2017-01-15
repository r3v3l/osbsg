package controllers.pages;

import play.mvc.Controller;

import java.util.HashMap;

/**
 * Created by astolarski on 15.01.17.
 */
public class PageFormMessagesController extends Controller {

    public HashMap<String, String> getMessages(){
        HashMap<String, String> messages = new HashMap<>();
        if(flash("pageFormDanger") != null){
            messages.put("pageFormDanger", flash("pageFormDanger"));
        }
        if(flash("pageFormWarning") != null){
            messages.put("pageFormWarning", flash("pageFormWarning"));
        }
        if(flash("pageFormSuccess") != null){
            messages.put("pageFormSuccess", flash("pageFormSuccess"));
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
        if(flash("statusDanger") != null){
            messages.put("statusDanger", flash("statusDanger"));
        }
        if(flash("statusWarning") != null){
            messages.put("statusWarning", flash("statusWarning"));
        }
        if(flash("statusSuccess") != null){
            messages.put("statusSuccess", flash("statusSuccess"));
        }
        if(flash("roleDanger") != null){
            messages.put("roleDanger", flash("roleDanger"));
        }
        if(flash("roleWarning") != null){
            messages.put("roleWarning", flash("roleWarning"));
        }
        if(flash("roleSuccess") != null){
            messages.put("roleSuccess", flash("roleSuccess"));
        }
        return messages;
    }

}
