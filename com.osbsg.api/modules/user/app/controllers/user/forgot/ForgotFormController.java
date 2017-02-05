package controllers.user.forgot;

import play.data.validation.Constraints;

/**
 * Created by adrian on 05.02.17.
 */
public class ForgotFormController extends ForgotController {

    public static class Forgot {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

    }

}
