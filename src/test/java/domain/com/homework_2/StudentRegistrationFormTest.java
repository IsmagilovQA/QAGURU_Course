package domain.com.homework_2;

import com.github.javafaker.Faker;
import domain.com.homework_2.enums.*;
import domain.com.homework_2.pages.RegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;

public class StudentRegistrationFormTest extends BaseTest {

    Faker faker = new Faker();
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String address = faker.address().fullAddress();
    String phoneNumber = faker.number().digits(10);

    @Test
    @DisplayName("Form should be submitted with all fields")
    void formShouldBeSubmittedAndVerified() {
        new RegistrationPage().openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGender(Gender.MALE)
                .setPhone(phoneNumber)
                .setBirthDate("April", "1984", "29")
                .setSubject(Subject.MATHS)
                .setSubject(Subject.ENGLISH)
                .setHobbies(Hobby.MUSIC)
                .setHobbies(Hobby.SPORTS)
                .uploadFile("simple.txt")
                .setAddress(address)
                .selectState(State.UTTAR_PRADESH)
                .selectCity(City.LUCKNOW)
                .clickSubmit();
        registrationResultsPage.modalShouldBe(visible);
        registrationResultsPage.tableShouldHaveTexts(firstName + " " + lastName, email, "Male", phoneNumber,
                "29 April,1984", "Maths, English", "Music, Sports", "simple.txt", address, "Uttar Pradesh Lucknow");
    }

    @Test
    @DisplayName("Not able to submit empty form")
    void notAbleToSubmitEmptyForm() {
        registrationPage.openPage().clickSubmit();
        registrationPage.registrationFormShouldBe(visible);
    }

    @ParameterizedTest(name = "Verify required fields on form")
    @ValueSource(strings = {"#firstName", "#lastName", "#gender-radio-1", "#gender-radio-2",
            "#gender-radio-3", "#userNumber"})
    void requiredFieldsTest(String element) {
        registrationPage.openPage().clickSubmit();
        registrationPage.shouldHaveRequiredAttr(element);
    }
}