package models;

import com.avaje.ebean.Ebean;
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
 * Created by astolarski on 11.01.17.
 */
@Entity
public class NewsletterModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    @Constraints.Email
    public String email;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, NewsletterModel> find = new Finder<Long, NewsletterModel>(Long.class, NewsletterModel.class);

    public List<NewsletterModel> findAll(){
        return find.all();
    }

    public List<NewsletterModel> findPage(int page, int size){
        return Ebean.find(NewsletterModel.class).orderBy("creationDate desc").findPagedList(page, size).getList();
    }

    public NewsletterModel findByEmail(String email){
        try {
            return find.where().eq("email", email).findUnique();
        }catch (NullPointerException e){
            return null;
        }
    }

}
