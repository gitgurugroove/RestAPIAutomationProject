package apiLogic;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.core.Serenity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MailSendAPI {

    private static final Logger log = LoggerFactory.getLogger(MailSendAPI.class);
    private Response response;

    private static final String BASE_URL =
            "https://sendgrid-v3-api.mock.beeceptor.com";

    /**
     * Configure base URI for RestAssured
     */
    public void configureBaseUrl() {
        RestAssured.baseURI = BASE_URL;
    }

    /**
     * Send email using SendGrid Mail Send API
     */
    public void sendMail(String endpoint, String payload) {

        response = RestAssured
                .given()
                .log().all()                       // request logged
                .relaxedHTTPSValidation()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(endpoint)
                .then()
                .log().all()                       // response logged
                .extract()
                .response();

        log.info(
                "Response Status Code: {}\nResponse Body:\n{}",
                response.getStatusCode(),
                response.getBody().asString()
        );

        Serenity.recordReportData()
                .withTitle("SendGrid - Request Payload")
                .andContents(payload);

        Serenity.recordReportData()
                .withTitle("SendGrid - Response (" + response.getStatusCode() + ")")
                .andContents(response.getStatusLine() + "\n\n" + response.getBody().asString());
    }

    /**
     * Get the API response
     */
    public Response getResponse() {
        return response;
    }
}
