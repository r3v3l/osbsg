package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Model;
import com.avaje.ebean.PagedList;
import com.avaje.ebean.annotation.JsonIgnore;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 07.12.16.
 */
@Entity
public class UserModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(unique = true, nullable = false)
    @Constraints.Required
    @Constraints.MaxLength(255)
    @Constraints.Email
    public String email;

    @JsonIgnore
    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

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

    @ManyToMany(cascade = CascadeType.ALL)
    public List<RoleModel> roles;

    @OneToOne(mappedBy = "user")
    public UserProfileModel userProfile;

    @OneToOne(mappedBy = "user")
    public UserProfilePhotoModel userProfilePhoto;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public List<BusinessCardModel> businessCards;

    @OneToOne(mappedBy = "user")
    public CompanyModel company;

    public void setPassword(String password){
        this.shaPassword = getSha512(password);
    }

    public void setEmail(String email){
        this.email = email.toLowerCase();
    }

    public byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Finder<Long, UserModel> find = new Finder<Long, UserModel>(Long.class, UserModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<UserModel> findAll(){
        return find.all();
    }

    public List<UserModel> findPage(int page, int size){
        return Ebean.find(UserModel.class).findPagedList(page, size).getList();
    }

    public UserModel findByEmailAndPassword(String email, String password){
        try {
            return find.where().eq("email", email.toLowerCase()).eq("shaPassword", getSha512(password)).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

    public UserModel findByEmail(String email){
        try {
            return find.where().eq("email", email).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
