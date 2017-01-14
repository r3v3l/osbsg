package controllers.company;

import controllers.core.CoreResponseController;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 10.01.17.
 */
public class CompanyMessagesController extends Controller {

    public CoreResponseController responseController = new CoreResponseController();

    public Result generateFormError(){
        return responseController.createMessage(
                "formDanger", "Errors occured. Please try again.", "/company"
        );
    }

    public Result generateCompanyExists(CompanyFormController.CurrentCompany currentCompany){
        return responseController.createMessage(
                "formWarning", "Company " +currentCompany.name+ " exists. Please try again.",
                "/company"
        );
    }

    public Result generateTaxNumberExists(CompanyFormController.CurrentCompany currentCompany){
        return responseController.createMessage(
                "formWarning", "Company " +currentCompany.name+
                        " contains tax number "+currentCompany.taxNumber+" exists. Please try again.",
                "/company"
        );
    }

    public Result generateSuccessCreated(CompanyFormController.CurrentCompany currentCompany){
        return responseController.createMessage(
                "formSuccess", "Company " +currentCompany.name+ " has been created.",
                "/company"
        );
    }

    public Result generateSuccessUpdate(CompanyFormController.CurrentCompany currentCompany){
        return responseController.createMessage(
                "formSuccess", "Company " +currentCompany.name+ " has been updated.",
                "/company"
        );
    }

}
