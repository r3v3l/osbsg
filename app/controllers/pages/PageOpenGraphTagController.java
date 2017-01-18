package controllers.pages;

import controllers.HomeController;
import models.core.PageModel;
import models.core.PageOpenGraphTagsModel;
import models.core.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.Date;

/**
 * Created by adrian on 18.01.17.
 */
public class PageOpenGraphTagController extends Controller {

    private PageMessageController pageMessageController = new PageMessageController();
    private UserModel userModel = new UserModel();
    private PageModel pageModel = new PageModel();
    private PageOpenGraphTagsModel pageOpenGraphTagsModel = new PageOpenGraphTagsModel();
    private PageOpenGraphMessageController pageOpenGraphMessageController = new PageOpenGraphMessageController();

    @RequireCSRFCheck
    public Result createOpenGraphTags(String name){
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
        Form<PageOpenGraphTagsFormController.CurrentOpenGraphTags> currentOpenGraphTagsForm = Form.form(
                PageOpenGraphTagsFormController.CurrentOpenGraphTags.class
        ).bindFromRequest();
        if(currentOpenGraphTagsForm.hasErrors()){
            return pageOpenGraphMessageController.createFormErrors();
        }
        PageOpenGraphTagsFormController.CurrentOpenGraphTags currentOpenGraphTags = currentOpenGraphTagsForm.get();
        if(pageOpenGraphTagsModel.findByTitle(currentOpenGraphTags.title) != null){
            return pageOpenGraphMessageController.titleExists(currentOpenGraphTags.title);
        }
        if(pageOpenGraphTagsModel.findByDescription(currentOpenGraphTags.description) != null){
            return pageOpenGraphMessageController.descriptionExists(currentOpenGraphTags.description);
        }
        if(pageOpenGraphTagsModel.findByUrl(currentOpenGraphTags.url) != null){
            return pageOpenGraphMessageController.urlExists(currentOpenGraphTags.url);
        }
        createTags(page, currentOpenGraphTags);
        if(
                (pageOpenGraphTagsModel.findByTitle(currentOpenGraphTags.title) == null) &&
                        (pageOpenGraphTagsModel.findByDescription(currentOpenGraphTags.description) == null) &&
                        (pageOpenGraphTagsModel.findByUrl(currentOpenGraphTags.url) == null) &&
                        (pageOpenGraphTagsModel.findByPage(page) == null)){
            return pageOpenGraphMessageController.tagsNotCreated();
        }
        return pageOpenGraphMessageController.tagsCreated();
    }

    @NotNull
    private void createTags(
            PageModel page, PageOpenGraphTagsFormController.CurrentOpenGraphTags currentOpenGraphTags
    ) {
        PageOpenGraphTagsModel pageOpenGraphTagsModel = new PageOpenGraphTagsModel();
        pageOpenGraphTagsModel.title = currentOpenGraphTags.title;
        pageOpenGraphTagsModel.description = currentOpenGraphTags.description;
        pageOpenGraphTagsModel.fbAdmins = currentOpenGraphTags.fbAdmins;
        pageOpenGraphTagsModel.page = page;
        pageOpenGraphTagsModel.tagType = currentOpenGraphTags.type;
        pageOpenGraphTagsModel.url = currentOpenGraphTags.url;
        pageOpenGraphTagsModel.creationDate = new Date();
        pageOpenGraphTagsModel.updateDate = new Date();
        pageOpenGraphTagsModel.save();
        page.updateDate = new Date();
        page.update();
    }

    @RequireCSRFCheck
    public Result updateOpenGraphTags(String name){
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
        if(page.pageOpenGraphTags == null){
            return pageOpenGraphMessageController.tagsNotExist();
        }
        Form<PageOpenGraphTagsFormController.CurrentOpenGraphTags> currentOpenGraphTagsForm = Form.form(
                PageOpenGraphTagsFormController.CurrentOpenGraphTags.class
        ).bindFromRequest();
        if(currentOpenGraphTagsForm.hasErrors()){
            return pageOpenGraphMessageController.createFormErrors();
        }
        PageOpenGraphTagsFormController.CurrentOpenGraphTags currentOpenGraphTags = currentOpenGraphTagsForm.get();
        if(
                (pageOpenGraphTagsModel.findByTitle(currentOpenGraphTags.title) != null) &&
                        !currentOpenGraphTags.title.equals(page.pageOpenGraphTags.title)
                ){
            return pageOpenGraphMessageController.titleExists(currentOpenGraphTags.title);
        }
        if((pageOpenGraphTagsModel.findByDescription(currentOpenGraphTags.description) != null) &&
                !currentOpenGraphTags.description.equals(page.pageOpenGraphTags.description)
                ){
            return pageOpenGraphMessageController.descriptionExists(currentOpenGraphTags.description);
        }
        if((pageOpenGraphTagsModel.findByUrl(currentOpenGraphTags.url) != null) &&
                !currentOpenGraphTags.url.equals(page.pageOpenGraphTags.url)
        ){
            return pageOpenGraphMessageController.urlExists(currentOpenGraphTags.url);
        }
        updateTags(page, currentOpenGraphTags);
        if(
                (pageOpenGraphTagsModel.findByTitle(currentOpenGraphTags.title) == null) &&
                        (pageOpenGraphTagsModel.findByDescription(currentOpenGraphTags.description) == null) &&
                        (pageOpenGraphTagsModel.findByUrl(currentOpenGraphTags.url) == null) &&
                        (pageOpenGraphTagsModel.findByPage(page) == null)){
            return pageOpenGraphMessageController.canNotBeUpdated();
        }
        return pageOpenGraphMessageController.hadBeenUpdated();
    }

    @NotNull
    private void updateTags(
            PageModel page, PageOpenGraphTagsFormController.CurrentOpenGraphTags currentOpenGraphTags
    ) {
        page.pageOpenGraphTags.title = currentOpenGraphTags.title;
        page.pageOpenGraphTags.description = currentOpenGraphTags.description;
        page.pageOpenGraphTags.fbAdmins = currentOpenGraphTags.fbAdmins;
        page.pageOpenGraphTags.page = page;
        page.pageOpenGraphTags.tagType = currentOpenGraphTags.type;
        page.pageOpenGraphTags.url = currentOpenGraphTags.url;
        page.pageOpenGraphTags.updateDate = new Date();
        page.pageOpenGraphTags.update();
        page.updateDate = new Date();
        page.update();
    }

}
