package utils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SendGridPayloadBuilder {

    // Use classpath-relative path (files should be in src/test/resources/apiRequests)
    private static final String REQUEST_PATH = "apiRequests/";

    /**
     * Generic method to read JSON file from resources on the test classpath
     */
    private static String readJsonFile(String fileName) {
        try (InputStream inputStream =
                     SendGridPayloadBuilder.class
                             .getClassLoader()
                             .getResourceAsStream(REQUEST_PATH + fileName)) {

            if (inputStream == null) {
                throw new RuntimeException(
                        "JSON file not found in resources: " + REQUEST_PATH + fileName
                );
            }

            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        } catch (Exception e) {
            throw new RuntimeException("Failed to read JSON file: " + fileName, e);
        }
    }


    public static String validEmailPayload() {
        return readJsonFile("sendGridMailSend.json");
    }

    public static String payloadWithAttachment() {
        return readJsonFile("sendgrid_with_attachment.json");
    }

    public static String invalidEmailPayload() {
        return readJsonFile("sendgrid_invalid_email.json");
    }
}
