package models.core;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 11.01.17.
 */
@Entity
public class BusinessCardModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false)
    @ManyToOne
    @Constraints.Required
    public UserModel user;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String firstname;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String lastname;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    @Constraints.Email
    public String email;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String phone;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String company;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String address;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, BusinessCardModel> find = new Finder<Long, BusinessCardModel>(
            Long.class, BusinessCardModel.class
    );

    public List<BusinessCardModel> findAll(){
        return find.all();
    }

    public List<BusinessCardModel> findPage(int page, int size){
        return Ebean.find(BusinessCardModel.class).findPagedList(page, size).getList();
    }

    public List<BusinessCardModel> findAllByUser(UserModel user){
        return find.where().eq("user", user).findList();
    }

    public List<BusinessCardModel> findPageByUser(UserModel user, int page, int size){
        return find.where().eq("user", user).findList();
    }

    public List<BusinessCardModel> findPageByUser(int page, int size){
        return Ebean.find(BusinessCardModel.class).where().eq("user", user).findPagedList(page, size).getList();
    }

    public BusinessCardModel findById(Long id){
        try {
            return find.ref(id);
        }catch (NullPointerException e){
            return null;
        }
    }

    public BusinessCardModel findByIdAndUser(Long id, UserModel user){
        try {
            return find.where().eq("id", id).eq("user", user).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

    public List<BusinessCardModel> findLastFive(UserModel user){
        return find.where().eq("user", user).orderBy("creationDate desc").setMaxRows(5).findList();
    }

}
