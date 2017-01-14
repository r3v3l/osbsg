package controllers.core;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.jetbrains.annotations.NotNull;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * Created by astolarski on 14.01.17.
 */
public class CoreResponseController extends Controller {

    @NotNull
    public Result createMessage(String variable, String message, String redirect) {
        return createResponse(
                "redirect", variable, message, redirect
        );
    }

    public static Result buildResponse(String type, String message, String responseType){
        ActionLogController.save(buildJsonResponse(type, message).toString());
        switch (responseType){
            case "bad":
                return badRequest(buildJsonResponse(type, message));
            case "good":
                return ok(buildJsonResponse(type, message));
            default:
                return ok(buildJsonResponse(type, message));
        }
    }

    public static ObjectNode buildJsonResponse(String type, String message){
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        wrapper.put(type, msg);
        return wrapper;
    }

    public static Result createResponse(String type, String messageName, String message, String redirect){
        ActionLogController.save(message);
        flash(messageName, message);
        switch (type){
            case "redirect":
                return redirect(redirect);
            default:
                return redirect(redirect);
        }
    }

}
