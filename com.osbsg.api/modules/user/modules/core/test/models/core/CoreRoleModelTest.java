package models.core;

import org.junit.Test;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 22.01.17.
 */
public class CoreRoleModelTest {

    private int exceptedSize = 9;
    private int statusesExceptedSize = 2;

    private String guest = "guest";
    private String user = "user";
    private String customer = "customer";
    private String advertiser = "advertiser";
    private String author = "author";
    private String editor = "editor";
    private String moderator = "moderator";
    private String accountManager = "accountManager";
    private String root = "root";

    private CoreRoleModel coreRoleModel = new CoreRoleModel();

    @Test
    public void rowCount() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertEquals(exceptedSize, coreRoleModel.rowCount());
        });
    }

    @Test
    public void findAll() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findAll());
            assertEquals(exceptedSize, coreRoleModel.findAll().size());
        });
    }

    @Test
    public void findByGuestName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(guest));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(guest).statuses.size());
        });
    }

    @Test
    public void findByUserName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(user));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(user).statuses.size());
        });
    }

    @Test
    public void findByCustomerName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(customer));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(customer).statuses.size());
        });
    }

    @Test
    public void findByAdvertiserName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(advertiser));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(advertiser).statuses.size());
        });
    }

    @Test
    public void findByAuthorName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(author));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(author).statuses.size());
        });
    }

    @Test
    public void findByEditorName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(editor));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(editor).statuses.size());
        });
    }

    @Test
    public void findByModeratorName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(moderator));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(moderator).statuses.size());
        });
    }

    @Test
    public void findByAccountManagerName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(accountManager));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(accountManager).statuses.size());
        });
    }

    @Test
    public void findByRootName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreRoleModel.findByName(root));
            assertEquals(statusesExceptedSize, coreRoleModel.findByName(root).statuses.size());
        });
    }

}