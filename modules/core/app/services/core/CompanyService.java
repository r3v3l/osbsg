package services.core;

import models.core.CompanyModel;

/**
 * Created by adrian on 19.01.17.
 */
public class CompanyService {

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
