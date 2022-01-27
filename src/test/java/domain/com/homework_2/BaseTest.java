package domain.com.homework_2;

import com.codeborne.selenide.Configuration;
import domain.com.homework_2.pages.RegistrationPage;
import domain.com.homework_2.pages.RegistrationResultsPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;

        // Remote run using Selenoid configuration:
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
}
