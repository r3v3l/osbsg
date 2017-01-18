package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by adrian on 17.01.17.
 */
@Entity
public class PageOpenGraphTagsModel extends Model {

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

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String tagType;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String url;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String fbAdmins;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, PageOpenGraphTagsModel> find = new Finder<Long, PageOpenGraphTagsModel>(
            Long.class, PageOpenGraphTagsModel.class
    );

    public PageOpenGraphTagsModel findByPage(PageModel page){
        try {
            return find.where().eq("page", page).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public PageOpenGraphTagsModel findByTitle(String title){
        try {
            return find.where().eq("title", title).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public PageOpenGraphTagsModel findByDescription(String description){
        try {
            return find.where().eq("description", description).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public PageOpenGraphTagsModel findByUrl(String url){
        try {
            return find.where().eq("url", url).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

}
