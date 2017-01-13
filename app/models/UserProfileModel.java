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
public class UserProfileModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @OneToOne
    @JoinColumn(name = "user")
    public UserModel user;


    @Column(length =255, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String firstname;

    @Column(length = 255, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String lastname;

    @Column(columnDefinition = "TEXT", nullable = false)
    @Constraints.Required
    public String biography;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, UserProfileModel> find = new Finder<Long, UserProfileModel>(
            Long.class, UserProfileModel.class
    );

    public UserProfileModel findByUser(UserModel user){

        try {

            return find.where().eq("user", user).findUnique();

        }catch (NullPointerException e){

            return null;

        }

    }

}
