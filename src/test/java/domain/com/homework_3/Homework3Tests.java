package domain.com.homework_3;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class Homework3Tests {

    /**
     * 1. Есть ли разница между $("h1 div"); и $("h1").$("div"); - может ли привести к тому что, поиск найдёт разные элементы?
     * Если может - приведите пример, когда.
     *
     * Ответ:
     *  Да, может быть ситуация, когда эти два локатора найдут разные элмементы (div блоки). Как пример:
     *  $("h1 div") -> этот локатор ищет первый вложенный div внутри h1 не только первого уровня вложенности,
     *  но и первый div всех последующих вложенностей, а это подход -  $("h1").$("div") означает, что первый div будет искаться
     *  исключительно среди вложенных элементов внутри h1 только непосредственно на первом уровне вложенности.
     *  По сути $("h1").$("div") это аналог CSS селектора $("h1 > div").
     */


    /**
     * 2. Разработайте следующий автотест:
     * - Откройте страницу Selenide в Github
     * - Перейдите в раздел Wiki проекта
     * - Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
     * - Откройте страницу SoftAssertions, проверьте что внутри есть пример кода для JUnit5
     */

    @Test
    void shouldBeJunitSnippet() {
        open("https://github.com/selenide/selenide");
        $("#wiki-tab").click();
        $("#wiki-pages-filter").setValue("SoftAssertions");
        $(By.linkText("SoftAssertions")).shouldBe(visible).click();
        $$(".markdown-body > ol").findBy(text("Using JUnit5 extend test class:")).sibling(0)
                .shouldBe(visible);
    }


    /**
     * 3. (опциональное) Запрограммируйте Drag&Drop с помощью Selenide.actions()
     * - Откройте https://the-internet.herokuapp.com/drag_and_drop
     * - Перенесите прямоугольник А на место В
     * - Проверьте, что прямоугольники действительно поменялись
     */

    @Test
    void dragAndDropWithSelenide() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        $$("#columns > div").first().shouldHave(text("A"));
        $("#column-a").dragAndDropTo("#column-b");
        $$("#columns > div").first().shouldHave(text("B"));
    }


    // This option doesn't work. Block A is moved, but not released to target block B.
    @Test
    void dragAndDropSecondOptionWithSelenide() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement a = $("#column-a");
        WebElement b = $("#column-b");
        $$("#columns > div").first().shouldHave(text("A"));
        Selenide.actions().dragAndDrop(a, b).perform();
        $$("#columns > div").first().shouldHave(text("B"));
    }

    // This option doesn't work as well
    @Test
    void dragAndDropSelenium() {
        open("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement a = $("#column-a");
        WebElement b = $("#column-b");
        $$("#columns > div").first().shouldHave(text("A"));
        new Actions(WebDriverRunner.getWebDriver()).clickAndHold(a).moveToElement(b).release().perform();
        $$("#columns > div").first().shouldHave(text("B"));
    }
}
