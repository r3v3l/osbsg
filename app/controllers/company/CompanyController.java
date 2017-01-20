package controllers.company;

import controllers.user.UserProfileMessagesController;
import models.core.BusinessCardModel;
import models.core.ContactModel;
import models.core.UserModel;
import play.data.Form;
import play.filters.csrf.AddCSRFToken;
import play.filters.csrf.RequireCSRFCheck;
import play.mvc.Controller;
import play.mvc.Result;
import repositories.CompanyRepo;
import services.core.CompanyService;
import services.core.RolesService;

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
        CompanyRepo companyRepo = new CompanyRepo();
        companyRepo.createCompany(currentUser, currentCompany);
    }

    private void updateExistsCompany(UserModel currentUser, CompanyFormController.CurrentCompany currentCompany) {
        CompanyRepo companyRepo = new CompanyRepo();
        companyRepo.updateCompany(currentUser, currentCompany);
    }

    public boolean checkName(String name){
        CompanyService companyService = new CompanyService();
        return companyService.checkName(name);
    }

    public boolean checkNip(String taxNumber){
        CompanyService companyService = new CompanyService();
        return companyService.checkNip(taxNumber);
    }

}
