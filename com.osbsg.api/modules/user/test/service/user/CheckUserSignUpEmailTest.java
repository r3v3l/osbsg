package service.user;

import org.junit.Test;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 31.01.17.
 */
public class CheckUserSignUpEmailTest {

    private String goodEmail = "adus@cos.cos";
    private String badEmail = "cos@cos.cos";

    @Test
    public void checkGoodEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CheckUserSignUpEmail checkUserSignUpEmail = new CheckUserSignUpEmail();
            assertTrue(checkUserSignUpEmail.checkEmail(goodEmail));
        });
    }

    @Test
    public void checkBadEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CheckUserSignUpEmail checkUserSignUpEmail = new CheckUserSignUpEmail();
            assertFalse(checkUserSignUpEmail.checkEmail(badEmail));
        });
    }

}