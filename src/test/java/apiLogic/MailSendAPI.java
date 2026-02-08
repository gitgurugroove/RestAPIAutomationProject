package apiLogic;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSendAPI {
    private static final Logger log = LoggerFactory.getLogger(MailSendAPI.class);
    private Response response;
    private static final String BASE_URL =
            "https://sendgrid-v3-api.mock.beeceptor.com";

    @Step("Configure SendGrid mock server base URL")
    public void configureBaseUrl() {
        SerenityRest.setDefaultBasePath("");
//        SerenityRest.setDefaultBaseUri(BASE_URL);
    }

    @Step("Send email using SendGrid POST {0}")
    public void sendMail(String endpoint, String payload) {

        response = SerenityRest
                .given().log().all()
                .relaxedHTTPSValidation()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .log().all()
                .extract()
                .response();
        log.info("The response body is +\n {}", response.getBody().asString().formatted() + "+\n The response status code is: {}", response.getStatusCode());
    }

    public Response getResponse() {
        return response;
    }
}
