package controllers.pages;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by astolarski on 16.01.17.
 */
public class PageContentFormController extends Controller {

    public static class Content {

    }

    public static class CurrentContent extends Content {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String title;

        @Constraints.Required
        public String content;

    }

}
