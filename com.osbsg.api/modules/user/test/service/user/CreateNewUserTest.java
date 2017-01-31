package service.user;

import controllers.user.signup.SignUpFormController;
import models.core.CoreUserModel;
import org.junit.Test;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 31.01.17.
 */
public class CreateNewUserTest {

    private String newEmail ="example@domain.com";
    private String newPassword = "ExamplePassword";

    private CoreUserModel coreUserModel = new CoreUserModel();

    private SignUpFormController.SignUp signUp = new SignUpFormController.SignUp();

    @Test
    public void create() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CreateNewUser createNewUser = new CreateNewUser();
            signUp.email = newEmail;
            signUp.password = newPassword;
            signUp.confirmPassword = newPassword;
            createNewUser.create(signUp);
            assertNotNull(coreUserModel.findByEmailAndPassword(newEmail, newPassword));
        });
    }

}