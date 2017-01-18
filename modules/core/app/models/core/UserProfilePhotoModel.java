package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by astolarski on 09.01.17.
 */
@Entity
public class UserProfilePhotoModel extends Model {

    @Id
    @GeneratedValue
    public Long id;


    @Column(nullable = false, unique = true)
    @OneToOne
    @JoinColumn(name = "user")
    public UserModel user;

    @Constraints.Required
    @Constraints.MaxLength(255)
    public String photo;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, UserProfilePhotoModel> find = new Finder<Long, UserProfilePhotoModel>(
            Long.class, UserProfilePhotoModel.class
    );

    public UserProfilePhotoModel findByUser(UserModel user){

        try{
            return find.where().eq("user", user).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }

    }

}
