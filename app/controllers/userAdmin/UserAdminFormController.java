package controllers.userAdmin;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 08.01.17.
 */
public class UserAdminFormController extends Controller {

    public static class User {}

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

    public static class UserRoles extends User {

        public String guest;

        public String user;

        public String customer;

        public String advertiser;

        public String author;

        public String editor;

        public String moderator;

        public String accountManager;

        public String administrator;

    }

    public static class UserStatuses extends User {

        public String active;

        public String inactive;

        public String frozen;

        public String banned;

        public String online;

        public String offline;

    }

    public static class CompleteUser extends User {

        @Constraints.Required
        @Constraints.MaxLength(255)
        @Constraints.Email
        public String email;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String password;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String confirmPassword;

        public String guest;

        public String user;

        public String customer;

        public String advertiser;

        public String author;

        public String editor;

        public String moderator;

        public String accountManager;

        public String administrator;

        public String active;

        public String inactive;

        public String frozen;

        public String banned;

        public String online;

        public String offline;

    }

}
