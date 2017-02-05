package service.user;

import models.core.CoreUserModel;

import java.util.Date;

/**
 * Created by adrian on 31.01.17.
 */
public class UpdatePassword {

    private CoreUserModel coreUserModel = new CoreUserModel();

    public void updatePassword(String email, String password){

        CoreUserModel currentUser = coreUserModel.findByEmail(email);
        if(currentUser != null) {
            currentUser.setPassword(password);
            currentUser.updateDate = new Date();
            currentUser.save();
        }

    }

}
