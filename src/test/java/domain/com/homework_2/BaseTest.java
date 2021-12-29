package domain.com.homework_2;

import com.codeborne.selenide.Configuration;
import domain.com.homework_2.pages.RegistrationPage;
import domain.com.homework_2.pages.RegistrationResultsPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTest {

    RegistrationPage registrationPage = new RegistrationPage();
    RegistrationResultsPage registrationResultsPage = new RegistrationResultsPage();

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;
    }
}
