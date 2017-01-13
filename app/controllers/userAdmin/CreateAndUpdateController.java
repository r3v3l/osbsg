package controllers.userAdmin;

import models.RoleModel;
import models.StatusModel;
import models.UserModel;
import play.mvc.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 09.01.17.
 */
public class CreateAndUpdateController extends Controller {

    private RoleModel roleModel = new RoleModel();
    private StatusModel statusModel = new StatusModel();

    public void createUser(UserAdminFormController.CompleteUser completeUser) {

        UserModel newUser = new UserModel();
        newUser.setEmail(completeUser.email);
        newUser.setPassword(completeUser.password);
        newUser.statuses = generateStatuses(completeUser);
        newUser.roles = generateRoles(completeUser);
        newUser.creationDate = new Date();
        newUser.updatedDate = new Date();
        newUser.save();
    }

    public void updateUser(UserModel currentUser, UserAdminFormController.CompleteUser completeUser){

        if((completeUser.email != null) && !completeUser.equals("")) {
            currentUser.setEmail(completeUser.email);
        }
        if((completeUser.password != null) && !completeUser.password.equals("")) {
            currentUser.setPassword(completeUser.password);
        }
        currentUser.statuses.clear();
        currentUser.statuses = generateStatuses(completeUser);
        currentUser.roles.clear();
        currentUser.roles = generateRoles(completeUser);
        currentUser.updatedDate = new Date();
        currentUser.update();
    }

    private List<RoleModel> generateRoles(UserAdminFormController.CompleteUser completeUser) {

        List<RoleModel> roles = new ArrayList<>();
        if(completeUser.guest != null){
            roles.add(roleModel.findByName("guest"));
        }
        if(completeUser.user != null){
            roles.add(roleModel.findByName("user"));
        }
        if(completeUser.customer != null){
            roles.add(roleModel.findByName("customer"));
        }
        if(completeUser.advertiser != null){
            roles.add(roleModel.findByName("advertiser"));
        }
        if(completeUser.author != null){
            roles.add(roleModel.findByName("author"));
        }
        if(completeUser.editor != null){
            roles.add(roleModel.findByName("editor"));
        }
        if(completeUser.editor != null){
            roles.add(roleModel.findByName("editor"));
        }
        if(completeUser.moderator != null){
            roles.add(roleModel.findByName("moderator"));
        }
        if(completeUser.accountManager != null){
            roles.add(roleModel.findByName("accountManager"));
        }
        if(completeUser.administrator != null){
            roles.add(roleModel.findByName("administrator"));
        }
        return roles;

    }

    private List<StatusModel> generateStatuses(UserAdminFormController.CompleteUser completeUser) {
        List<StatusModel> statues = new ArrayList<>();
        if(completeUser.active != null){
            statues.add(statusModel.findByName("active"));
        }
        if(completeUser.inactive != null){
            statues.add(statusModel.findByName("inactive"));
        }
        if(completeUser.frozen != null){
            statues.add(statusModel.findByName("frozen"));
        }
        if(completeUser.banned != null){
            statues.add(statusModel.findByName("banned"));
        }
        if(completeUser.online != null){
            statues.add(statusModel.findByName("online"));
        }
        if(completeUser.offline != null){
            statues.add(statusModel.findByName("offline"));
        }
        return statues;
    }

}
