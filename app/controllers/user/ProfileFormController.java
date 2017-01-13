package controllers.user;

import play.data.validation.Constraints;

/**
 * Created by astolarski on 09.01.17.
 */
public class ProfileFormController {

    public static class UserProfiel {}

    public static class CurrentProfile{

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String firstname;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String lastname;

        @Constraints.Required
        public String biography;

    }

}
