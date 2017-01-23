package repository.user;

import models.core.CoreUserModel;

/**
 * Created by adrian on 23.01.17.
 */
public class GetUserByEmailAndPassword implements I_UserGetable {

    private CoreUserModel coreUserModel = new CoreUserModel();

    @Override
    public CoreUserModel findByEmailAndPassword(String email, String password) {
        return coreUserModel.findByEmailAndPassword(email, password);
    }
}
