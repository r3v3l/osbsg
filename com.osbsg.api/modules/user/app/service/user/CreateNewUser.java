package service.user;

import controllers.user.signup.SignUpFormController;
import models.core.CoreRoleModel;
import models.core.CoreStatusModel;
import models.core.CoreUserModel;

import java.util.Date;

/**
 * Created by adrian on 31.01.17.
 */
public class CreateNewUser {

    public void create(SignUpFormController.SignUp signUp){
        CoreRoleModel coreRoleModel = new CoreRoleModel();
        CoreStatusModel coreStatusModel = new CoreStatusModel();
        CoreUserModel coreUserModel = new CoreUserModel();
        coreUserModel.setEmail(signUp.email);
        coreUserModel.setPassword(signUp.password);
        coreUserModel.statuses.add(coreStatusModel.findByName("active"));
        coreUserModel.roles.add(coreRoleModel.findByName("user"));
        coreUserModel.creationDate = new Date();
        coreUserModel.updateDate = new Date();
        coreUserModel.save();

    }

}
