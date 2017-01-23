package service.user;

import models.core.CoreUserModel;
import service.user.I_EmailCheckable;

/**
 * Created by adrian on 23.01.17.
 */
public class CheckUserSignInEmail implements I_EmailCheckable{

    private CoreUserModel coreUserModel = new CoreUserModel();

    @Override
    public boolean checkEmail(String email) {
        try {
            CoreUserModel currentUser = coreUserModel.findByEmail(email);
            if (currentUser == null) {
                return true;
            }
            return false;
        }catch (NullPointerException e){
            e.printStackTrace();
            return false;
        }
    }

}
