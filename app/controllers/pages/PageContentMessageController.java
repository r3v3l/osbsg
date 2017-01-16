package controllers.pages;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 16.01.17.
 */
public class PageContentMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result createFormErrors(){
        return responseController.createMessage(
                "pageContentFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result updateFormErrors(){
        return responseController.createMessage(
                "pageContentFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result titleExists(String title){
        return responseController.createMessage(
                "pageContentFormDanger", "Page title exists. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result createContentErrors(String name){
        return responseController.createMessage(
                "pageContentFormDanger", "Page " +name+ " content can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result createContentSuccess(String name){
        return responseController.createMessage(
                "pageContentFormDanger", "Page " +name+ " has been created.",
                request().getHeader("referer")
        );
    }

    public Result updateContentErrors(String name){
        return responseController.createMessage(
                "pageContentFormDanger", "Page " +name+ " content can not be updated. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result updateContentSuccess(String name){
        return responseController.createMessage(
                "pageContentFormDanger", "Page " +name+ " has been updated.",
                request().getHeader("referer")
        );
    }

}
