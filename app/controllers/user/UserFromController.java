package controllers.user;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 08.01.17.
 */
public class UserFromController extends Controller {

    public static class User { }

    public static class UserEmail extends User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

    }

    public static class UserPassword extends User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String password;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String confirmPassword;

    }

}
