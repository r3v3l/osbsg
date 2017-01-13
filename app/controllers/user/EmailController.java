package controllers.user;

import com.google.inject.Inject;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Controller;

/**
 * Created by astolarski on 07.01.17.
 */
public class EmailController extends Controller {

    @Inject
    MailerClient mailerClient;

    public void sendRegistrationEmail(String email){

        String cid = "1234";
        Email message = new Email()
                .setSubject("Simple email").setFrom(email).addTo(email)
                .setBodyText("A text message")
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
                mailerClient.send(message);

    }

    public void sendForgotPasswordMessage(String email, String password){

        String cid = "1234";
        Email message = new Email()
                .setSubject("Simple email").setFrom("Mister FROM <from@email.com>").addTo(email)
                .setBodyText("Password: " + password)
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        mailerClient.send(message);

    }

}
