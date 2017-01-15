package controllers.pages;

import controllers.core.CoreResponseController;
import models.core.UserModel;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 11.01.17.
 */
public class PageMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result youMustLoginFirst(){
        return responseController.createMessage(
                "formWarning", "You must login first.", "/user/login"
        );
    }

    public Result invalidUserRole(UserModel currentUser){
        return responseController.createMessage(
                "formWarning", "User " +currentUser.email+
                        " can not access this page. Please try again.", "/user"
        );
    }

    public Result createFormErrors(){
        return responseController.createMessage(
                "pageFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result updateFormErrors(){
        return responseController.createMessage(
                "pageFormDanger", "Errors occurred. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result pageNotFound(String name){
        return responseController.createMessage(
                "pageFormDanger", "Page " +name+ " not found. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result createPageError(String name){
        return responseController.createMessage(
                "pageFormDanger", "Page " +name+ " can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result createPageSuccess(String name){
        return responseController.createMessage(
                "pageFormSuccess", "Page " +name+ " has been created.",
                request().getHeader("/pages/get-all")
        );
    }

    public Result updatePageError(String name){
        return responseController.createMessage(
                "pageFormDanger", "Page " +name+ " can not be updated. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result updatePageSuccess(String name){
        return responseController.createMessage(
                "pageFormSuccess", "Page " +name+ " has been updated.",
                request().getHeader("referer")
        );
    }

    public Result deletePageError(String name){
        return responseController.createMessage(
                "pageFormDanger", "Page " +name+ " can not be deleted. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result deletePageSuccess(String name){
        return responseController.createMessage(
                "pageFormSuccess", "Page " +name+ " has been deleted.",
                request().getHeader("referer")
        );
    }

    public Result pageExists(String name){
        return responseController.createMessage(
                "pageFormWarning", "Page " +name+ " has been deleted.",
                request().getHeader("referer")
        );
    }

}
