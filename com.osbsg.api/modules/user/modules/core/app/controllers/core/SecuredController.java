package controllers.core;

import play.mvc.Http.Context;
import play.mvc.Result;
import play.mvc.Security;
/**
 * Created by adrian on 23.01.17.
 */
public class SecuredController extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx){
        return ctx.session().get("email");
    }

    @Override
    public Result onUnauthorized(Context ctx){
        return unauthorized();
    }

}
