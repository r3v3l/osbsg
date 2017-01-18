package controllers.pages;

import play.data.validation.Constraints;
import play.mvc.Controller;

/**
 * Created by adrian on 18.01.17.
 */
public class PageOpenGraphTagsFormController extends Controller {

    public static class OpenGraphTags {

    }

    public static class CurrentOpenGraphTags extends OpenGraphTags {

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String title;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String description;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String type;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String url;

        @Constraints.Required
        @Constraints.MaxLength(255)
        public String fbAdmins;

    }

}
