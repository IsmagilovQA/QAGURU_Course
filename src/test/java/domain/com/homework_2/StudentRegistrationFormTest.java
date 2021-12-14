package domain.com.homework_2;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationFormTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.holdBrowserOpen = false;
        Configuration.headless = false;
    }

    @Test
    void formShouldBeSubmittedAndVerified() {
        // Arrange
        open("https://demoqa.com/automation-practice-form");

        // Act
        $("#firstName").setValue("Vitaliy");
        $("#lastName").setValue("Ismagilov");
        $("#userEmail").setValue("ism@gmail.com");
        $x("//label[contains(text(),'Male')]").click();
        $("#userNumber").setValue("3777448888");

        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").click();
        $(".react-datepicker__month-select").selectOption("April");
        $(".react-datepicker__year-select").selectOption("1984");
        $x("//div[contains(text(),'29')]").click();

        $("#subjectsInput").hover().setValue("Ma").pressEnter();
        $("#subjectsInput").setValue("En").pressEnter();

        $x("//label[contains(text(),'Music')]").click();
        $x("//label[contains(text(),'Sports')]").click();

        $("#submit").scrollTo();

        $("#uploadPicture").uploadFromClasspath("simple.txt");

        $("#currentAddress").setValue("London is a capital of Great Britain");

        $("#react-select-3-input").setValue("Ha").pressEnter();
        $("#react-select-4-input").setValue("Pa").pressEnter();

        $("#submit").click();

        // Assert
        $("#example-modal-sizes-title-lg").shouldBe(visible);

        $$x("//*[@class='modal-body']//td[2]").shouldHave(CollectionCondition
                .exactTexts("Vitaliy Ismagilov", "ism@gmail.com", "Male", "3777448888",
                        "29 April,1984", "Maths, English", "Music, Sports", "simple.txt",
                        "London is a capital of Great Britain", "Haryana Panipat"));
    }
}