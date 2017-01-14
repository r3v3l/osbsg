package services.core;

import models.core.RoleModel;
import models.core.UserModel;

/**
 * Created by adrian on 21.12.16.
 */
public class RolesService {

    private String administrator = "administrator";
    private String user = "user";
    private String customer = "customer";
    private String advertiser = "advertiser";
    private String author = "author";
    private String editor = "editor";
    private String moderator = "moderator";
    private String accountManager = "accountManager";

    public boolean isCustomer(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(customer)){
                return true;
            }
        }
        return false;

    }

    public boolean isUser(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(user)){
                return true;
            }
        }
        return false;

    }

    public boolean isAdvertiser(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(advertiser)){
                return true;
            }
        }
        return false;

    }

    public boolean isAuthor(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(author)){
                return true;
            }
        }
        return false;

    }

    public boolean isEditor(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(editor)){
                return true;
            }
        }
        return false;

    }

    public boolean isModerator(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(moderator)){
                return true;
            }
        }
        return false;

    }

    public boolean isAccountManager(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(accountManager)){
                return true;
            }
        }
        return false;

    }

    public boolean isAdmin(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(administrator)){
                return true;
            }
        }
        return false;

    }

}
