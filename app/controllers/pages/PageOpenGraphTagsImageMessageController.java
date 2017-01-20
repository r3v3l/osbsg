package controllers.pages;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by adrian on 20.01.17.
 */
public class PageOpenGraphTagsImageMessageController extends Controller {

    private CoreResponseController responseController = new CoreResponseController();

    public Result canNotCreated(String name){
        return responseController.createMessage(
                "pageOpenGraphTagsImageFormDanger", "Image " +name+ " can not be created. Please try again.",
                request().getHeader("referer")
        );
    }

    public Result hasBeenCreated(String name){
        return responseController.createMessage(
                "pageOpenGraphTagsImageFormSuccess", "Image " +name+ " has been created.",
                request().getHeader("referer")
        );
    }

}
