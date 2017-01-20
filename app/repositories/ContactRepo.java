package repositories;

import controllers.contact.ContactFormController;
import models.core.ContactModel;

import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class ContactRepo {

    public void createContact(ContactFormController.Contact contact){
        ContactModel contactModel = new ContactModel();
        contactModel.name = contact.name;
        contactModel.email = contact.email;
        contactModel.phone = contact.phone;
        contactModel.message = contact.message;
        contactModel.response = false;
        contactModel.creationDate = new Date();
        contactModel.updateDate = new Date();
        contactModel.save();
    }

    public void currentContactUpdate(ContactModel currentContact) {
        currentContact.response = true;
        currentContact.updateDate = new Date();
        currentContact.save();
    }

}
