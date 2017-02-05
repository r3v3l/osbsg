package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

/**
 * Created by adrian on 22.01.17.
 */
@Entity
public class CoreUserModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    @Constraints.Email
    public String email;

    @Column(length = 64, nullable = false)
    private byte[] shaPassword;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<CoreStatusModel> statuses;

    @ManyToMany(cascade = CascadeType.ALL)
    public List<CoreRoleModel> roles;

    public void setPassword(String password) {
        this.shaPassword = getSha512(password);
    }

    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }

    public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public Finder<Long, CoreUserModel> find = new Finder<Long, CoreUserModel>(Long.class, CoreUserModel.class);

    public int rowCount(){
        return find.findRowCount();
    }

    public List<CoreUserModel> findAll(){
        try {
            return find.all();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreUserModel findById(Long id){
        try {
            return find.ref(id);
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreUserModel findByEmail(String email){
        try {
            return find.where().eq("email", email).findUnique();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    public CoreUserModel findByEmailAndPassword(String email, String password){
        try {
            return find.where().eq("email", email.toLowerCase()).eq("shaPassword", getSha512(password)).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
