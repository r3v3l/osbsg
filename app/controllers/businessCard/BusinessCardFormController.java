package controllers.businessCard;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 11.01.17.
 */
public class BusinessCardFormController extends Controller {

    public static class Card {}

    public static class BusinessCard extends Card {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String firstname;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String lastname;

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String phone;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String company;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String address;

    }

}
