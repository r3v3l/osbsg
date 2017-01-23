package repository.user;

import models.core.CoreUserModel;

/**
 * Created by adrian on 23.01.17.
 */
public interface I_UserGetable {

    CoreUserModel findByEmailAndPassword(String email, String password);

}
