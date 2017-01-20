package repositories;

import controllers.businessCard.BusinessCardFormController;
import models.core.BusinessCardModel;
import models.core.UserModel;

import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class BusinessCardRepo {

    public void createBusinessCard(UserModel currentUser, BusinessCardFormController.BusinessCard businessCard) {
        BusinessCardModel businessCardModel = new BusinessCardModel();
        businessCardModel.user = currentUser;
        businessCardModel.firstname = businessCard.firstname;
        businessCardModel.lastname = businessCard.lastname;
        businessCardModel.email = businessCard.email;
        businessCardModel.phone = businessCard.phone;
        businessCardModel.company = businessCard.company;
        businessCardModel.address = businessCard.address;
        businessCardModel.creationDate = new Date();
        businessCardModel.updateDate = new Date();
        businessCardModel.save();
    }

    public void updateBusinessCard(BusinessCardModel card, BusinessCardFormController.BusinessCard currentCard) {
        card.firstname = currentCard.firstname;
        card.lastname = currentCard.lastname;
        card.email = currentCard.email;
        card.company = currentCard.company;
        card.phone = currentCard.phone;
        card.address = currentCard.address;
        card.updateDate = new Date();
        card.update();
    }

    public void deleteBusinessCard(BusinessCardModel card){

        card.delete();

    }

}
