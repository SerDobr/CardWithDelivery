import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class CardWithDeliveryTest {
    //    private WebDriver driver;
//
    @BeforeAll
    static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }


    //    @BeforeEach //Selenium
//    public void setUp() {
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--disable-dev-shm-usage");
//        options.addArguments("--no-sandbox");
//        options.addArguments("--headless");
//        driver = new ChromeDriver(options);
//    }
//
//    @AfterEach
//    void tearDown() {
//        driver.quit();
//        driver=null;
//    }
    String date = LocalDate.now().plusDays(4).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));// Установка даты через 3 дня

    @Test
    void applicationDebitCard() {

        Configuration.holdBrowserOpen = true; // Оставляет открытый браузер.
//      Configuration.browserSize="600x800"; //Размер открытого браузера.
//        Configuration.timeout= Long.parseLong("15");// Время загрузки
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Казань");//Установить город
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");// Выделить строку
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);// Удалить все из строки
        $("[data-test-id=date] input").setValue(date);// Установка даты
        $("[data-test-id=name] input").setValue("Добровольский Сергей"); // Установка ФИО
        $("[data-test-id=phone] input").setValue("+79040276423");// Номер телефона
        $("[data-test-id=agreement]").click();// Установить чек бокс
        $(".button").click();// Нажать продолжить
        $x("//*[contains(@class,'notification_visible')]").should(visible, Duration.ofSeconds(15));// Выбираем что видим поле и время задержки 15 сек


//        driver.findElement(By.cssSelector("[data-test-id=city] input")).sendKeys("Казань");
//        driver.findElement(By.cssSelector("[data-test-id=date] input")).sendKeys("25.03.2023");
//        driver.findElement(By.cssSelector("[data-test-id=name] input")).sendKeys("Добровольский Сергей");
//        driver.findElement(By.cssSelector("[data-test-id=phone] input")).sendKeys("+79040276423");
//        driver.findElement(By.cssSelector("[data-test-id=agreement]")).click();
//        driver.findElement(By.cssSelector("button_view_extra")).click();
//        String expected = "Успешно!";
//        String actual = driver.findElement(By.cssSelector("notification__title")).getText();
//        Assertions.assertEquals(expected, actual);

    }
}
