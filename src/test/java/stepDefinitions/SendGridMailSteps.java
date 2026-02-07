package stepDefinitions;

import apiLogic.MailSendAPI;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;
import utils.SendGridPayloadBuilder;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SendGridMailSteps {

    private String requestPayload;
    private Response response;

    @Steps
    MailSendAPI mailSendAPI;

    @Given("the SendGrid mock server base URL is configured")
    public void the_send_grid_mock_server_base_url_is_configured() {
        mailSendAPI.configureBaseUrl();
    }

    @Given("a valid email send request payload")
    public void a_valid_email_send_request_payload() {
        requestPayload = SendGridPayloadBuilder.validEmailPayload();
    }

    @Given("a valid email send request payload with CC and BCC recipients")
    public void payloadWithCcAndBcc() {
        requestPayload = SendGridPayloadBuilder.validEmailPayload();
    }

    @Given("a valid email send request payload with an attachment")
    public void payloadWithAttachment() {
        requestPayload = SendGridPayloadBuilder.validEmailPayload();
    }

    @Given("a valid email send request payload with open and click tracking enabled")
    public void payloadWithTrackingEnabled() {
        requestPayload = SendGridPayloadBuilder.validEmailPayload();
    }

    @When("the client sends a POST request to {string}")
    public void the_client_sends_a_post_request_to(String endpoint) {
        mailSendAPI.sendMail(endpoint, requestPayload);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        assertThat(mailSendAPI.getResponse().getStatusCode(), is(Integer.parseInt(statusCode)));
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        assertThat(mailSendAPI.getResponse().getBody().asString(), anyOf(is(emptyString()), nullValue()));
    }


}
