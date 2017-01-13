package services;

import models.RoleModel;
import models.UserModel;

import java.util.List;

/**
 * Created by adrian on 21.12.16.
 */
public class RolesService {

    private String administrator = "administrator";
    private String user = "user";
    private String customer = "customer";

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

    public boolean isAdmin(UserModel userModel){

        for(RoleModel role: userModel.roles){
            if(role.name.equals(administrator)){
                return true;
            }
        }
        return false;

    }

}
