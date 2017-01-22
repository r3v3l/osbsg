package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 22.01.17.
 */
@Entity
public class CoreStatusModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String name;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm-dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm-dd")
    public Date updateDate;

    public Finder<Long, CoreStatusModel> find = new Finder<Long, CoreStatusModel>(Long.class, CoreStatusModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<CoreStatusModel> findAll(){
        try {
            return find.all();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreStatusModel findById(Long id){
        try {
            return find.ref(id);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreStatusModel findByName(String name){
        try {
            return find.where().eq("name", name).findUnique();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

}
