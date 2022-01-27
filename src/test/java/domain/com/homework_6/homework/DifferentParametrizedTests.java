package domain.com.homework_6.homework;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DifferentParametrizedTests {

    @ValueSource(strings = {"Git Pocket", "Speaking JavaScript", "You Don't Know JS"})
    @ParameterizedTest(name = "Test using valueSource with input data: {0}")
    void testUsingValueSource(String inputData) {
        open("https://demoqa.com/books");
        $("#searchBox").setValue(inputData);
        $$(".action-buttons a").first().shouldHave(text(inputData));
    }


    @CsvSource(value = {
            "Vasiliy; Name:Vasiliy",
            "Ivan; Name:Ivan",
            "Nikolay; Name:Nikolay"
    }, delimiter = ';'
    )
    @ParameterizedTest(name = "Test using CsvSource with input data: {0}")
    void testUsingCsvSource(String inputData, String expectedResult) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(inputData).click();
        $("#submit").scrollTo().click();
        $("#name").shouldHave(text(expectedResult));
    }


    @CsvFileSource(resources = "/dataProvider.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Test using CsvSource with input data: {0}")
    void testUsingCsvFileSource(String input, String result) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(input).click();
        $("#submit").scrollTo().click();
        $("#name").shouldHave(text(result));
    }


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Leon", "email_1@gmail.com", 123, List.of("Name:Leon","Email:email_1@gmail.com", "Current Address :123")),
                Arguments.of("Egor", "email_2@gmail.com", 456, List.of("Name:Egor","Email:email_2@gmail.com", "Current Address :456")),
                Arguments.of("Sergey", "email_3@gmail.com", 789, List.of("Name:Sergey","Email:email_3@gmail.com", "Current Address :789"))
        );
    }
    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Test using MethodSource with input data: {0}, {1}, {2}")
    void testUsingMethodSource(String name, String email, int address, List<String> result) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(name).click();
        $("#userEmail").setValue(email).click();
        $("#currentAddress").setValue(String.valueOf(address)).click();
        $("#submit").scrollTo().click();
        $$("#output p").shouldHave(texts(result));
    }


    public enum MyEnum {
        TEST1, TEST2, TEST3
    }

    @EnumSource(MyEnum.class)
    @ParameterizedTest(name = "Test using EnumSource with input data {0}")
    void testUsingEnumSource(MyEnum myEnum) {
        open("https://demoqa.com/text-box");
        $("#userName").setValue(String.valueOf(myEnum)).click();
        $("#submit").scrollTo().click();
        $("#name").shouldHave(text("Name:"+ myEnum));
    }
}
