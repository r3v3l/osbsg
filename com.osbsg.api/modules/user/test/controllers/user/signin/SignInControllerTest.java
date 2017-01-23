package controllers.user.signin;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.Test;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.test.WithApplication;

import static org.junit.Assert.assertEquals;
import static play.mvc.Http.Status.CREATED;
import static play.test.Helpers.POST;
import static play.test.Helpers.route;

/**
 * Created by adrian on 23.01.17.
 */
public class SignInControllerTest extends WithApplication {

    private String myUrl = "http://localhost:9000/signIn";
    private String goodEmail = "adus.cos.pl";
    private String goodPassword = "adus.cos.pl";

    @Test
    public void goodEmailAndGoodPasswordTest() throws Exception {

        Result createResult = route(new Http.RequestBuilder().method(POST)
                .uri(myUrl).bodyJson(itemData(goodEmail, goodPassword)));
        assertEquals(CREATED, createResult.status());
        //DocumentContext findingCtx = JsonPath.parse(contentAsString(findingResult));
    }

    private JsonNode itemData(String email, String password) {
        return Json.newObject()
                .put("email", email)
                .put("password", password);
    }

}