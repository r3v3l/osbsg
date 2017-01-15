package controllers.pages;

import models.core.PageModel;
import models.core.StatusModel;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by astolarski on 15.01.17.
 */
public class PageStatusController extends Controller {

    public List<StatusModel> createStatusList(PageFormController.CurrentPage currentPage){

        List<StatusModel> statuses = new ArrayList<>();
        if((currentPage.active != null) && !currentPage.active.equals("")){
            if(getStatus(currentPage.active) != null) {
                statuses.add(getStatus(currentPage.active));
            }
        }
        if((currentPage.inactive != null) && !currentPage.inactive.equals("")){
            if(getStatus(currentPage.inactive) != null) {
                statuses.add(getStatus(currentPage.inactive));
            }
        }
        if((currentPage.frozen != null) && !currentPage.frozen.equals("")){
            if(getStatus(currentPage.frozen) != null) {
                statuses.add(getStatus(currentPage.frozen));
            }
        }
        if((currentPage.banned != null) && !currentPage.banned.equals("")){
            if(getStatus(currentPage.banned) != null) {
                statuses.add(getStatus(currentPage.banned));
            }
        }
        if((currentPage.online != null) && !currentPage.online.equals("")){
            if(getStatus(currentPage.online) != null) {
                statuses.add(getStatus(currentPage.online));
            }
        }
        if((currentPage.offline != null) && !currentPage.offline.equals("")){
            if(getStatus(currentPage.active) != null) {
                statuses.add(getStatus(currentPage.active));
            }
        }

        return statuses;

    }

    public HashMap<String, StatusModel> getCurrenntStatuses(PageModel pageModel){
        HashMap<String, StatusModel> statuses = new HashMap<>();
        for(StatusModel status: pageModel.statuses){
            statuses.put(status.name, status);
        }
        return statuses;
    }

    public StatusModel getStatus(String name){
        StatusModel statusModel = new StatusModel();
        return statusModel.findByName(name);
    }

}
