package controllers.company;

import controllers.user.UserProfileMessagesController;
import models.BusinessCardModel;
import models.CompanyModel;
import models.ContactModel;
import models.UserModel;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import services.RolesService;

import java.util.Date;
import java.util.List;

/**
 * Created by astolarski on 09.01.17.
 */
public class CompanyController extends Controller {

    private CompanyMessagesController companyMessagesController = new CompanyMessagesController();
    private UserModel userModel = new UserModel();
    private UserProfileMessagesController userProfileMessagesController = new UserProfileMessagesController();
    private BusinessCardModel businessCardModel = new BusinessCardModel();

    @AddCSRFToken
    public Result getCompany(){
        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        CompanyFormMessageController companyFormMessageController = new CompanyFormMessageController();
        return ok(
                views.html.company.company.render(
                        currentUser, companyFormMessageController.generateMessages(), getContacts(currentUser),
                        businessCardModel.findLastFive(currentUser)
                )
        );

    }

    private List<ContactModel> getContacts(UserModel currentUser){
        RolesService rolesService = new RolesService();
        if(rolesService.isAdmin(currentUser)){
            ContactModel contactModel = new ContactModel();
            return contactModel.findLastFive();
        } else {
            return null;
        }
    }

    @RequireCSRFCheck
    public Result createCompany(){

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Form<CompanyFormController.CurrentCompany> currentCompanyForm = Form
                .form(CompanyFormController.CurrentCompany.class).bindFromRequest();
        if(currentCompanyForm.hasErrors()){
            return companyMessagesController.generateFormError();
        }

        CompanyFormController.CurrentCompany currentCompany = currentCompanyForm.get();
        if(checkName(currentCompany.name)){
            return companyMessagesController.generateCompanyExists(currentCompany);
        }
        if(checkNip(currentCompany.taxNumber)){
            return companyMessagesController.generateTaxNumberExists(currentCompany);
        }

        createNewCompany(currentUser, currentCompany);

        if(!checkNip(currentCompany.taxNumber) && !checkName(currentCompany.name)){
            return companyMessagesController.generateFormError();
        }
        return companyMessagesController.generateSuccessCreated(currentCompany);
    }

    @RequireCSRFCheck
    public Result updateCompany(){

        if(session().get("email") == null){
            return userProfileMessagesController.youMustLogin();
        }
        UserModel currentUser = userModel.findByEmail(session().get("email"));
        if(userModel.findByEmail(session().get("email")) == null){
            return userProfileMessagesController.youMustLogin();
        }

        Form<CompanyFormController.CurrentCompany> currentCompanyForm = Form
                .form(CompanyFormController.CurrentCompany.class).bindFromRequest();
        if(currentCompanyForm.hasErrors()){
            return companyMessagesController.generateFormError();
        }

        CompanyFormController.CurrentCompany currentCompany = currentCompanyForm.get();
        if(checkName(currentCompany.name) && !currentCompany.name.equals(currentUser.company.name)){
            return companyMessagesController.generateCompanyExists(currentCompany);
        }
        if(checkNip(currentCompany.taxNumber) && !currentCompany.taxNumber.equals(currentUser.company.taxNumber)){
            return companyMessagesController.generateTaxNumberExists(currentCompany);
        }

        updateExistsCompany(currentUser, currentCompany);

        if(!checkNip(currentCompany.taxNumber) && !checkName(currentCompany.name)){
            return companyMessagesController.generateFormError();
        }

        return companyMessagesController.generateSuccessUpdate(currentCompany);
    }

    private void createNewCompany(UserModel currentUser, CompanyFormController.CurrentCompany currentCompany) {
        CompanyModel companyModel = new CompanyModel();
        companyModel.city = currentCompany.city;
        companyModel.lawForm = currentCompany.lawForm;
        companyModel.country = currentCompany.country;
        companyModel.creationDate = new Date();
        companyModel.name = currentCompany.name;
        companyModel.postalCode = currentCompany.postalCode;
        companyModel.street = currentCompany.street;
        companyModel.taxNumber = currentCompany.taxNumber;
        companyModel.updatedDate = new Date();
        companyModel.user = currentUser;
        companyModel.save();
    }

    private void updateExistsCompany(UserModel currentUser, CompanyFormController.CurrentCompany currentCompany) {
        currentUser.company.city = currentCompany.city;
        currentUser.company.lawForm = currentCompany.lawForm;
        currentUser.company.country = currentCompany.country;
        currentUser.company.name = currentCompany.name;
        currentUser.company.postalCode = currentCompany.postalCode;
        currentUser.company.street = currentCompany.street;
        currentUser.company.taxNumber = currentCompany.taxNumber;
        currentUser.company.updatedDate = new Date();
        currentUser.company.update();
    }

    public boolean checkName(String name){
        CompanyModel companyModel = new CompanyModel();
        if(companyModel.findByName(name) != null){
            return true;
        }
        return false;
    }

    public boolean checkNip(String taxNumber){
        CompanyModel companyModel = new CompanyModel();
        if(companyModel.findByTaxNumber(taxNumber) != null){
            return true;
        }
        return false;
    }

}
