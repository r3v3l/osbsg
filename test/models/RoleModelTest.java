package models;

import models.core.RoleModel;
import models.core.StatusModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static play.test.Helpers.*;

/**
 * Created by adrian on 07.12.16.
 */
public class RoleModelTest {

    public String guest = "guest";
    public String user = "user";
    public String customer = "customer";
    public String administrator = "administrator";

    public int exceptedSize= 4;
    public int exceptedStatusesSize = 6;

    @Test
    public void rowCount() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            statusModelTest.createStatuses();
            create();
            RoleModel roleModel = new RoleModel();
            assertEquals(exceptedSize, roleModel.rowCount());
            remove(createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    @Test
    public void findAll() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            statusModelTest.createStatuses();
            create();
            RoleModel roleModel = new RoleModel();
            List<RoleModel> roles = roleModel.findAll();
            assertEquals(exceptedSize, roles.size());
            HashMap<String, RoleModel> rolesMap = createHashMap(roles);
            assertNotNull(rolesMap.get(guest));
            assertNotNull(rolesMap.get(user));
            assertNotNull(rolesMap.get(customer));
            assertNotNull(rolesMap.get(administrator));
            remove(createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    @Test
    public void findByName() throws Exception {
        running(fakeApplication(inMemoryDatabase("test")), () -> {
            StatusModelTest statusModelTest = new StatusModelTest();
            statusModelTest.createStatuses();
            create();
            RoleModel roleModel = new RoleModel();
            assertNotNull(roleModel.findByName(guest));
            assertNotNull(roleModel.findByName(user));
            assertNotNull(roleModel.findByName(customer));
            assertNotNull(roleModel.findByName(administrator));
            assertEquals(exceptedStatusesSize, roleModel.findByName(guest).statuses.size());
            assertEquals(exceptedStatusesSize, roleModel.findByName(user).statuses.size());
            assertEquals(exceptedStatusesSize, roleModel.findByName(customer).statuses.size());
            assertEquals(exceptedStatusesSize, roleModel.findByName(administrator).statuses.size());
            remove(createRolesList());
            statusModelTest.removeStatuses();
        });
    }

    private HashMap<String, RoleModel> createHashMap(List<RoleModel> roles){
        HashMap<String, RoleModel> rolesMap = new HashMap<>();
        for(RoleModel role: roles){
            rolesMap.put(role.name, role);
        }
        return rolesMap;
    }

    public void create(){
        StatusModel statusModel = new StatusModel();
        createRoles(createRolesList(), statusModel.findAll());
    }

    public List<String> createRolesList(){
        List<String> roleNames = new ArrayList<>();
        roleNames.add(guest);
        roleNames.add(user);
        roleNames.add(customer);
        roleNames.add(administrator);
        return roleNames;
    }

    public void createRoles(List<String> names, List<StatusModel> statuses){
        for(String roleName: names){
            createRole(roleName, statuses);
        }
    }

    private void createRole(String name, List<StatusModel> statuses){
        RoleModel roleModel = new RoleModel();
        roleModel.name = name;
        roleModel.statuses = statuses;
        roleModel.creationDate = new Date();
        roleModel.updatedDate = new Date();
        roleModel.save();
    }

    public void remove(List<String> names){
        for(String role: createRolesList()){
            RoleModel roleModel = new RoleModel();
            roleModel.findByName(role).delete();
        }
    }

}