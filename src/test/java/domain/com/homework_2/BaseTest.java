package domain.com.homework_2;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import domain.com.homework_2.helpers.Attach;
import domain.com.homework_2.pages.RegistrationPage;
import domain.com.homework_2.pages.RegistrationResultsPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        //Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;

        // Remote run using Selenoid configuration:
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}


