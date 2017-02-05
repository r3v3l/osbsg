package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 22.01.17.
 */
@Entity
public class CoreRoleModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String name;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<CoreStatusModel> statuses;

    @ManyToMany(mappedBy="statuses")
    public List<CoreUserModel> users;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm-dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm-dd")
    public Date updateDate;

    public Finder<Long, CoreRoleModel> find = new Finder<Long, CoreRoleModel>(Long.class, CoreRoleModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<CoreRoleModel> findAll(){
        try {
            return find.all();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreRoleModel findById(Long id){
        try {
            return find.ref(id);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreRoleModel findByName(String name){
        try {
            return find.where().eq("name", name).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

}
