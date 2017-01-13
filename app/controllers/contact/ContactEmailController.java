package controllers.contact;

import com.google.inject.Inject;
import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;
import play.mvc.Controller;

/**
 * Created by astolarski on 10.01.17.
 */
public class ContactEmailController extends Controller {

    @Inject
    MailerClient mailerClient;

    public void sendMessage(String userEmail, String customerEmail, String response){

        String cid = "1234";
        Email email = new Email()
                .setSubject("Simple email").setFrom(userEmail).addTo(customerEmail)
                .setBodyText(response)
                .setBodyHtml("<html><body><p>An <b>html</b> message with cid <img src=\"cid:" + cid + "\"></p></body></html>");
        mailerClient.send(email);

    }

}
