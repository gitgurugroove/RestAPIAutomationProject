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

  @positive
  Scenario: Send email with attachment
    Given a valid email send request payload with an attachment
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "202"

  @positive
  Scenario: Send email with tracking enabled
    Given a valid email send request payload with open and click tracking enabled
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "202"

  @negative @validation
  Scenario: Fail to send email when "from" email is missing
    Given an email send request payload without a from address
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"

  @negative @validation
  Scenario: Fail to send email when recipient email is invalid
    Given an email send request payload with an invalid recipient email
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"

  @negative @validation
  Scenario: Fail to send email when personalizations are missing
    Given an email send request payload without personalizations
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"

  @negative @validation
  Scenario: Fail to send email when content is empty
    Given an email send request payload without content
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"

  @edge
  Scenario: Send email with scheduled send time in the future
    Given a valid email send request payload with future send_at timestamp
    When the client sends a POST request to "/v3/mail/send"
    Then the response status code should be "400"
