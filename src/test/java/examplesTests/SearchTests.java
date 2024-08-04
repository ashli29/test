package examplesTests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SearchTests {
    @Test
    void successfulSearchTest() {
        open("https://www.google.com/");
        $("[name=q]").setValue("selenide").pressEnter();
        $("[id=search]").shouldHave(text("https://selenide.org"));
    }
    @Test
    void testNew() {
        open("https://github.com/");
        //Selenide.$("[aria-label=Search or jump to…]").click();
        $(By.className("header-search-button")).click();
        $("[id=query-builder-test]").setValue("selenide").pressEnter();
        $$(By.cssSelector("div.Box-sc-g0xbh4-0.kXssRI div")).first().$(By.cssSelector("a")).click();
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }
}
