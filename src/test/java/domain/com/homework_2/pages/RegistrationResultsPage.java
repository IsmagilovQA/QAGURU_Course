package domain.com.homework_2.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class RegistrationResultsPage {

    public void modalShouldBe(Condition condition) {
        $("#example-modal-sizes-title-lg").shouldBe(condition);
    }

    public void tableShouldHaveTexts(String... exactTexts) {
        $$(".table td:last-child").shouldHave(exactTexts(exactTexts));
    }
}
