package domain.com.homework_2.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationResultsPage {

    @Step("Modal should be {0}")
    public void modalShouldBe(Condition condition) {
        $("#example-modal-sizes-title-lg").shouldBe(condition);
    }

    @Step("Table should have text {0}")
    public void tableShouldHaveTexts(String... exactTexts) {
        $$(".table td:last-child").shouldHave(exactTexts(exactTexts));
    }
}
