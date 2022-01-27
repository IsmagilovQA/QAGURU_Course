package domain.com.homework_2.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import domain.com.homework_2.components.Calendar;
import domain.com.homework_2.enums.*;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {

    private final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderRadio = $("#genterWrapper"),
            phoneInput = $("#userNumber"),
            subjectsInput = $("#subjectsInput"),
            uploadInput = $("#uploadPicture"),
            addressTextArea = $("#currentAddress"),
            stateSelectBox = $("#react-select-3-input"),
            citySelectBox = $("#react-select-4-input"),
            submitBtn = $("#submit"),
            registrationForm = $("#userForm");


    @Step("Open page")
    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    @Step("Type first name {0}")
    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step("Type last name {0}")
    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step("Type email address {0}")
    public RegistrationPage typeEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    @Step("Select gender {0}")
    public RegistrationPage selectGender(Gender gender) {
        genderRadio.$(byText(String.valueOf(gender))).click();
        return this;
    }

    @Step("Type phone number {0}")
    public RegistrationPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    @Step("Set birth date: month {0}, year {1}, day {2}")
    public RegistrationPage setBirthDate(String month, String year, String day) {
        new Calendar().setDate(month, year, day);
        return this;
    }

    @Step("Set subject {0}")
    public RegistrationPage setSubject(Subject value) {
        subjectsInput.hover().setValue(String.valueOf(value)).pressEnter();
        return this;
    }

    @Step("Set hobby {0}")
    public RegistrationPage setHobbies(Hobby value) {
        $(byText(String.valueOf(value))).click();
        return this;
    }

    @Step("Upload file with name {0}")
    public RegistrationPage uploadFile(String fileName) {
        uploadInput.uploadFromClasspath(fileName);
        return this;
    }

    @Step("Set address {0}")
    public RegistrationPage setAddress(String address) {
        addressTextArea.setValue(address);
        return this;
    }

    @Step("Select state {0}")
    public RegistrationPage selectState(State state) {
        stateSelectBox.setValue(String.valueOf(state)).pressEnter();
        return this;
    }

    @Step("Select city {0}")
    public RegistrationPage selectCity(City city) {
        citySelectBox.setValue(String.valueOf(city)).pressEnter();
        return this;
    }

    @Step("Submit the form")
    public void clickSubmit() {
        submitBtn.scrollIntoView(true).click();
    }

    @Step("Registration form should be {0}")
    public void registrationFormShouldBe(Condition condition) {
        registrationForm.shouldBe(condition);
    }

    @Step("Registration form should have required attribute {0}")
    public void shouldHaveRequiredAttr(String element) {
        $(element).shouldHave(attribute("required", "true"));
    }
}

