package models.core;

import com.avaje.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by adrian on 21.12.16.
 */
@Entity
public class ActionLogModel extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = true)
    @Constraints.Required
    public UserModel userModel;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String remoteAddress;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String currentHost;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String currentMethod;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String currentPath;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String currentUri;

    @Column(nullable = false, length = 255)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String currentVersion;

    @Column(nullable = false, columnDefinition = "TEXT")
    @Constraints.Required
    public String userMessage;

    @Column(name="creationDate")
    @Constraints.Required
    @Formats.DateTime(pattern="dd/MM/yyyy")
    public Date creationDate;

}
