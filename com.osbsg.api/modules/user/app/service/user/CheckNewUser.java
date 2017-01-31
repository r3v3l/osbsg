package service.user;

import models.core.CoreUserModel;
import repository.user.I_UserGetable;

/**
 * Created by adrian on 31.01.17.
 */
public class CheckNewUser implements I_UserCheckable {

    @Override
    public boolean check(String email, String password) {
        CoreUserModel coreUserModel = new CoreUserModel();
        try {
            if(coreUserModel.findByEmailAndPassword(email, password) != null){
                return true;
            }
            return false;
        }catch (NullPointerException e){
            return false;
        }
    }

}
