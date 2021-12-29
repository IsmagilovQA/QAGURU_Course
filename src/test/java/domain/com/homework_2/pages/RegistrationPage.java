package domain.com.homework_2.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import domain.com.homework_2.components.Calendar;
import domain.com.homework_2.enums.*;

import static com.codeborne.selenide.CollectionCondition.exactTexts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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


    public RegistrationPage openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        return this;
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        emailInput.setValue(email);
        return this;
    }

    public RegistrationPage selectGender(Gender gender) {
        genderRadio.$(byText(String.valueOf(gender))).click();
        return this;
    }

    public RegistrationPage setPhone(String phone) {
        phoneInput.setValue(phone);
        return this;
    }

    public RegistrationPage setBirthDate(String month, String year, String day) {
        new Calendar().setDate(month, year, day);
        return this;
    }

    public RegistrationPage setSubject(Subject value) {
        subjectsInput.hover().setValue(String.valueOf(value)).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(Hobby value) {
        $(byText(String.valueOf(value))).click();
        return this;
    }

    public RegistrationPage uploadFile(String fileName) {
        uploadInput.uploadFromClasspath(fileName);
        return this;
    }

    public RegistrationPage setAddress(String address) {
        addressTextArea.setValue(address);
        return this;
    }

    public RegistrationPage selectState(State state) {
        stateSelectBox.setValue(String.valueOf(state)).pressEnter();
        return this;
    }

    public RegistrationPage selectCity(City city) {
        citySelectBox.setValue(String.valueOf(city)).pressEnter();
        return this;
    }

    public void clickSubmit() {
        submitBtn.scrollIntoView(true).click();
    }

    public void registrationFormShouldBe(Condition condition) {
       registrationForm.shouldBe(condition);
    }

    public void shouldHaveRequiredAttr (String element) {
        $(element).shouldHave(attribute("required", "true"));
    }


}
