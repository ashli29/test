package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestQA {

    @BeforeAll
    static void beforeAll(){
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        //запускается перед всеми тестами. всегра статичный класс, сюда записывать конфигурации
    }

    @Test
    void formTest(){
        open("/text-box");
        $(".text-center").shouldHave(text("Text Box"));
        $("#userName").setValue("Alex Egorov");
        $("#userEmail").setValue("test@test.com");
        $("#currentAddress").setValue("Минск, ул, Машерова, 36");
        $("#permanentAddress").setValue("тест тест тест тест");
        $("#submit").click();

        $("#output").shouldBe(visible);
        $("#output").$("#name").shouldHave(text("Alex Egorov"));
        $("#output").$("#email").shouldHave(text("test@test.com"));
        $("#output #currentAddress").shouldHave(text("Минск, ул, Машерова, 36"));
        $("#output #permanentAddress").shouldHave(text("тест тест тест тест"));
    }
}
