package models.core;

import com.avaje.ebean.Finder;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by adrian on 17.01.17.
 */
@Entity
public class PageMetaTagsModel extends Model {

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

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String description;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Constraints.Required
    public String keywords;

    @Column(nullable = false)
    @Constraints.Required
    public String robots;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, PageMetaTagsModel> find = new Finder<Long, PageMetaTagsModel>(
            Long.class, PageMetaTagsModel.class
    );

    public PageMetaTagsModel findByPage(PageModel page){
        try {
            return find.where().eq("page", page).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

    public PageMetaTagsModel findByTitle(String title){
        try {
            return find.where().eq("title", title).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

    public PageMetaTagsModel findByDescription(String description){
        try {
            return find.where().eq("description", description).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
