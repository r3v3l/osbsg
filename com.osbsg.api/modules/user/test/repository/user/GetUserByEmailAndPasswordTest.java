package repository.user;

import models.core.CoreUserModel;
import org.junit.Test;
import play.test.WithApplication;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 23.01.17.
 */
public class GetUserByEmailAndPasswordTest extends WithApplication implements I_UserGetable{

    private String goodEmail = "adus@cos.cos";
    private String goodPassword = "P@ssw0rd";

    private String badEmail = "adus@cosik.cos";
    private String badPassword = "P@ssw0rd1";

    @Test
    public void findByGoodEmailAndGoodPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(findByEmailAndPassword(goodEmail, goodPassword));
        });
    }

    @Test
    public void findByBadEmailAndGoodPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNull(findByEmailAndPassword(badEmail, goodPassword));
        });
    }

    @Test
    public void findByGoodEmailAndBadPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNull(findByEmailAndPassword(goodEmail, badPassword));
        });
    }

    @Test
    public void findByBadEmailAndBadPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNull(findByEmailAndPassword(badEmail, badPassword));
        });
    }

    @Override
    public CoreUserModel findByEmailAndPassword(String email, String password) {
        GetUserByEmailAndPassword getUserByEmailAndPassword = new GetUserByEmailAndPassword();
        return getUserByEmailAndPassword.findByEmailAndPassword(email, password);
    }
}