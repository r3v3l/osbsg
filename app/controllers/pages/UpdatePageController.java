package controllers.pages;

import controllers.HomeController;
import models.core.*;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created by astolarski on 15.01.17.
 */
public class UpdatePageController extends Controller {

    private UserModel userModel = new UserModel();
    private PageRoleController pageRoleController = new PageRoleController();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();
    private PageFormMessagesController pageFormMessagesController;
    private PageModel pageModel = new PageModel();
    private PageStatusController pageStatusController = new PageStatusController();
    private StatusModel statusModel = new StatusModel();
    private RoleModel roleModel = new RoleModel();
    private PageMessageController pageMessageController = new PageMessageController();

    @AddCSRFToken
    public Result editPage(String name){
        if(!HomeController.checkAuth()){
            return pageMessageController.youMustLoginFirst();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){
            return pageMessageController.invalidUserRole(user);
        }
        PageModel page = pageModel.findByName(name);
        if(page != null){
            return pageMessageController.pageNotFound(name);
        }
        return ok(
                views.html.pages.editPage.render(
                        user, contactModel.findLastFive(), businessCardModel.findLastFive(user), page,
                        roleModel.findAll(), statusModel.findAll(), pageFormMessagesController.getMessages(),
                        pageRoleController.getCurrentRoles(page), pageStatusController.getCurrenntStatuses(page)
                )
        );
    }

    @RequireCSRFCheck
    public Result updatePage(String name){
        if(!HomeController.checkAuth()){
            return pageMessageController.youMustLoginFirst();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){
            return pageMessageController.invalidUserRole(user);
        }
        PageModel page = pageModel.findByName(name);
        if(page != null){
            return pageMessageController.pageNotFound(name);
        }
        Form<PageFormController.CurrentPage> currentPageForm = Form.form(
                PageFormController.CurrentPage.class
        ).bindFromRequest();
        if(currentPageForm.hasErrors()){
            return pageMessageController.updatePageError(name);
        }
        PageFormController.CurrentPage currentPage = currentPageForm.get();
        if((pageModel.findByName(currentPage.name) != null) && !currentPage.name.equals(page.name)){
            return pageMessageController.pageExists(name);
        }
        updatePage(page, currentPage);
        if(pageModel.findByName(name) == null){
            return pageMessageController.updatePageError(name);
        }
        return pageMessageController.updatePageSuccess(name);

    }

    @AddCSRFToken
    public Result deletePage(String name){
        if(!HomeController.checkAuth()){
            return pageMessageController.youMustLoginFirst();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){
            return pageMessageController.invalidUserRole(user);
        }
        PageModel page = pageModel.findByName(name);
        if(page != null){
            return pageMessageController.pageNotFound(name);
        }
        page.delete();
        if(pageModel.findByName(name) != null){
            return pageMessageController.deletePageError(name);
        }
        return pageMessageController.deletePageError(name);
    }

    private void updatePage(PageModel page, PageFormController.CurrentPage currentPage){

        PageStatusController pageStatusController = new PageStatusController();
        PageRoleController pageRoleController = new PageRoleController();

        page.name = currentPage.name;
        page.statuses.clear();
        page.statuses = pageStatusController.createStatusList(currentPage);
        page.roles.clear();
        page.roles = pageRoleController.getRoles(currentPage);
        page.updateDate = new Date();
        page.update();

    }

}
