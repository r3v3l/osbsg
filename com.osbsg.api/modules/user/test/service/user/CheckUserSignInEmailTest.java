package service.user;

import org.junit.Test;
import play.test.WithApplication;
import service.user.CheckUserSignInEmail;
import service.user.I_EmailCheckable;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 23.01.17.
 */
public class CheckUserSignInEmailTest extends WithApplication implements I_EmailCheckable {

    private String userEmail = "adus@cos.cos";
    private String fakeEmail = "fake@fake.com";

    @Test
    public void checkUserEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertFalse(checkEmail(userEmail));
        });
    }

    @Test
    public void checkFakeEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertTrue(checkEmail(fakeEmail));
        });
    }

    @Override
    public boolean checkEmail(String email) {
        CheckUserSignInEmail checkUserSignInEmail = new CheckUserSignInEmail();
        return checkUserSignInEmail.checkEmail(email);
    }
}