package controllers.core;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Controller;

/**
 * Created by adrian on 23.01.17.
 */
public class MainController extends Controller {

    public ObjectNode buildJsonResponse(String type, String message){
        ObjectNode wrapper = Json.newObject();
        ObjectNode msg = Json.newObject();
        msg.put("message", message);
        return wrapper;
    }

}
