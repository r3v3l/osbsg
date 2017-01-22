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

    public void onStart(Application app) {
        if(coreStatusModel.rowCount() == 0) {
            createStatuses();
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


}
