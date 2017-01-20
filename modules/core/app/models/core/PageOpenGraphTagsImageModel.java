package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
@Entity
public class PageOpenGraphTagsImageModel extends Model {

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
    public String name;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, PageOpenGraphTagsImageModel> find = new Finder<Long, PageOpenGraphTagsImageModel>(
            Long.class, PageOpenGraphTagsImageModel.class
    );

    public PageOpenGraphTagsImageModel findByPage(PageModel page){
        try {
            return find.where().eq("page", page).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
