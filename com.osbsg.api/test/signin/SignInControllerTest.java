package signin;

import org.junit.Test;
import play.libs.ws.WS;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.test.WithServer;

import java.util.concurrent.CompletionStage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static play.mvc.Http.Status.OK;
import static play.test.Helpers.BAD_REQUEST;

/**
 * Created by adrian on 23.01.17.
 */
public class SignInControllerTest extends WithServer {

    private String myUrl = "http://localhost:9000/user/signIn";
    private String goodEmail = "adus@cos.cos";
    private String goodPassword = "P@ssw0rd";
    private String badEmail = "cos@cos.cos";
    private String badPassword = "P@sssw0rd";

    private String noEmailAndPasswordMessage = "{\"password\":[\"This field is required\"],\"email\"" +
            ":[\"This field is required\"]}";
    private String noEmailMessage = "email";
    private String noPasswordMessage = "password";
    private String goodEmailAndGoodPasswordMessage = "Użytkownik został zalogowany.";
    private String invalidEmailMessage = "Adres email " +badEmail+ " nie został odnaleziony.";
    private String invalidPasswordMessage = "Nieprawidłowe hasło.";

    @Test
    public void nullEmailAndNullPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("");
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noEmailAndPasswordMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void nullEmailAndGoodPasswordTest() throws Exception {
            int timeout = 5000;
            String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
            try (WSClient ws = WS.newClient(this.testServer.port())) {
                CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                        post("password=" + goodPassword );
                WSResponse response = stage.toCompletableFuture().get();
                assertEquals(BAD_REQUEST, response.getStatus());
                assertTrue(response.getBody().contains(noEmailMessage));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }

    @Test
    public void goodEmailAndNoPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email=" + goodEmail );
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(BAD_REQUEST, response.getStatus());
            assertTrue(response.getBody().contains(noPasswordMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goodEmailAndGoodPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email=" + goodEmail+"&password="+goodPassword );
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(goodEmailAndGoodPasswordMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void invalidEmailAndGoodPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email=" + badEmail+"&password="+goodPassword );
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(invalidEmailMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void goodEmailAndInvalidPasswordTest() throws Exception {
        int timeout = 5000;
        String url = "http://localhost:" + this.testServer.port() + "/user/signIn";
        try (WSClient ws = WS.newClient(this.testServer.port())) {
            CompletionStage<WSResponse> stage = ws.url(url).setContentType("application/x-www-form-urlencoded").
                    post("email=" + goodEmail+"&password="+badPassword );
            WSResponse response = stage.toCompletableFuture().get();
            assertEquals(OK, response.getStatus());
            assertTrue(response.getBody().contains(invalidPasswordMessage));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}