package models.core;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 22.01.17.
 */
public class CoreStatusModelTest extends WithApplication {

    private CoreStatusModel coreStatusModel = new CoreStatusModel();

    private int expectedSize = 6;

    private String active = "active" ;
    private String inactive = "inactive";
    private String frozen = "frozen";
    private String banned = "banned";
    private String online = "online";
    private String offline = "offline";

    @Test
    public void rowCount() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertEquals(expectedSize, coreStatusModel.rowCount());
        });
    }


    @Test
    public void findById() throws Exception {

    }
    @Test
    public void findAll() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findAll());
            assertEquals(expectedSize, coreStatusModel.findAll().size());
        });
    }

    @Test
    public void findByActiveName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(active));
        });
    }

    @Test
    public void findByInactiveName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(inactive));
        });
    }

    @Test
    public void findByFrozenName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(frozen));
        });
    }

    @Test
    public void findByBannedName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(banned));
        });
    }

    @Test
    public void findByOffline() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(offline));
        });
    }

    @Test
    public void findByOnlineName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            assertNotNull(coreStatusModel.findByName(online));
        });
    }

}