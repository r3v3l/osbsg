package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 06.12.16.
 */
@Entity
public class RoleModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

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
    public Date updatedDate;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<StatusModel> statuses;

    @ManyToMany(mappedBy="roles")
    public List<UserModel> users;

    public Finder<Long, RoleModel> find = new Finder<Long, RoleModel>(Long.class, RoleModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<RoleModel> findAll(){
        return find.all();
    }

    public RoleModel findByName(String name){
        try {
            return find.where().eq("name", name).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }
}