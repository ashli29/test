package examplesTests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
//import static com.codeborne.selenide.Selenide.*;

public class BestContributor {
    @Test
    void solntsevShouldBeThe (){
        //Configuration.browserSize="800x400";
        //открыть страницу репозитория селенида
        open("https://github.com/selenide/selenide");
        // подвести мышку к первому аватару из блока contributors
        $(".BorderGrid").$(byText("Contributors")).ancestor(".BorderGrid-row").$$("ul li")
                .first().hover();
        // проверка: во вспылываеющем окне есть текст
        $$(".Popover .Popover-message").findBy(visible).shouldHave(text("Andrei Solntsev"));
        open("sfddfj/sf");


    }
}
