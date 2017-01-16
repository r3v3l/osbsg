package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by astolarski on 16.01.17.
 */
@Entity
public class PageContentModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @OneToOne
    @JoinColumn(name = "page")
    public PageModel page;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Constraints.Required
    public String content;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, PageContentModel> find = new Finder<Long, PageContentModel>(Long.class, PageContentModel.class);

    public PageContentModel findByTitle(String title){
        try {
            return find.where().eq("title", title).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
