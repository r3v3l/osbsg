package controllers.pages;

import controllers.HomeController;
import models.core.PageContentModel;
import models.core.PageModel;
import models.core.UserModel;
import play.data.Form;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created by astolarski on 16.01.17.
 */
public class PageContentController extends Controller {

    private PageMessageController pageMessageController = new PageMessageController();
    private PageModel pageModel = new PageModel();
    private UserModel userModel = new UserModel();
    private PageContentModel pageContentModel = new PageContentModel();
    private PageContentMessageController pageContentMessageController = new PageContentMessageController();

    @RequireCSRFCheck
    public Result createContent(String name){
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
        Form<PageContentFormController.CurrentContent> currentContentForm = Form.form(
                PageContentFormController.CurrentContent.class
        ).bindFromRequest();
        if(currentContentForm.hasErrors()){
            return pageContentMessageController.createFormErrors();
        }
        PageContentFormController.CurrentContent currentContent = currentContentForm.get();
        if(pageContentModel.findByTitle(currentContent.title) != null){
            return pageContentMessageController.titleExists(currentContent.title);
        }
        createContent(page, currentContent);
        if(pageContentModel.findByTitle(currentContent.title) == null){
            return pageContentMessageController.createContentErrors(page.name);
        }
        return pageContentMessageController.createContentSuccess(page.name);
    }

    @RequireCSRFCheck
    public Result updateContent(String name){
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
        Form<PageContentFormController.CurrentContent> currentContentForm = Form.form(
                PageContentFormController.CurrentContent.class
        ).bindFromRequest();
        if(currentContentForm.hasErrors()){
            return pageContentMessageController.updateFormErrors();
        }
        PageContentFormController.CurrentContent currentContent = currentContentForm.get();
        if(
                (pageContentModel.findByTitle(currentContent.title) != null) &&
                        !page.pageContent.title.equals(currentContent.title)
                ){
            return pageContentMessageController.titleExists(currentContent.title);
        }
        updatePageContent(page, currentContent);
        if(pageContentModel.findByTitle(currentContent.title) == null){
            return pageContentMessageController.updateContentErrors(page.name);
        }
        return pageContentMessageController.updateContentSuccess(page.name);
    }

    private void updatePageContent(PageModel page, PageContentFormController.CurrentContent currentContent) {
        page.pageContent.title = currentContent.title;
        page.pageContent.content = currentContent.content;
        page.pageContent.updateDate = new Date();
        page.updateDate = new Date();
        page.pageContent.update();
        page.update();
    }

    public void createContent(PageModel pageModel, PageContentFormController.CurrentContent currentContent){
        PageContentModel pageContentModel = new PageContentModel();
        pageContentModel.page = pageModel;
        pageContentModel.title = currentContent.title;
        pageContentModel.content = currentContent.content;
        pageContentModel.creationDate = new Date();
        pageContentModel.updateDate = new Date();
        pageContentModel.save();
    }

}
