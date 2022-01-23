package domain.com.homework_4;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class Steps {

    @Step("Open github {url}")
    public Steps open(String url) {
        Selenide.open(url);
        return this;
    }

    @Step("Search for repository: {repo}")
    public Steps searchFor(String repo) {
        $("[name = q]").setValue(repo).pressEnter();
        return this;
    }

    @Step("Open available repository")
    public Steps openRepo() {
        $("[href='/IsmagilovQA/QAGURU_Course']").click();
        return this;
    }

    @Step("Open issue tab")
    public Steps openIssueTab() {
        $("#issues-tab").click();
        return this;
    }

    @Step("Assert visibility of issue title: {title}")
    public void shouldBeTitleVisible(String title) {
        $(By.linkText(title)).shouldBe(visible);
    }

    @Attachment(value = "Screenshot", type = "text/html", fileExtension = "html")
    public byte[] attachPageSource() {
        return WebDriverRunner.source().getBytes(StandardCharsets.UTF_8);
    }
}
