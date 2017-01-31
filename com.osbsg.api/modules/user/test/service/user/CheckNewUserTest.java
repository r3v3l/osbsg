package service.user;

import org.junit.Test;
import play.test.WithApplication;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 31.01.17.
 */
public class CheckNewUserTest extends WithApplication {

    private String goodEmail = "adus@cos.cos";
    private String goodPassword = "P@ssw0rd";
    private String badEmail = "cos@cos.cos";
    private String badPassword = "12345678900546547568";

    @Test
    public void checkGoodEmailAndPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CheckNewUser checkNewUser = new CheckNewUser();
            assertTrue(checkNewUser.check(goodEmail, goodPassword));
        });
    }

    @Test
    public void checkBadEmailAndGoodPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CheckNewUser checkNewUser = new CheckNewUser();
            assertFalse(checkNewUser.check(badEmail, goodPassword));
        });
    }

    @Test
    public void checkGoodEmailAndBadPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CheckNewUser checkNewUser = new CheckNewUser();
            assertTrue(checkNewUser.check(goodEmail, badPassword));
        });
    }

}