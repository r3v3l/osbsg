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
public class CreatePageController extends Controller {

    private UserModel userModel = new UserModel();
    private PageRoleController pageRoleController = new PageRoleController();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();
    private PageFormMessagesController pageFormMessagesController;
    private PageModel pageModel = new PageModel();
    private PageStatusController pageStatusController = new PageStatusController();
    private PageMessageController pageMessageController = new PageMessageController();

    @AddCSRFToken
    public Result addPage(){
        if(!HomeController.checkAuth()){
            return pageMessageController.youMustLoginFirst();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){
            return pageMessageController.invalidUserRole(user);
        }
        RoleModel roleModel = new RoleModel();
        StatusModel statusModel = new StatusModel();
        return ok(
                views.html.pages.addPage.render(
                        user, contactModel.findAll(), businessCardModel.findAll(),
                        pageFormMessagesController.getMessages(), roleModel.findAll(), statusModel.findAll()
                )
        );
    }

    @RequireCSRFCheck
    public Result createPage(){
        if(!HomeController.checkAuth()){
            return pageMessageController.youMustLoginFirst();
        }
        UserModel user = userModel.findByEmail(session().get("email"));
        if(!PageRoleController.checkUserRole(user)){
            return pageMessageController.invalidUserRole(user);
        }
        Form<PageFormController.CurrentPage> currentPageForm = Form.form(
                PageFormController.CurrentPage.class
        ).bindFromRequest();
        if(currentPageForm.hasErrors()){
            return pageMessageController.createPageError(currentPageForm.get().name);
        }
        PageFormController.CurrentPage currentPage = currentPageForm.get();
        if(pageModel.findByName(currentPage.name) != null){
            return pageMessageController.pageExists(currentPage.name);
        }
        createNew(currentPage);
        if(pageModel.findByName(currentPage.name) != null){
            return pageMessageController.createPageError(currentPage.name);
        }
        return pageMessageController.createPageSuccess(currentPage.name);
    }

    private void createNew(PageFormController.CurrentPage currentPage){
        PageModel newPage = new PageModel();
        newPage.name = currentPage.name;
        newPage.roles = pageRoleController.getRoles(currentPage);
        newPage.statuses = pageStatusController.createStatusList(currentPage);
        newPage.creationDate = new Date();
        newPage.updateDate = new Date();
        newPage.save();
    }

}
