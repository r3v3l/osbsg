package models.core;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 15.01.17.
 */
@Entity
public class PageModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String name;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<StatusModel> statuses;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<RoleModel> roles;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, PageModel> find = new Finder<Long, PageModel>(Long.class, PageModel.class);

    public List<PageModel> findAll(){
        return find.all();
    }

    public List<PageModel> findByPage(int page, int size){
        return Ebean.find(PageModel.class).findPagedList(page, size).getList();
    }

    public PageModel findByName(String name){
        try {
            return find.where().eq("name", name).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
