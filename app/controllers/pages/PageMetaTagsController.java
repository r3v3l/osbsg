package controllers.pages;

import controllers.HomeController;
import models.core.PageMetaTagsModel;
import models.core.PageModel;
import models.core.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created by adrian on 17.01.17.
 */
public class PageMetaTagsController extends Controller {

    private PageMessageController pageMessageController = new PageMessageController();
    private UserModel userModel = new UserModel();
    private PageModel pageModel = new PageModel();
    private PageMetaTagsModel pageMetaTagsModel = new PageMetaTagsModel();
    private PageMetaTagsMessageController pageMetaTagsMessageController = new PageMetaTagsMessageController();

    @RequireCSRFCheck
    public Result createMetaTags(String name){
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
        Form<PageMetaTagsFormController.CurrentTags> currentTagsForm = Form.form(
                PageMetaTagsFormController.CurrentTags.class
        ).bindFromRequest();
        if(currentTagsForm.hasErrors()){
            return pageMetaTagsMessageController.createFormErrors();
        }
        PageMetaTagsFormController.CurrentTags currentTags = currentTagsForm.get();
        if(pageMetaTagsModel.findByTitle(currentTags.title) != null){
            return pageMetaTagsMessageController.metaTitleExits(currentTags.title);
        }
        if(pageMetaTagsModel.findByDescription(currentTags.description) != null){
            return pageMetaTagsMessageController.metaDescriptionExits(currentTags.description);
        }
        PageMetaTagsModel pageMetaTagsModel = createPageMetaTags(page, currentTags);
        if(
                (pageMetaTagsModel.findByTitle(currentTags.title) == null) &&
                        (pageMetaTagsModel.findByDescription(currentTags.description) == null)
                ){
            return pageMetaTagsMessageController.canNotBeCreated();
        }
        return pageMetaTagsMessageController.hasBeenCreated();
    }

    @RequireCSRFCheck
    public Result updateMetaTags(String name){
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
        if(page.pageMetaTags == null){
            return pageMetaTagsMessageController.metaDescriptionNotExists(page.name);
        }
        Form<PageMetaTagsFormController.CurrentTags> currentTagsForm = Form.form(
                PageMetaTagsFormController.CurrentTags.class
        ).bindFromRequest();
        if(currentTagsForm.hasErrors()){
            return pageMetaTagsMessageController.updateFormErrors();
        }
        PageMetaTagsFormController.CurrentTags currentTags = currentTagsForm.get();
        if(
                (pageMetaTagsModel.findByTitle(currentTags.title) != null)
                        && !currentTags.title.equals(page.pageMetaTags.title)
                ){
            return pageMetaTagsMessageController.metaTitleExits(currentTags.title);
        }
        if(
                (pageMetaTagsModel.findByDescription(currentTags.description) != null)
                        && !currentTags.description.equals(page.pageMetaTags.description)
                ){
            return pageMetaTagsMessageController.metaDescriptionExits(currentTags.description);
        }
        updatePageMetaTags(page, currentTags);
        if(
                (pageMetaTagsModel.findByTitle(currentTags.title) == null) &&
                        (pageMetaTagsModel.findByDescription(currentTags.description) == null)
                ){
            return pageMetaTagsMessageController.canNotBeUpdated();
        }
        return pageMetaTagsMessageController.hasBeenUpdated();
    }

    private void updatePageMetaTags(PageModel page, PageMetaTagsFormController.CurrentTags currentTags) {
        page.pageMetaTags.title = currentTags.title;
        page.pageMetaTags.description = currentTags.description;
        page.pageMetaTags.robots = currentTags.robots;
        page.pageMetaTags.keywords = currentTags.keywords;
        page.pageMetaTags.updateDate = new Date();
        page.pageMetaTags.update();
        page.updateDate = new Date();
        page.update();
    }

    @NotNull
    private PageMetaTagsModel createPageMetaTags(PageModel page, PageMetaTagsFormController.CurrentTags currentTags) {
        PageMetaTagsModel pageMetaTagsModel = new PageMetaTagsModel();
        pageMetaTagsModel.page = page;
        pageMetaTagsModel.title = currentTags.title;
        pageMetaTagsModel.description = currentTags.description;
        pageMetaTagsModel.keywords = currentTags.keywords;
        pageMetaTagsModel.robots = currentTags.robots;
        pageMetaTagsModel.creationDate = new Date();
        pageMetaTagsModel.updateDate = new Date();
        pageMetaTagsModel.save();
        return pageMetaTagsModel;
    }

}
