package models;

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
public class StatusModel extends Model {

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

    @ManyToMany(mappedBy="statuses")
    public List<RoleModel> roles;

    @ManyToMany(mappedBy="statuses")
    public List<UserModel> users;

    public Finder<Long, StatusModel> find = new Finder<Long, StatusModel>(Long.class, StatusModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<StatusModel> findAll(){
        return find.all();
    }

    public StatusModel findByName(String name){
        StatusModel statusModel = new StatusModel();
        try {
            return find.where().eq("name", name).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
