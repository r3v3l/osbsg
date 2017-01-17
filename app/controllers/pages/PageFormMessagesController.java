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
        if(flash("pageContentFormDanger") != null){
            messages.put("pageContentFormDanger", flash("pageContentFormDanger"));
        }
        if(flash("pageContentFormWarning") != null){
            messages.put("pageContentFormWarning", flash("pageContentFormWarning"));
        }
        if(flash("pageContentFormSuccess") != null){
            messages.put("pageContentFormSuccess", flash("pageContentFormSuccess"));
        }
        if(flash("titleDanger") != null){
            messages.put("titleDanger", flash("titleDanger"));
        }
        if(flash("titleWarning") != null){
            messages.put("titleWarning", flash("titleWarning"));
        }
        if(flash("titleSuccess") != null){
            messages.put("titleSuccess", flash("titleSuccess"));
        }
        if(flash("contentDanger") != null){
            messages.put("contentDanger", flash("contentDanger"));
        }
        if(flash("contentWarning") != null){
            messages.put("contentWarning", flash("contentWarning"));
        }
        if(flash("contentSuccess") != null){
            messages.put("contentSuccess", flash("contentSuccess"));
        }
        if(flash("pageMetaTagsFormDanger") != null){
            messages.put("pageMetaTagsFormDanger", flash("pageMetaTagsFormDanger"));
        }
        if(flash("pageMetaTagsFormWarning") != null){
            messages.put("pageMetaTagsFormWarning", flash("pageMetaTagsFormWarning"));
        }
        if(flash("pageMetaTagsFormSuccess") != null){
            messages.put("pageMetaTagsFormSuccess", flash("pageMetaTagsFormSuccess"));
        }
        if(flash("pageMetaTagsTitleDanger") != null){
            messages.put("pageMetaTagsTitleDanger", flash("pageMetaTagsTitleDanger"));
        }
        if(flash("pageMetaTagsTitleWarning") != null){
            messages.put("pageMetaTagsTitleWarning", flash("pageMetaTagsTitleWarning"));
        }
        if(flash("pageMetaTagsTitleSuccess") != null){
            messages.put("pageMetaTagsTitleSuccess", flash("pageMetaTagsTitleSuccess"));
        }
        if(flash("pageMetaTagsDescriptionDanger") != null){
            messages.put("pageMetaTagsDescriptionDanger", flash("pageMetaTagsDescriptionDanger"));
        }
        if(flash("pageMetaTagsDescriptionWarning") != null){
            messages.put("pageMetaTagsDescriptionWarning", flash("pageMetaTagsDescriptionWarning"));
        }
        if(flash("pageMetaTagsDescriptionSuccess") != null){
            messages.put("pageMetaTagsDescriptionSuccess", flash("pageMetaTagsDescriptionSuccess"));
        }
        if(flash("pageMetaTagsKeywordsDanger") != null){
            messages.put("pageMetaTagsKeywordsDanger", flash("pageMetaTagsKeywordsDanger"));
        }
        if(flash("pageMetaTagsKeywordsWarning") != null){
            messages.put("pageMetaTagsKeywordsWarning", flash("pageMetaTagsKeywordsWarning"));
        }
        if(flash("pageMetaTagsKeywordsSuccess") != null){
            messages.put("pageMetaTagsKeywordsSuccess", flash("pageMetaTagsKeywordsSuccess"));
        }
        if(flash("pageMetaTagsRobotsDanger") != null){
            messages.put("pageMetaTagsRobotsDanger", flash("pageMetaTagsRobotsDanger"));
        }
        if(flash("pageMetaTagsRobotsWarning") != null){
            messages.put("pageMetaTagsRobotsWarning", flash("pageMetaTagsRobotsWarning"));
        }
        if(flash("pageMetaTagsRobotsSuccess") != null){
            messages.put("pageMetaTagsRobotsSuccess", flash("pageMetaTagsRobotsSuccess"));
        }
        return messages;
    }

}
