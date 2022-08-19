package steps;

import com.codeborne.selenide.WebDriverRunner;
import io.cucumber.java.After;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class Hooks {

    @After
    public void after_test(Scenario scenario) {
        if (scenario.isFailed()) {
            try {
                ByteArrayInputStream screenshot = new ByteArrayInputStream(((TakesScreenshot) WebDriverRunner.getWebDriver())
                        .getScreenshotAs(OutputType.BYTES));
                Allure.addAttachment(UUID.randomUUID().toString(), screenshot);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
