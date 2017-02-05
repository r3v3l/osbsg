package service.user;

import play.libs.mailer.Email;
import play.libs.mailer.MailerClient;

import javax.inject.Inject;


/**
 * Created by adrian on 31.01.17.
 */
public class SendNewPassword {

    @Inject
    MailerClient mailerClient;

    public void sendEmail(String currentEmail, String password){
          Email email = new Email().setSubject("Nowe hasło")
                  .setFrom("kontakt@osbsg.com")
                  .addTo(currentEmail)
                  .setBodyText(
                          "Twoje nowe hasło to: " +password+
                                  ". Pamiętaj o tym, żeby je zmienić po pierwszym użyciu."
                  );
          mailerClient.send(email);
    }

}
