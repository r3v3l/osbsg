package controllers.user;

import controllers.core.CoreResponseController;
import models.core.RoleModel;
import models.core.StatusModel;
import models.core.UserModel;
import org.jetbrains.annotations.NotNull;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 07.01.17.
 */
public class SignUpController extends Controller {

    private UserModel userModel = new UserModel();

    @AddCSRFToken
    public Result signUp(){
        SignUpErrorController signUpErrorController = new SignUpErrorController();
        return ok(
                views.html.user.signUp.render(
                        signUpErrorController.getMessages()
                )
        );
    }

    @RequireCSRFCheck
    public Result create(){

        Form<UserAccessFormController.SignUp> signUpForm = Form.form(UserAccessFormController.SignUp.class).bindFromRequest();
        if(signUpForm.hasErrors()){
            return createMessage("formError", "Errors occurred. Please try again.");
        }
        UserAccessFormController.SignUp signUp = signUpForm.get();
        if(!signUp.password.equals(signUp.confirmPassword)){
            return createMessage("formWarning", "Passwords mismatch");
        }

        if(userModel.findByEmail(signUp.email) != null){
            return createMessage("formWarning", "Email exists. Please try again andd choose " +
                    "other e-mail address. If it's your e-mail addres, you can use forgot password option.");
        }

        UserModel userModel = createNewUser(signUp);

        if(userModel.findByEmailAndPassword(signUp.email, signUp.password) == null){
            return createMessage("formError", "User has not been created. Please try again.");
        }

        return createMessage("formSuccess", "User has been created.");

    }

    @NotNull
    private UserModel createNewUser(UserAccessFormController.SignUp signUp) {
        UserModel userModel = new UserModel();
        userModel.setEmail(signUp.email);
        userModel.setPassword(signUp.password);
        userModel.roles = createRoles();
        userModel.statuses = createStatuses();
        userModel.creationDate = new Date();
        userModel.updatedDate = new Date();
        userModel.save();
        return userModel;
    }

    @NotNull
    private Result createMessage(String formWarning, String message) {
        return CoreResponseController.createResponse(
                "redirect", formWarning, message,
                "/user/register-new-account"
        );
    }

    public List<RoleModel> createRoles(){
        RoleModel roleModel = new RoleModel();
        List<RoleModel> roles = new ArrayList<>();
        roles.add(roleModel.findByName("user"));
        return roles;
    }

    public List<StatusModel> createStatuses(){
        StatusModel statusModel = new StatusModel();
        List<StatusModel> statuses = new ArrayList<>();
        statuses.add(statusModel.findByName("inactive"));
        return statuses;
    }

}
