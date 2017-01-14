package models;

import models.core.StatusModel;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 06.12.16.
 */
public class StatusModelTest extends WithApplication {

    private String active = "active";
    private String inactive = "inactive";
    private String frozen = "frozen";
    private String banned = "banned";
    private String offline = "offline";
    private String online = "online";

    private int exceptedResult = 6;

    @Test
    public void rowCountTest() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatuses();
            StatusModel statusModel = new StatusModel();
            assertEquals(exceptedResult, statusModel.rowCount());
            removeStatuses();
        });
    }

    @Test
    public void findAllTest() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatuses();
            StatusModel statusModel = new StatusModel();
            List<StatusModel> statusModels = statusModel.findAll();
            assertEquals(exceptedResult, statusModels.size());
            HashMap<String, StatusModel> statuses = createHashmap(statusModels);
            assertEquals(exceptedResult, statuses.size());
            assertNotNull(statuses.get(active));
            assertNotNull(statuses.get(inactive));
            assertNotNull(statuses.get(frozen));
            assertNotNull(statuses.get(banned));
            assertNotNull(statuses.get(offline));
            assertNotNull(statuses.get(online));
            removeStatuses();
        });
    }

    @Test
    public void findByNameTest() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatuses();
            StatusModel statusModel = new StatusModel();
            assertNotNull(statusModel.findByName(active));
            assertNotNull(statusModel.findByName(inactive));
            assertNotNull(statusModel.findByName(frozen));
            assertNotNull(statusModel.findByName(banned));
            assertNotNull(statusModel.findByName(offline));
            assertNotNull(statusModel.findByName(online));
            removeStatuses();
        });
    }

    private HashMap<String, StatusModel> createHashmap(List<StatusModel> statusModels){
        HashMap<String, StatusModel> statuses = new HashMap<>();
        for(StatusModel status: statusModels){
            statuses.put(status.name, status);
        }
        return statuses;
    }

    public void removeStatuses(){
        remove(active);
        remove(inactive);
        remove(frozen);
        remove(banned);
        remove(offline);
        remove(online);
    }

    public void createStatuses(){
        create(active);
        create(inactive);
        create(frozen);
        create(banned);
        create(offline);
        create(online);
    }

    private void remove(String name){
        StatusModel statusModel = new StatusModel();
        statusModel.findByName(name).delete();
    }

    private void create(String name){
        StatusModel statusModel = new StatusModel();
        statusModel.name = name;
        statusModel.creationDate = new Date();
        statusModel.updatedDate = new Date();
        statusModel.save();
    }

}