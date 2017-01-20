package controllers.pages;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by adrian on 18.01.17.
 */
public class PageOpenGraphTagsMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result createFormErrors(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result titleExists(String title){
        return responseController.createMessage(
                "pageOpenGraphTagsFormWarning", "Title " +title+" exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result descriptionExists(String description){
        return responseController.createMessage(
                "pageOpenGraphTagsFormWarning", "Description " +description+" exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result urlExists(String url){
        return responseController.createMessage(
                "pageOpenGraphTagsFormWarning", "Url " +url+" exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result tagsCreated(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormSuccess", "Open graph tags has been created.",
                request().getHeader("referer")
        );
    }

    public Result tagsNotCreated(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormDanger", "Open graph tags can not be created.",
                request().getHeader("referer")
        );
    }

    public Result tagsNotExist(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormDanger", "Open graph tags not exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result canNotBeUpdated(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormDanger", "Open graph tags can not be updated. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hadBeenUpdated(){
        return responseController.createMessage(
                "pageOpenGraphTagsFormSuccess", "Open graph tags had been updated. Please try again.",
                request().getHeader("referer")
        );
    }
}
