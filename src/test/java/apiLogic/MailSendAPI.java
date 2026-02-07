package apiLogic;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.annotations.Step;

public class MailSendAPI {
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
    }

    public Response getResponse() {
        return response;
    }
}
