package service.user;

import models.core.CoreUserModel;

/**
 * Created by adrian on 31.01.17.
 */
public class CheckUserSignUpEmail implements I_EmailCheckable {

    @Override
    public boolean checkEmail(String email) {

        try {
            CoreUserModel coreUserModel = new CoreUserModel();
            if(coreUserModel.findByEmail(email) != null){
                return true;
            }
            return false;
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }

    }

}
