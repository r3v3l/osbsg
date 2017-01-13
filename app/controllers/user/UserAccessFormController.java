package controllers.user;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 07.01.17.
 */
public class UserAccessFormController extends Controller {

    public static class User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

    }

    public static class Forgot extends User {

    }

    public static class SignIn extends User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String password;

    }

    public static class SignUp extends SignIn {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String confirmPassword;

        //@Constraints.Required
        public String acceptTermOfUse;

        //@Constraints.Required
        public String acceptPrivacyPolicy;

    }

}
