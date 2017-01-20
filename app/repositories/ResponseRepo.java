package repositories;

import controllers.contact.ResponseContactController;
import models.core.ContactModel;
import models.core.ResponseContactModel;

import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class ResponseRepo {

    public void createResponse(ContactModel currentContact, ResponseContactController.ContactResponse contactResponse) {
        ResponseContactModel responseContactModel = new ResponseContactModel();
        responseContactModel.response = contactResponse.response;
        responseContactModel.contact = currentContact;
        responseContactModel.creationDate = new Date();
        responseContactModel.updateDate = new Date();
        responseContactModel.save();
    }

}
