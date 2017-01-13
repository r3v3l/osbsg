package controllers.company;

import controllers.ApplicationController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 10.01.17.
 */
public class CompanyMessagesController extends Controller {

    public ApplicationController applicationController = new ApplicationController();

    public Result generateFormError(){
        return applicationController.createMessage(
                "formDanger", "Errors occured. Please try again.", "/company"
        );
    }

    public Result generateCompanyExists(CompanyFormController.CurrentCompany currentCompany){
        return applicationController.createMessage(
                "formWarning", "Company " +currentCompany.name+ " exists. Please try again.",
                "/company"
        );
    }

    public Result generateTaxNumberExists(CompanyFormController.CurrentCompany currentCompany){
        return applicationController.createMessage(
                "formWarning", "Company " +currentCompany.name+
                        " contains tax number "+currentCompany.taxNumber+" exists. Please try again.",
                "/company"
        );
    }

    public Result generateSuccessCreated(CompanyFormController.CurrentCompany currentCompany){
        return applicationController.createMessage(
                "formSuccess", "Company " +currentCompany.name+ " has been created.",
                "/company"
        );
    }

    public Result generateSuccessUpdate(CompanyFormController.CurrentCompany currentCompany){
        return applicationController.createMessage(
                "formSuccess", "Company " +currentCompany.name+ " has been updated.",
                "/company"
        );
    }

}
