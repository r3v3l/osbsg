package signin;

import org.junit.Test;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.WithServer;

import java.util.concurrent.CompletionStage;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.BAD_REQUEST;
import static play.mvc.Http.Status.OK;

/**
 * Created by adrian on 31.01.17.
 */
public class SignUpControllerTest extends WithServer {

    private String emptyEmail = "";
    private String emptyPassword = "";
    private String emptyConfirmPassword = "";
    private String invalidEmail = "c";
    private String shortPassword = "12345678901";
    private String existsEmail = "adus@cos.cos";
    private String newEmail = "email@example.com";
    private String password = "123456789012";
    private String confirmPassword = "123456789012";
    private String badConfirmPassword = "098765432109";

    private String noDataResponse = "{\"password\":[\"This field is required\"],\"confirmPassword\":[\"This field is " +
            "required\"],\"email\":[\"This field is required\"]}";
    private String noEmailResponse = "{\"email\":[\"This field is required\"]}";
    private String badEmailResposne = "{\"email\":[\"Valid email required\"]}";
    private String noPasswordResponse = "{\"password\":[\"This field is required\"]}";
    private String noConfirmPasswordResponse ="{\"confirmPassword\":[\"This field is required\"]}";
    private String emailExistsRsponse = "{\"error\":\"Wybrany adres e-mail już istnieje. Spróbuj ponownie\"}";
    private String passwordsMismatch = "{\"error\":\"Wprowadzone hasła nie są identyczne. Spróbuj ponownie.\"}";
    private String passwordToShort = "{\"password\":[\"Minimum length is 12\"]}";
    private String accountCreated = "{\"success\":\"Użytkownik został utworzony pomyślnie.\"}";

    @Test
    public void allFieldsAreNullTest() throws Exception {

        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+emptyEmail+"&password="+emptyPassword+"&confirmPassword="+emptyConfirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noDataResponse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void nullEmailTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+emptyEmail+"&password="+password+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noEmailResponse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+newEmail+"&password="+emptyPassword+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noPasswordResponse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullConfirmPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+newEmail+"&password="+password+"&confirmPassword="+emptyPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noConfirmPasswordResponse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void badEmailTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+invalidEmail+"&password="+password+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(badEmailResposne));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void shortPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+newEmail+"&password="+shortPassword+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(passwordToShort));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void passwordMismatchTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+newEmail+"&password="+password+"&confirmPassword="+badConfirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(passwordsMismatch));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void existsEmailTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+existsEmail+"&password="+password+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(emailExistsRsponse));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void accountCreatedTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signUp";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email="+newEmail+"&password="+password+"&confirmPassword="+confirmPassword);
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(accountCreated));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
