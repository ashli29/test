package selenide;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Browsers.FIREFOX;

public class Examples {

    @BeforeAll
    static void beforeAll(){
        Configuration.browser = FIREFOX;
        System.out.println("        Это метод beforeAll");
        //запускается перед всеми тестами. всегра статичный класс, сюда записывать конфигурации
    }

    @AfterAll
    static void afterAll(){
        System.out.println("        Это метод afterAll");
        //запускается после всех тестов
    }


    @BeforeEach
    void beforeEach(){
        System.out.println("***Это метод before***");
        //запускается перед каждым тестом
    }

    @AfterEach
    void afterEach(){
        System.out.println("---Это метод after---");
        //запускается после каждого теста
    }

    @Test
    void exmp(){
        System.out.println("This is test 11111");
        Assertions.assertTrue(3>2);
    }
    @Test
    void exmp2(){
        System.out.println("This is test 22222");
        Assertions.assertTrue(3>2);
    }
}
