package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 10.01.17.
 */
@Entity
public class ContactModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    @Constraints.Required
    public boolean response;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String name;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    @Constraints.Email
    public String email;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String phone;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String message;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    @OneToOne(mappedBy = "contact")
    public ResponseContactModel responseContact;

    public Finder<Long, ContactModel> find = new Finder<Long, ContactModel>(Long.class, ContactModel.class);

    public List<ContactModel> findAll(){
        return find.all();
    }

    public List<ContactModel> findLastFive(){
        return find.where().orderBy("creationDate desc").setMaxRows(5).findList();
    }

    public List<ContactModel> findPaggedList(int page, int size){
        return Ebean.find(ContactModel.class).orderBy("creationDate desc").findPagedList(page, size).getList();
    }

    public ContactModel findById(Long id){
        try {
            return find.ref(id);
        }catch (NullPointerException e){
            return null;
        }
    }

}
