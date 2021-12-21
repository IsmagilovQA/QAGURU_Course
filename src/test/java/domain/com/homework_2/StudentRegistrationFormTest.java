package domain.com.homework_2;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.valueOf;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;
    }

    @BeforeEach
    void setup() {
        open("/automation-practice-form");
    }

    @Test
    void formShouldBeSubmittedAndVerified() {
        // Act
        $("#firstName").setValue("Vitaliy");
        $("#lastName").setValue("Ismagilov");
        $("#userEmail").setValue("ism@gmail.com");

        $("#genterWrapper").$(byText("Male")).click();

        $("#userNumber").setValue("3777448888");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1984");
        $$(".react-datepicker__day--029").filter(not(cssClass(".react-datepicker__day--outside-month")))
                .first().click();

        $("#subjectsInput").hover().setValue("Ma").pressEnter();
        $("#subjectsInput").setValue("En").pressEnter();

        $(byText("Music")).click();
        $(byText("Sports")).click();

        $("#uploadPicture").uploadFromClasspath("simple.txt");

        $("#currentAddress").setValue("London is a capital of Great Britain");

        $("#react-select-3-input").setValue("Ha").pressEnter();
        $("#react-select-4-input").setValue("Pa").pressEnter();

        $("#submit").scrollIntoView(true).click();

        // Assert
        $("#example-modal-sizes-title-lg").shouldBe(visible);

        $$(".table td:last-child").shouldHave(exactTexts("Vitaliy Ismagilov", "ism@gmail.com",
                "Male", "3777448888", "29 April,1984", "Maths, English", "Music, Sports", "simple.txt",
                "London is a capital of Great Britain", "Haryana Panipat"));
    }

    @Test
    void notAbleToSubmitEmptyForm() {
        $("#submit").scrollIntoView(true).click();
        $("#userForm").shouldBe(visible);
    }

    @ParameterizedTest
    @ValueSource(strings = {"#firstName", "#lastName", "#gender-radio-1", "#gender-radio-2", "#gender-radio-3", "#userNumber"})
    void requiredFieldsTest(String locator) {
        $("#submit").scrollIntoView(true).click();
        $(locator).shouldHave(attribute("required", "true"));
    }
}