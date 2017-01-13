import models.RoleModel;
import models.StatusModel;
import models.UserModel;
import play.Application;
import play.GlobalSettings;

import java.util.Date;

/**
 * Created by astolarski on 07.01.17.
 */
public class Global extends GlobalSettings {

    public StatusModel statusModel = new StatusModel();
    public RoleModel roleModel = new RoleModel();
    public UserModel userModel = new UserModel();

    public void onStart(Application app){

        if(statusModel.rowCount() == 0){
            createStatusModel("active");
            createStatusModel("inactive");
            createStatusModel("frozen");
            createStatusModel("banned");
            createStatusModel("online");
            createStatusModel("offline");
        }

        if(roleModel.rowCount() == 0){

            createRoleModel("guest");
            createRoleModel("user");
            createRoleModel("customer");
            createRoleModel("advertiser");
            createRoleModel("author");
            createRoleModel("editor");
            createRoleModel("moderator");
            createRoleModel("accountManager");
            createRoleModel("administrator");

        }

        if(userModel.rowCount() == 0){
            UserModel userModel = new UserModel();
            userModel.setEmail("r3v@protonmail.ch");
            userModel.setPassword("R3v3l@t104L0A");
            userModel.statuses.add(statusModel.findByName("active"));
            userModel.roles.add(roleModel.findByName("administrator"));
            userModel.creationDate = new Date();
            userModel.updatedDate = new Date();
            userModel.save();
        }

    }

    public void createRoleModel(String name){
        RoleModel roleModel = new RoleModel();
        roleModel.name = name;
        roleModel.statuses.add(statusModel.findByName("active"));
        roleModel.statuses.add(statusModel.findByName("online"));
        roleModel.creationDate = new Date();
        roleModel.updatedDate = new Date();
        roleModel.save();
    }

    public void createStatusModel(String name){
        StatusModel statusModel = new StatusModel();
        statusModel.name = name;
        statusModel.creationDate = new Date();
        statusModel.updatedDate = new Date();
        statusModel.save();
    }

    public void onStop(Application app){

    }

}
