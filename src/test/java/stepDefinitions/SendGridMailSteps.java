package stepDefinitions;

import apiLogic.MailSendAPI;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
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

    @Given("an email send request payload without a from address")
    public void an_email_send_request_payload_without_a_from_address() {
        requestPayload = SendGridPayloadBuilder.EmailPayload400Status();
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

    @When("the client sends a POST request to {string} with incorrect base URL")
    public void theClientSendsAPOSTRequestToWithIncorrectBaseURL(String endpoint) {
        mailSendAPI.sendMail404(endpoint, requestPayload);
    }

    @Then("the response status code should be {string}")
    public void theResponseStatusCodeShouldBe(String statusCode) {
        assertThat(mailSendAPI.getResponse().getStatusCode(), is(Integer.parseInt(statusCode)));
    }

    @Then("the response body should be empty")
    public void the_response_body_should_be_empty() {
        assertThat(mailSendAPI.getResponse().getBody().asString(), anyOf(is(emptyString()), nullValue()));
    }

    @And("the response body contains message {string}")
    public void theResponseBodyContainsMessage(String message) {
        assertThat(mailSendAPI.getResponse().getBody().asString(), containsString(message));
    }

    @And("the response body should contain an error message indicating the missing fields from the request payload")
    public void theResponseBodyShouldContainAnErrorMessageIndicatingTheMissingFieldsFromTheRequestPayload() {
        String responseBody = mailSendAPI.getResponse().getBody().prettyPrint();
        String errorMessage = mailSendAPI.getResponse().jsonPath().getString("errors[0].message");

        System.out.println("Error message from response: " + errorMessage);

        assertThat(
                "Expected error message to indicate invalid email format",
                errorMessage,
                containsString("personalizations.1.from.email must match format \"email\"")
        );
    }

}
