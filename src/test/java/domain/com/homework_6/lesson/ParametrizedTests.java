package domain.com.homework_6.lesson;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ParametrizedTests {

    @BeforeEach
    void beforeEach() {
        System.out.println("@Before each");
    }

    @ValueSource(strings = {"Selenide", "Allure", "JUnit"})
    @ParameterizedTest(name = "Testing parametrized option with ValueSource: {0}")
    void googleTest(String testData) {
        Selenide.open("https://www.google.com.ua/");
        $("[name='q']").setValue(testData).pressEnter();
        $$("#search a h3").first().shouldHave(text(testData));
    }


    @CsvSource(value = {
            "Selenide; Selenide: concise UI tests in Java",
            "JUnit; JUnit 5"
    }, delimiter = ';'
    )
    @ParameterizedTest(name = "Testing parametrized option with CsvSource: {0}")
    void googleTest2(String testData, String expectedResult) {
        Selenide.open("https://www.google.com.ua/");
        $("[name='q']").setValue(testData).pressEnter();
        $$("#search a h3").first()
                .shouldHave(text(expectedResult));
    }


    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of("Selenide", false, List.of("1", "2", "3")),
                Arguments.of("JUnit", true, List.of("5", "6", "7"))
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Testing parametrized option with MethodSource: {0}, {1}, {2}")
    void googleTest3(String testData, boolean flag, List<String> list) {
        System.out.println("flag -> " + flag);
        System.out.println("list -> " + list.toString());

        Selenide.open("https://www.google.com.ua/");
        $("[name='q']").setValue(testData).pressEnter();
        $$("#search a h3").first()
                .shouldHave(text(testData));
    }
}
