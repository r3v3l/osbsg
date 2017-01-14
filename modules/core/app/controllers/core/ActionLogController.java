package controllers.core;

import models.core.ActionLogModel;
import models.core.UserModel;
import play.mvc.Controller;

import java.util.Date;

/**
 * Created by adrian on 21.12.16.
 */
public class ActionLogController extends Controller {

    public static void save(String message){

        ActionLogModel actionLogModel = new ActionLogModel();

        UserModel userModel = new UserModel();

        actionLogModel.userModel = userModel.findByEmail(session().get("username"));
        actionLogModel.creationDate = new Date();
        actionLogModel.currentHost = request().host();
        actionLogModel.currentMethod = request().method();
        actionLogModel.currentPath = request().path();
        actionLogModel.currentUri = request().uri();
        actionLogModel.currentVersion = request().version();
        actionLogModel.remoteAddress = request().remoteAddress();
        actionLogModel.userMessage = message;
        actionLogModel.save();

    }

}
