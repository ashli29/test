package selenide;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class Snippets {

    void browserCommandExamples(){

        open("https://google.com");
        open("/custom/orders");
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials
                ("", "user", "password"));

        Selenide.back(); //для возврата назад в браузере
        Selenide.refresh(); //обновить страницу браузера

        Selenide.clearBrowserCookies(); //очистить куки
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();");
        //для разлогинивания почистить все куки, лакал сторадж, в джава скрипте почистить и обновить страницу

        Selenide.confirm(); //нажать ок на поп-апе аллерте
        Selenide.dismiss(); //нажать отмену на аллерте

        Selenide.closeWindow(); //закрывает текущее окно
        Selenide.closeWebDriver(); //закрывает браузер целиком

        Selenide.switchTo().window("The Internet"); //перейти к нужному окну браузера

        //установить куки
        var cookie = new Cookie("foo", "bar");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie); //потом перзагрузить страницу


    }
    void selectors_examples(){
        $("div").click();//$ в котлине нельзя
        element("div").click();//одно и тоже

        $("div", 2).click(); //третий див

        $x("//h/div").click();
        $("div").$x(".//h1");
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click();// искать по содержимому внутри тега
        $(withText("ull te")).click(); //искать по частичному совпадению

        $(byTagAndText("div", "full text"));
        $(withTagAndText("div", "ull text"));

        $("").parent();//родитель
        $("").sibling(1);//ищут братьем и сестрер вниз
        $("").preceding(1);//ищут братьем и сестрер вверх
        $("").closest("div");//вернуться к верхнему диву
        $("").ancestor("div");// -//-
        $("div:last-child");//берет последнего ребенка в диве

        $("div").$("h1").find(byText("abc")).click();//$ и find одно и тоже, но с него нельза начать
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();

        $(byId("myText")).click();
        $("#mytext").click();

        $(byClassName("red")).click();
        $(".red").click();
    }
    void actions_examples(){
        $("").click();
        $("").doubleClick();
        $("").contextClick(); //правый клик мыши

        $("").hover(); //поднести мышку и не кликать

        $("").setValue("text");//удалит текст и напишет заново
        $("").append("text");//добавит в конец
        $("").clear();//может не сработать с некоторыми фреймворками
        $("").setValue("");//clear

        $("div").sendKeys(("c"));//эмуляция нажатия клавиши
        actions().sendKeys("c").perform();//нажать на кливишу без привязок к элементу
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); //ctrl и f
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));//на главном элементе

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();

        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();
        //переместить объект

        $("").selectOption("dropdown_options");//рабоатет со страрыми дропдаунами
        $("").selectRadio("radio_options");//рабоатет со страрыми радиобаттонами
    }
    void assertions_examples(){
        // проверки. таймаут 4 секунды
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        //удлиннить таймаут
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }
    void conditions_examples(){
        $("").shouldBe(visible);//видит пользователь или нет элемент
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactOwnTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));//проверка на регулярку

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssClass("red"), cssClass("black"));
        $("").shouldHave(cssValue("font-size", "12"));//свойства элемента

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);// проверка на пустое поле

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked);//включен ли чекбокс. для не включенного NotBe

        $("").should(exist);//есть ли спрятанные элементы

        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
        //если нет явного атрибута проверки задизейбленного элемента, то искать по классу shouldHave cssClass
        // или поискать по cssValue
    }
    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));//фильтрация
        $$("div").excludeWith(text("123")).shouldHave(size(1));//ищет те элементы которые не удовлетворяют условию

        $$("div").first().click();//перый элемент
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();//последний элемента
        $$("div").get(1).click(); // the second! (start with 0) элемент по номеру
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first комбинация filterBy и first

        // assertions
        $$("").shouldHave(size(0));//пустая коллекция
        $$("").shouldBe(CollectionCondition.empty); // the same. то же самое

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));//точное количество элементов. тексты проверяются по вхождению
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));//точные тексты

        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));//если не важен порядок текста
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));//с учетом регистра

        $$("").shouldHave(itemWithText("Gamma")); // only one text проверка что в коллекции есть этот элемент

        $$("").shouldHave(sizeGreaterThan(0));//больше 0
        $$("").shouldHave(sizeGreaterThanOrEqual(1));//больше или равно 1
        $$("").shouldHave(sizeLessThan(3));//меньше 3
        $$("").shouldHave(sizeLessThanOrEqual(2));//меньше или равно 2
    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links работает на простых линках
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");//запускает любую команду джава скрипта
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);//работа с аргументами
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);//вычисление значения

    }
}
