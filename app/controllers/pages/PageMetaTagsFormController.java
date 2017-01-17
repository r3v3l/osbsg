package controllers.pages;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by adrian on 17.01.17.
 */
public class PageMetaTagsFormController extends Controller {

    public static class Tags {}

    public static class CurrentTags extends Tags {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String title;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String description;

        @Constraints.Required
        public String keywords;

        @Constraints.Required
        public String robots;

    }

}
