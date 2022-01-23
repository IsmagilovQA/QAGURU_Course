package domain.com.homework_4;

import com.beust.jcommander.Parameter;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class IssueTests {

    private static final String URL = "https://github.com/";
    private static final String REPO = "IsmagilovQA/QAGURU_Course";
    private static final String ISSUE_TITLE = "Test issue #3";

    @AfterEach
    public void saveSources() {
        new Steps().attachPageSource();
    }

    @Test
    void selenideWithListenerTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open(URL);
        $("[name = q]").setValue(REPO).pressEnter();
        $("[href='/IsmagilovQA/QAGURU_Course']").click();
        $("#issues-tab").click();
        $(By.linkText(ISSUE_TITLE)).shouldBe(visible);
    }

    @Test
    void selenideWithLambdaStepTest() {
        Allure.label("owner", "ismagilov");
        Allure.feature("Issues");
        Allure.story("Creating issues for authorized users");
        Allure.label("severity", SeverityLevel.CRITICAL.value());
        Allure.link("GitHub", "https://github.com");
        Allure.parameter("parameter name", "parameter value");
        Allure.description("Selenide test using lambda approach for allure steps");

        step("Open github " + URL, () -> {
            open(URL);
        });
        step("Search for repository " + REPO, () -> {
            $("[name = q]").setValue(REPO).pressEnter();
        });
        step("Open available repository", () -> {
            $("[href='/IsmagilovQA/QAGURU_Course']").click();
        });
        step("Open issue tab", () -> {
            $("#issues-tab").click();
        });
        step("Assert visibility of issue title " + ISSUE_TITLE, () -> {
            Allure.addAttachment("Page Source", "text/html", WebDriverRunner.source(), "html");
            $(By.linkText(ISSUE_TITLE)).shouldBe(visible);
        });
    }

    @Test
    @Owner("ismagilov")
    @Feature("Issues")
    @Stories({
            @Story("Creating issues for authorized users"),
            @Story("Creating issues for authorized users_2")
    })
    @Severity(SeverityLevel.CRITICAL)
    @Link(value = "GitHub", url = "https://github.com")
    @DisplayName("Selenide test using annotated approach for allure steps")
    void selenideWithAnnotatedStepsTest() {
        new Steps()
                .open(URL)
                .searchFor(REPO)
                .openRepo()
                .openIssueTab()
                .shouldBeTitleVisible(ISSUE_TITLE);
    }
}
