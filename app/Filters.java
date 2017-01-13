import play.http.DefaultHttpFilters;
import play.mvc.EssentialFilter;
import play.filters.csrf.CSRFFilter;
import javax.inject.Inject;

public class Filters extends DefaultHttpFilters {
    @Inject
    public Filters(CSRFFilter csrfFilter) {
        super(csrfFilter);
    }
}

