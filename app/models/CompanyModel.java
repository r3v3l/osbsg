package models;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by astolarski on 09.01.17.
 */
@Entity
public class CompanyModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @OneToOne
    @JoinColumn(name = "user")
    public UserModel user;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String lawForm;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String country;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String name;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String street;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String city;

    @Column(nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String postalCode;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String taxNumber;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updatedDate;

    public Finder<Long, CompanyModel> find = new Finder<Long, CompanyModel>(Long.class, CompanyModel.class);

    public CompanyModel findByName(String name){
        try {
            return find.where().eq("name", name).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

    public CompanyModel findByTaxNumber(String taxNumber){
        try {
            return find.where().eq("taxNumber", taxNumber).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }


}
