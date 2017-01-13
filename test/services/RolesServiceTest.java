package services;

import models.RoleModel;
import models.StatusModel;
import models.UserModel;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Date;

import static org.junit.Assert.*;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;

/**
 * Created by adrian on 21.12.16.
 */
public class RolesServiceTest extends WithApplication {

    private String active = "active";

    private String user = "user";
    private String customer = "customer";
    private String administrator = "administrator";

    private String email = "r3v@protonmail.ch";
    private String password = "FantaSprite";

    @Test
    public void isCustomer() throws Exception {

        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatus(active);
            createRole(customer, active);
            createUser(active, customer);
            RolesService rolesService = new RolesService();
            UserModel userModel = new UserModel();
            assertTrue(rolesService.isCustomer(userModel.findByEmail(email)));
            deleteUser(email);
            deleteRole(customer);
            deleteStatus(active);
        });

    }

    @Test
    public void isUser() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatus(active);
            createRole(user, active);
            createUser(active, user);
            RolesService rolesService = new RolesService();
            UserModel userModel = new UserModel();
            assertTrue(rolesService.isCustomer(userModel.findByEmail(email)));
            deleteUser(email);
            deleteRole(user);
            deleteStatus(active);
        });
    }

    @Test
    public void isAdmin() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            createStatus(active);
            createRole(administrator, active);
            createUser(active, administrator);
            RolesService rolesService = new RolesService();
            UserModel userModel = new UserModel();
            assertTrue(rolesService.isCustomer(userModel.findByEmail(email)));
            deleteUser(email);
            deleteRole(administrator);
            deleteStatus(active);
        });
    }

    private void createStatus(String statusName){
        StatusModel statusModel = new StatusModel();
        statusModel.name = statusName;
        statusModel.creationDate = new Date();
        statusModel.updatedDate = new Date();
        statusModel.save();
    }

    private void deleteStatus(String statusName){
        StatusModel statusModel = new StatusModel();
        statusModel.findByName(statusName).delete();
    }

    private void createRole(String roleName, String statusName){
        StatusModel statusModel = new StatusModel();
        RoleModel roleModel = new RoleModel();
        roleModel.name = roleName;
        roleModel.statuses.add(statusModel.findByName(active));
        roleModel.creationDate = new Date();
        roleModel.updatedDate = new Date();
        roleModel.save();
    }

    private void deleteRole(String roleName){
        RoleModel roleModel = new RoleModel();
        roleModel.findByName(roleName).delete();
    }

    public void createUser(String statusName, String roleName){

        StatusModel statusModel = new StatusModel();
        RoleModel roleModel = new RoleModel();

        UserModel userModel = new UserModel();
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.statuses.add(statusModel.findByName(active));
        userModel.roles.add(roleModel.findByName(roleName));
        userModel.updatedDate = new Date();
        userModel.creationDate = new Date();
        userModel.save();

    }

    public void deleteUser(String email){
        UserModel userModel = new UserModel();
        userModel.findByEmail(email).delete();
    }

}