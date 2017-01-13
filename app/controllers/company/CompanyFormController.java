package controllers.company;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 09.01.17.
 */
public class CompanyFormController extends Controller {

    public static class Company {

    }

    public static class CurrentCompany extends Company {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String lawForm;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String country;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String name;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String street;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String city;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String postalCode;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String taxNumber;

    }

}
