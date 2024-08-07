package demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Browsers.FIREFOX;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

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

    @Test
    void hw(){
        String firstName = "Alex";
        String lastName = "Egorov";
        String userEmail = "test@qa.com";
        String gender = "Male";
        String userNumber = "1234567890";
        String month = "March";
        String year = "1995";
        String currentAddress = "Минск, ул. Машерова 43";
        String importFile = "readme.txt";

        open("/automation-practice-form");
        $(".text-center").shouldHave(text("Practice Form"));

        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--029").click();
        $("#subjectsContainer").click();
        $("#subjectsInput").sendKeys(("e"));
        $("#react-select-2-option-0").click();
        $(byText("Sports")).click();
        $(byText("Music")).click();
        File file = new File("src/test/resources/" + importFile);
        $(".form-control-file").uploadFile(file);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#react-select-3-option-1").click();
        $("#city").click();
        $("#react-select-4-option-0").click();
        $("#submit").click();

        $(".modal-content").shouldHave(text("Thanks for submitting the form"));
        $(".table-responsive").shouldHave(text(firstName), text(lastName), text(userEmail), text(gender),
                text(userNumber), text("29"), text(month), text(year), text("English"), text("Sports, Music"),
                text(importFile), text(currentAddress), text("Uttar Pradesh"), text("Agra"));
        
    }
}
