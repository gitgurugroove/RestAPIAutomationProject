@sendGridMail
Feature: Send email using SendGrid V3 Mail Send API (Mock Server)

  The SendGrid V3 Mail Send API allows applications to send transactional
  and marketing emails. This feature validates successful email delivery
  and error handling using the SendGrid V3 mock server without authentication.

  Background:
    Given the SendGrid mock server base URL is configured

  @positive @smoke @202Status
  Scenario: Successfully send a transactional email with multiple recipients
    Given a valid email send request payload
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "202"
    And the response body should be empty

  @positive @smoke @200Status
  Scenario: Send email with wrong endpoint to validate error handling
    Given a valid email send request payload
    When the client sends a POST request to "/v3/mail/sen"
    Then the response status code should be "200"
    And the response body contains message "Hey ya! Great to see you here. Btw, nothing is configured for this request path. Create a rule and start building a mock API."


  @negative @validation @400Status
  Scenario: Fail to send email when "from"/"to" from email is missing
    Given an email send request payload without a from address
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"
    And the response body should contain an error message indicating the missing fields from the request payload

  @negative @validation @404Status
  Scenario: Fail to send email when baseURL is incorrect
    Given a valid email send request payload
    When the client sends a POST request to "/v3/mail/send" with incorrect base URL
    Then the response status code should be "404"
    And the response body contains message "Hey ya! Great to see you here. BTW, nothing is configured here. Create a mock server on Beeceptor.com"

