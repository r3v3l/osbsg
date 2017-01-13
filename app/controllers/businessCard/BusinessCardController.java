package controllers.businessCard;

import models.BusinessCardModel;
import models.ContactModel;
import models.UserModel;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.RolesService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 11.01.17.
 */
public class BusinessCardController extends Controller {

    private UserModel userModel = new UserModel();
    private ContactModel contactModel = new ContactModel();
    private BusinessCardModel businessCardModel = new BusinessCardModel();
    private BusinessCardFormMessagesController messages = new BusinessCardFormMessagesController();
    private BusinessCardMessageController businessCardMessageController = new BusinessCardMessageController();

    private List<BusinessCardModel> businessCard = new ArrayList<>();

    private RolesService rolesService = new RolesService();

    @AddCSRFToken
    public Result getAll(){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel curentUser = userModel.findByEmail(session().get("email"));
        if(curentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        if(rolesService.isAdmin(curentUser)){
            businessCard = businessCardModel.findAll();
        }else {
            businessCard = businessCardModel.findAllByUser(curentUser);
        }
        return ok(
                views.html.businessCard.all.render(
                        curentUser, messages.createMessages(), contactModel.findLastFive(), businessCard,
                        businessCardModel.findLastFive(curentUser)
                )
        );
    }

    @AddCSRFToken
    public Result getPage(int page, int size){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel curentUser = userModel.findByEmail(session().get("email"));
        if(curentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        if(rolesService.isAdmin(curentUser)){
            businessCard = businessCardModel.findPage(page, size);
        }else {
            businessCard = businessCardModel.findPageByUser(curentUser, page, size);
        }
        return ok(
                views.html.businessCard.all.render(
                        curentUser, messages.createMessages(), contactModel.findLastFive(), businessCard,
                        businessCardModel.findLastFive(curentUser)
                )
        );
    }

    @AddCSRFToken
    public Result getCard(Long id){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel curentUser = userModel.findByEmail(session().get("email"));
        if(curentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        BusinessCardModel card = new BusinessCardModel();
        if(rolesService.isAdmin(curentUser)){
            card = businessCardModel.findById(id);
        }else {
            card = businessCardModel.findByIdAndUser(id, curentUser);
        }
        if(card == null){
            return businessCardMessageController.cardNotFound();
        }
        return ok(
                views.html.businessCard.getCard.render(
                        curentUser, messages.createMessages(), contactModel.findLastFive(),
                        businessCardModel.findLastFive(curentUser), businessCardModel.findById(id)
                )
        );

    }

    @AddCSRFToken
    public Result addCard(){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }

        return ok(views.html.businessCard.addCard.render(
                currentUser, messages.createMessages(), contactModel.findLastFive(),
                businessCardModel.findLastFive(currentUser))
        );
    }

    @RequireCSRFCheck
    public Result createCard(){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }

        Form<BusinessCardFormController.BusinessCard> businessCardForm = Form
                .form(BusinessCardFormController.BusinessCard.class).bindFromRequest();
        if(businessCardForm.hasErrors()){
            return businessCardMessageController.canNotCreateCard();
        }

        BusinessCardFormController.BusinessCard businessCard = businessCardForm.get();

        createBusinessCard(currentUser, businessCard);

        return businessCardMessageController.hasBeenCreated();
    }

    private void createBusinessCard(UserModel currentUser, BusinessCardFormController.BusinessCard businessCard) {
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

    @AddCSRFToken
    public Result editCard(Long id){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        BusinessCardModel card = new BusinessCardModel();
        if(rolesService.isAdmin(currentUser)){
            card = businessCardModel.findById(id);
        }else {
            card = businessCardModel.findByIdAndUser(id, currentUser);
        }
        if(card == null){
            return businessCardMessageController.cardNotFound();
        }
        return ok(
                views.html.businessCard.updateCard.render(
                        currentUser, messages.createMessages(), contactModel.findLastFive(),
                        businessCardModel.findLastFive(currentUser), businessCardModel.findById(id)
                )
        );
    }

    @RequireCSRFCheck
    public Result updateCard(Long id){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        BusinessCardModel card = new BusinessCardModel();
        if(rolesService.isAdmin(currentUser)){
            card = businessCardModel.findById(id);
        }else {
            card = businessCardModel.findByIdAndUser(id, currentUser);
        }
        if(card == null){
            return businessCardMessageController.cardNotFound();
        }
        Form<BusinessCardFormController.BusinessCard> businessCardForm = Form.
                form(BusinessCardFormController.BusinessCard.class).bindFromRequest();
        if(businessCardForm.hasErrors()){
            return businessCardMessageController.canNotUpdated();
        }

        BusinessCardFormController.BusinessCard currentCard = businessCardForm.get();

        updateBusinessCard(card, currentCard);
        return businessCardMessageController.hasBeenUpdated();
    }

    private void updateBusinessCard(BusinessCardModel card, BusinessCardFormController.BusinessCard currentCard) {
        card.firstname = currentCard.firstname;
        card.lastname = currentCard.lastname;
        card.email = currentCard.email;
        card.company = currentCard.company;
        card.phone = currentCard.phone;
        card.address = currentCard.address;
        card.updateDate = new Date();
        card.update();
    }

    public Result deleteCard(Long id){
        if(session().get("email") == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(currentUser == null){
            return businessCardMessageController.youMustLoginFirst();
        }
        BusinessCardModel card = new BusinessCardModel();
        if(rolesService.isAdmin(currentUser)){
            card = businessCardModel.findById(id);
        }else {
            card = businessCardModel.findByIdAndUser(id, currentUser);
        }
        if(card == null){
            return businessCardMessageController.cardNotFound();
        }

        card.delete();
        return businessCardMessageController.hasBeenDeleted();
    }

}
