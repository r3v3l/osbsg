package controllers.user;

import controllers.core.FileUploadController;
import models.core.*;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import services.core.RolesService;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 09.01.17.
 */
public class UserProfileController extends Controller {

    private UserProfileMessagesController userProfileMessagesController = new UserProfileMessagesController();
    private BusinessCardModel businessCardModel = new BusinessCardModel();
    private UserModel userModel = new UserModel();

    @AddCSRFToken
    public Result getProfile(){

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        UserProfileFormMessageController userProfileFormMessageController = new UserProfileFormMessageController();
        return ok(
            views.html.user.profile.render(
                    currentUser, userProfileFormMessageController.createMessages(), getContacts(currentUser),
                    businessCardModel.findLastFive(currentUser)
            )
        );
    }

    private List<ContactModel> getContacts(UserModel currentUser){
        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(currentUser)){
            ContactModel contactModel = new ContactModel();
            return contactModel.findLastFive();
        } else {
            return null;
        }
    }

    @RequireCSRFCheck
    public Result createProfile(){

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Form<ProfileFormController.CurrentProfile> profileForm = Form.form(ProfileFormController.CurrentProfile.class)
                .bindFromRequest();
        if(profileForm.hasErrors()){
            return userProfileMessagesController.userProfileFormError();
        }

        ProfileFormController.CurrentProfile profile = profileForm.get();

        UserProfileModel userProfile = new UserProfileModel();
        userProfile.user = currentUser;
        userProfile.firstname = profile.firstname;
        userProfile.lastname = profile.lastname;
        userProfile.biography = profile.biography;
        userProfile.creationDate = new Date();
        userProfile.updateDate = new Date();
        userProfile.save();

        return userProfileMessagesController.userProfieHasBeenUpdated();

    }

    @RequireCSRFCheck
    public Result updateProfile(){

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Form<ProfileFormController.CurrentProfile> profileForm = Form.form(ProfileFormController.CurrentProfile.class)
                .bindFromRequest();
        if(profileForm.hasErrors()){
            return userProfileMessagesController.userProfileFormError();
        }

        ProfileFormController.CurrentProfile profile = profileForm.get();

        if(!profile.firstname.equals("") && (profile.firstname != null)){
            currentUser.userProfile.firstname = profile.firstname;
        }

        if(!profile.biography.equals("") && (profile.biography != null)){
            currentUser.userProfile.biography = profile.biography;
        }

        if(!profile.biography.equals("") && (profile.biography != null)){
            currentUser.userProfile.biography = profile.biography;
        }

        currentUser.userProfile.updateDate = new Date();
        currentUser.userProfile.update();

        return userProfileMessagesController.userProfieHasBeenUpdated();

    }

    @RequireCSRFCheck
    public Result createPhoto() throws IOException {

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if(picture == null){
            return userProfileMessagesController.userProfilePhotoFormError();
        }

        String fileName = picture.getFilename();
        String contentType = picture.getContentType();
        File file = picture.getFile();

        FileUploadController fileUploadController = new FileUploadController();
        if(!fileUploadController.upload(
                "public/images/users/"+currentUser.id, fileName, file)
                ){
            return userProfileMessagesController.userProfilePhotoFormError();
        }

        UserProfilePhotoModel userProfilePhoto = new UserProfilePhotoModel();
        userProfilePhoto.user = currentUser;
        userProfilePhoto.photo = fileName;
        userProfilePhoto.creationDate = new Date();
        userProfilePhoto.updateDate = new Date();
        userProfilePhoto.save();

        return userProfileMessagesController.userProfilePhotoHasBeenUpdated();

    }

    @RequireCSRFCheck
    public Result updatePhoto() throws IOException {

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Http.MultipartFormData<File> body = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart<File> picture = body.getFile("picture");
        if(picture == null){
            return ok();
        }

        String fileName = picture.getFilename();
        String contentType = picture.getContentType();
        File file = picture.getFile();

        FileUploadController fileUploadController = new FileUploadController();
        if(!fileUploadController.remove(
                "public/images/users/"+currentUser.id, currentUser.userProfilePhoto.photo)
                ){
            return userProfileMessagesController.userProfilePhotoFormError();
        }
        if(!fileUploadController.upload(
                "public/images/users/"+currentUser.id, fileName, file)
                ){
            return userProfileMessagesController.userProfilePhotoFormError();
        }

        currentUser.userProfilePhoto.user = currentUser;
        currentUser.userProfilePhoto.photo = fileName;
        currentUser.userProfilePhoto.creationDate = new Date();
        currentUser.userProfilePhoto.updateDate = new Date();
        currentUser.userProfilePhoto.update();

        return userProfileMessagesController.userProfilePhotoHasBeenUpdated();

    }

}
