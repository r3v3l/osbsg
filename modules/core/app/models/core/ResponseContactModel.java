package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by astolarski on 10.01.17.
 */
@Entity
public class ResponseContactModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @OneToOne
    @JoinColumn(name = "contact")
    public ContactModel contact;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Constraints.Required
    public String response;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date creationDate;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date updateDate;

    public Finder<Long, ResponseContactModel> find = new Finder<Long, ResponseContactModel>(
            Long.class, ResponseContactModel.class
    );

}
