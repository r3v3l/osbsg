package controllers;

import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Security;

/**
 * Created by adrian on 21.12.16.
 */
public class SecuredController extends Security.Authenticator {

    @Override
    public String getUsername(Http.Context ctx) {
        return ctx.session().get("username");
    }

    @Override
    public Result onUnauthorized(Http.Context ctx) {
        return unauthorized();
    }

}
