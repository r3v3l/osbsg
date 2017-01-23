package controllers.user.signin;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by adrian on 23.01.17.
 */
public class SignInFormController extends Controller {

    public static class SignIn {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String password;

    }

}
