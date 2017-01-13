package models;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 07.12.16.
 */
public class UserModelTest {

    private String firstEmail = "r3v@protonmail.ch";
    private String secondEmail = "this@this.this";
    private String thirdEmail = "this2@this.this";
    private String fourthEmail = "this3@this.this";
    private String fifthEmail = "thi4@this.this";
    private String sixthEmail = "this5@this.this";
    private String firstPassword = "FantaSprite";

    private int exceptedSize = 6;

    @Test
    public void rowCount() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            RoleModelTest roleModelTest = new RoleModelTest();
            statusModelTest.createStatuses();
            roleModelTest.create();
            create();
            UserModel userModel = new UserModel();
            assertEquals(exceptedSize, userModel.rowCount());
            remove();
            roleModelTest.remove(roleModelTest.createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    @Test
    public void findAll() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            RoleModelTest roleModelTest = new RoleModelTest();
            statusModelTest.createStatuses();
            roleModelTest.create();
            create();
            UserModel userModel = new UserModel();
            List<UserModel> userModels = userModel.findAll();
            assertEquals(exceptedSize, userModels.size());
            HashMap<String, UserModel> users = createMap(userModels);
            assertNotNull(users.get(firstEmail));
            assertNotNull(users.get(secondEmail));
            assertNotNull(users.get(thirdEmail));
            assertNotNull(users.get(fourthEmail));
            assertNotNull(users.get(fifthEmail));
            assertNotNull(users.get(sixthEmail));
            remove();
            roleModelTest.remove(roleModelTest.createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    @Test
    public void findByEmailAndPassword() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            RoleModelTest roleModelTest = new RoleModelTest();
            statusModelTest.createStatuses();
            roleModelTest.create();
            create();
            UserModel userModel = new UserModel();
            assertNotNull(userModel.findByEmailAndPassword(firstEmail, firstPassword));
            assertNotNull(userModel.findByEmailAndPassword(secondEmail, firstPassword));
            assertNotNull(userModel.findByEmailAndPassword(thirdEmail, firstPassword));
            assertNotNull(userModel.findByEmailAndPassword(fourthEmail, firstPassword));
            assertNotNull(userModel.findByEmailAndPassword(fifthEmail, firstPassword));
            assertNotNull(userModel.findByEmailAndPassword(sixthEmail, firstPassword));
            remove();
            roleModelTest.remove(roleModelTest.createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    @Test
    public void findByEmail() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            RoleModelTest roleModelTest = new RoleModelTest();
            statusModelTest.createStatuses();
            roleModelTest.create();
            create();
            UserModel userModel = new UserModel();
            assertNotNull(userModel.findByEmail(firstEmail));
            assertNotNull(userModel.findByEmail(secondEmail));
            assertNotNull(userModel.findByEmail(thirdEmail));
            assertNotNull(userModel.findByEmail(fourthEmail));
            assertNotNull(userModel.findByEmail(fifthEmail));
            assertNotNull(userModel.findByEmail(sixthEmail));
            remove();
            roleModelTest.remove(roleModelTest.createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    public HashMap<String, UserModel> createMap(List<UserModel> userModels){
        HashMap<String, UserModel> users = new HashMap<>();
        for(UserModel user: userModels){
            users.put(user.email, user);
        }
        return users;
    }

    public void create(){
        StatusModel statusModel = new StatusModel();
        RoleModel roleModel = new RoleModel();
        createUser(this.firstEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
        createUser(this.secondEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
        createUser(this.thirdEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
        createUser(this.fourthEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
        createUser(this.fifthEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
        createUser(this.sixthEmail, this.firstPassword, statusModel.findAll(), roleModel.findAll());
    }

    public void remove(){
        UserModel userModel = new UserModel();
        userModel.findByEmail(firstEmail).delete();
        userModel.findByEmail(secondEmail).delete();
        userModel.findByEmail(thirdEmail).delete();
        userModel.findByEmail(fourthEmail).delete();
        userModel.findByEmail(fifthEmail).delete();
        userModel.findByEmail(sixthEmail).delete();
    }

    public void createUser(String email, String password, List<StatusModel> statuses, List<RoleModel> roles){
        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.statuses = statuses;
        userModel.roles = roles;
        userModel.creationDate = new Date();
        userModel.updatedDate = new Date();
        userModel.save();
    }

}