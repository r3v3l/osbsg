package controllers.pages;

import controllers.HomeController;
import models.core.*;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
/**
 * Created by astolarski on 15.01.17.
 */
public class PageController  extends Controller{

    private PageRoleController pageRoleController = new PageRoleController();
    private UserModel userModel = new UserModel();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();
    private PageModel pageModel = new PageModel();
    private PageFormMessagesController pageFormMessagesController = new PageFormMessagesController();
    private RoleModel roleModel = new RoleModel();
    private StatusModel statusModel = new StatusModel();

    @AddCSRFToken
    public Result getAll(){
        if(!HomeController.checkAuth()){

        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){

        }
        return ok(
                views.html.pages.all.render(
                        user, contactModel.findLastFive(), businessCardModel.findLastFive(user), pageModel.findAll(),
                        pageFormMessagesController.getMessages()

                )
        );
    }

    @AddCSRFToken
    public Result getPage(int page, int size){
        if(!HomeController.checkAuth()){

        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){

        }
        return ok(
                views.html.pages.all.render(
                        user, contactModel.findLastFive(), businessCardModel.findLastFive(user),
                        pageModel.findByPage(page, size), pageFormMessagesController.getMessages()

                )
        );
    }

    @AddCSRFToken
    public Result getCurrentPage(String name){
        if(!HomeController.checkAuth()){

        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){

        }
        PageModel currentPage = pageModel.findByName(name);
        if(currentPage == null){

        }
        PageStatusController pageStatusController = new PageStatusController();
        RoleModel roleModel = new RoleModel();
        StatusModel statusModel = new StatusModel();
        return ok(
                views.html.pages.currentPage.render(
                        user, contactModel.findLastFive(), businessCardModel.findLastFive(user),
                        currentPage, pageFormMessagesController.getMessages(),
                        pageRoleController.getCurrentRoles(currentPage),
                        pageStatusController.getCurrenntStatuses(currentPage), roleModel.findAll(),
                        statusModel.findAll()

                )
        );
    }

    @RequireCSRFCheck
    public Result getOnlinePage(String name){

        return ok();

    }

}
