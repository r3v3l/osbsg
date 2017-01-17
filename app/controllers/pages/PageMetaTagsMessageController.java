package controllers.pages;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by adrian on 17.01.17.
 */
public class PageMetaTagsMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result createFormErrors(){
        return responseController.createMessage(
                "pageMetaTagsFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result updateFormErrors(){
        return responseController.createMessage(
                "pageMetaTagsFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result metaTitleExits(String title){
        return responseController.createMessage(
                "pageMetaTagsFormWarning", "Title" +title+ " exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result metaDescriptionExits(String description){
        return responseController.createMessage(
                "pageMetaTagsFormWarning", "Description" +description+ " exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result metaDescriptionNotExists(String name){
        return responseController.createMessage(
                "pageMetaTagsFormWarning", "Meta description for page" +name+
                        " not exists. Please try again.", request().getHeader("referer")
        );
    }

    public Result canNotBeCreated(){
        return responseController.createMessage(
                "pageMetaTagsFormDanger", "Page meta tags can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenCreated(){
        return responseController.createMessage(
                "pageMetaTagsFormSuccess", "Page meta tags has been created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result canNotBeUpdated(){
        return responseController.createMessage(
                "pageMetaTagsFormDanger", "Page meta tags can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenUpdated(){
        return responseController.createMessage(
                "pageMetaTagsFormSuccess", "Page meta tags has been updated. Please try again.",
                request().getHeader("referer")
        );
    }

}
