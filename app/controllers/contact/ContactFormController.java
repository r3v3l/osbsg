package controllers.contact;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactFormController extends Controller {

    public static class Contact {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String name;

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String phone;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String message;

    }

}
