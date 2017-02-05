package controllers.user.signup;

import play.data.validation.Constraints;

/**
 * Created by adrian on 31.01.17.
 */
public class SignUpFormController {

    public static class SignUp {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MinLength(12)
        @Constraints.MaxLength(255)
        public String password;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String confirmPassword;

        public String termOfUse;

    }

}
