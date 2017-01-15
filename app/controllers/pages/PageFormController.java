package controllers.pages;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 15.01.17.
 */
public class PageFormController extends Controller {

    public static class Page {}

    public static class NewPage extends Page {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String name;

    }

    public static class PageStatus extends NewPage {

        public String active;

        public String inactive;

        public String offline;

        public String online;

        public String frozen;

        public String banned;

    }

    public static class PageRole extends NewPage {

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

    public static class CurrentPage extends Page {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String name;

        public String active;

        public String inactive;

        public String offline;

        public String online;

        public String frozen;

        public String banned;

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


}
