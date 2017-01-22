package models.core;

import org.junit.Test;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 22.01.17.
 */
public class CoreUserModelTest {

    private int exceptedUserSize = 1;
    private int exceptedStatusSize = 2;
    private int exceptedRolesSize = 9;
    private String email = "adus@cos.cos";
    private String password = "P@ssw0rd";

    private CoreUserModel coreUserModel = new CoreUserModel();

    @Test
    public void rowCount() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertEquals(exceptedUserSize, coreUserModel.rowCount());
        });
    }

    @Test
    public void findAll() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreUserModel.findAll());
            assertEquals(exceptedUserSize, coreUserModel.findAll().size());
        });
    }

    @Test
    public void findByEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CoreUserModel currentUser = coreUserModel.findByEmail(email);
            assertNotNull(currentUser);
            assertEquals(exceptedStatusSize, currentUser.statuses.size());
            assertEquals(exceptedRolesSize, currentUser.roles.size());
        });
    }

    @Test
    public void findByEmailAndPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            CoreUserModel currentUser = coreUserModel.findByEmailAndPassword(email, password);
            assertNotNull(currentUser);
            assertEquals(exceptedStatusSize, currentUser.statuses.size());
            assertEquals(exceptedRolesSize, currentUser.roles.size());
        });
    }

}