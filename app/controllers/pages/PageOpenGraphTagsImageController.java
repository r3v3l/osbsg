package controllers.pages;

import controllers.HomeController;
import controllers.core.FileUploadController;
import models.core.PageModel;
import models.core.PageOpenGraphTagsImageModel;
import models.core.UserModel;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class PageOpenGraphTagsImageController extends Controller {

    private PageMessageController pageMessageController = new PageMessageController();
    private UserModel userModel = new UserModel();
    private PageModel pageModel = new PageModel();
    private PageOpenGraphTagsImageModel pageOpenGraphTagsImageModel = new PageOpenGraphTagsImageModel();
    private PageOpenGraphTagsImageMessageController pageOpenGraphTagsImageMessageController =
            new PageOpenGraphTagsImageMessageController();

    @RequireCSRFCheck
    public Result createImage(String name) throws IOException {

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

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if(picture == null){
            return pageOpenGraphTagsImageMessageController.canNotCreated(null);
        }

        String fileName = picture.getFilename();
        String contentType = picture.getContentType();
        File file = picture.getFile();

        FileUploadController fileUploadController = new FileUploadController();
        if(!fileUploadController.upload(
                "public/images/users/"+page.name, fileName, file)
                ){
            return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
        }

        PageOpenGraphTagsImageModel pageOpenGraphTagsImageModel = new PageOpenGraphTagsImageModel();
        pageOpenGraphTagsImageModel.page = page;
        pageOpenGraphTagsImageModel.name = fileName;
        pageOpenGraphTagsImageModel.creationDate = new Date();
        pageOpenGraphTagsImageModel.updateDate = new Date();
        pageOpenGraphTagsImageModel.save();

        if(pageOpenGraphTagsImageModel.findByPage(pageModel.findByName(name)).name == null){
            return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
        }

        return pageOpenGraphTagsImageMessageController.hasBeenCreated(fileName);
    }

    @RequireCSRFCheck
    public Result updateImage(String name) throws IOException {

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

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if(picture == null){
            return pageOpenGraphTagsImageMessageController.canNotCreated(null);
        }

        String fileName = picture.getFilename();
        String contentType = picture.getContentType();
        File file = picture.getFile();

        FileUploadController fileUploadController = new FileUploadController();
        if(!fileUploadController.remove(
                "public/images/users/"+page.name, page.pageOpenGraphTagsImageModel.name)
                ){
            return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
        }
        if(!fileUploadController.upload(
                "public/images/users/"+page.pageOpenGraphTagsImageModel.page, fileName, file)
                ){
            return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
        }

        page.pageOpenGraphTagsImageModel.name = fileName;
        page.pageOpenGraphTagsImageModel.updateDate = new Date();
        page.update();

        if(pageOpenGraphTagsImageModel.findByPage(pageModel.findByName(name)).name == null){
            return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
        }
        return pageOpenGraphTagsImageMessageController.canNotCreated(fileName);
    }

}
