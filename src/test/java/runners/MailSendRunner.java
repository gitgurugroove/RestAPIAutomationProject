package runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"stepDefinitions"},
        plugin = {
                "pretty",
                "html:target/site/serenity/cucumber-reports.html",
                "json:target/site/serenity/cucumber-reports.json",
                "junit:target/site/serenity/cucumber-reports.xml",
        },
        monochrome = false,                          // Makes console output readable
        tags = "@sendGridMail",                        // Tags to include in the test run
        dryRun = false                          // Set to true to check mappings without executing the tests
)
public class MailSendRunner {}