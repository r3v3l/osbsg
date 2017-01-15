package controllers.pages;

import models.core.PageModel;
import models.core.RoleModel;
import models.core.UserModel;
import play.mvc.Controller;
import services.core.RolesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by astolarski on 15.01.17.
 */
public class PageRoleController extends Controller {

    public List<RoleModel> getRoles(PageFormController.CurrentPage currentPage){

        List<RoleModel> roles = new ArrayList<>();

        if((currentPage.guest != null) && !currentPage.guest.equals("")){
            if(getRole(currentPage.guest) != null){
                roles.add(getRole(currentPage.guest));
            }
        }
        if((currentPage.user != null) && !currentPage.user.equals("")){
            if(getRole(currentPage.user) != null){
                roles.add(getRole(currentPage.user));
            }
        }
        if((currentPage.customer != null) && !currentPage.customer.equals("")){
            if(getRole(currentPage.customer) != null){
                roles.add(getRole(currentPage.customer));
            }
        }
        if((currentPage.advertiser != null) && !currentPage.advertiser.equals("")){
            if(getRole(currentPage.advertiser) != null){
                roles.add(getRole(currentPage.advertiser));
            }
        }
        if((currentPage.author != null) && !currentPage.author.equals("")){
            if(getRole(currentPage.author) != null){
                roles.add(getRole(currentPage.author));
            }
        }
        if((currentPage.editor != null) && !currentPage.editor.equals("")){
            if(getRole(currentPage.editor) != null){
                roles.add(getRole(currentPage.editor));
            }
        }
        if((currentPage.moderator != null) && !currentPage.moderator.equals("")){
            if(getRole(currentPage.moderator) != null){
                roles.add(getRole(currentPage.moderator));
            }
        }
        if((currentPage.accountManager != null) && !currentPage.accountManager.equals("")){
            if(getRole(currentPage.accountManager) != null){
                roles.add(getRole(currentPage.accountManager));
            }
        }
        if((currentPage.administrator != null) && !currentPage.administrator.equals("")){
            if(getRole(currentPage.administrator) != null){
                roles.add(getRole(currentPage.administrator));
            }
        }

        return roles;

    }

    public RoleModel getRole(String name){
        RoleModel roleModel = new RoleModel();
        return roleModel.findByName(name);
    }

    public static boolean checkUserRole(UserModel user){
        RolesService rolesService = new RolesService();
        if(
                rolesService.isAuthor(user) || rolesService.isEditor(user) || rolesService.isModerator(user)
                        || rolesService.isAccountManager(user) || rolesService.isAdmin(user)
                ){
            return true;
        }
        return false;
    }

    public HashMap<String, RoleModel> getCurrentRoles(PageModel pageModel){
        HashMap<String, RoleModel> roles = new HashMap<>();
        for(RoleModel role: pageModel.roles){
            roles.put(role.name, role);
        }
        return roles;
    }

}
