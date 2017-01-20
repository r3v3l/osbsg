package repositories;

import controllers.company.CompanyFormController;
import models.core.CompanyModel;
import models.core.UserModel;

import java.util.Date;

/**
 * Created by adrian on 19.01.17.
 */
public class CompanyRepo {

    public void createCompany(UserModel currentUser, CompanyFormController.CurrentCompany currentCompany) {
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

    public void updateCompany(UserModel currentUser, CompanyFormController.CurrentCompany currentCompany) {
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

}
