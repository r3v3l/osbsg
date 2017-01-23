package controllers.user;

import play.api.mvc.*;

/**
 * Created by adrian on 23.01.17.
 */

public class Assets {
    public static Action<AnyContent> at(String path, String file) {
        return controllers.user.Assets.at(path, file);
    }
}