package controllers.pages;

import controllers.HomeController;
import models.core.UserModel;
import play.filters.csrf.AddCSRFToken;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 15.01.17.
 */
public class DeletePageController extends Controller {

    private UserModel userModel = new UserModel();

    @AddCSRFToken
    public Result deletePage(String name){
        if(!HomeController.checkAuth()){

        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){

        }
        return ok();
    }

}
