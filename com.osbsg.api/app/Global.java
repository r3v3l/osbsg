import models.core.CoreRoleModel;
import models.core.CoreStatusModel;
import play.Application;
import play.GlobalSettings;
import play.Logger;

import java.util.Date;

/**
 * Created by adrian on 22.01.17.
 */
public class Global extends GlobalSettings {

    private CoreStatusModel coreStatusModel = new CoreStatusModel();
    private CoreRoleModel coreRoleModel = new CoreRoleModel();

    public void onStart(Application app) {
        if(coreStatusModel.rowCount() == 0) {
            createStatuses();
        }
        if(coreRoleModel.rowCount() == 0){
            createRoles();
        }
        Logger.info("Application has started");
    }

    public void createStatuses(){
        Logger.info("Create application statuses ...");
        createStatus("active");
        createStatus("inactive");
        createStatus("frozen");
        createStatus("banned");
        createStatus("offline");
        createStatus("online");
        Logger.info("Statuses have been created.");
    }

    public void createStatus(String name){
        Logger.info("Create status " +name+ "...");
        CoreStatusModel status = new CoreStatusModel();
        status.name = name;
        status.creationDate = new Date();
        status.updateDate = new Date();
        status.save();
        if(coreStatusModel.findByName(name) == null){
            Logger.error("Status " +name+ " can not be created. Try again.");
        }
        Logger.info("Status " +name+ " has been created.");
    }

    public void onStop(Application app) {
        Logger.info("Application shutdown...");
    }

    public void createRoles(){
        Logger.info("Create application roles ...");
        createRole("guest");
        createRole("user");
        createRole("customer");
        createRole("advertiser");
        createRole("author");
        createRole("editor");
        createRole("moderator");
        createRole("accountManager");
        createRole("root");
        Logger.info("Roles have been created.");
    }

    public void createRole(String name){
        Logger.info("Create role " +name+ "...");
        CoreRoleModel coreRoleModel = new CoreRoleModel();
        coreRoleModel.name = name;
        coreRoleModel.statuses.add(coreStatusModel.findByName("active"));
        coreRoleModel.statuses.add(coreStatusModel.findByName("online"));
        coreRoleModel.creationDate = new Date();
        coreRoleModel.updateDate = new Date();
        coreRoleModel.save();
        Logger.info("Role " +name+ " has been created.");
    }

}
