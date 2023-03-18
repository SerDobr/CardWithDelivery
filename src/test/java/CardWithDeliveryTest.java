import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class CardWithDeliveryTest {

    public String generateDate(int days) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    String date = generateDate(3);// Установка даты через 3 дня

    @Test
    void applicationDebitCard() {

        Configuration.holdBrowserOpen = true; // Оставляет открытый браузер.
        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Казань");//Установить город
        $("[data-test-id=date] input").sendKeys(Keys.CONTROL + "A");// Выделить строку
        $("[data-test-id=date] input").sendKeys(Keys.DELETE);// Удалить все из строки
        $("[data-test-id=date] input").setValue(date);// Установка даты
        $("[data-test-id=name] input").setValue("Добровольский Сергей"); // Установка ФИО
        $("[data-test-id=phone] input").setValue("+79040276423");// Номер телефона
        $("[data-test-id=agreement]").click();// Установить чек бокс
        $(".button").click();// Нажать продолжить
        $x("//*[contains(@class,'notification_visible')]")
                .should(visible, Duration.ofSeconds(15));// Выбираем что видим поле и время задержки 15 сек
        $(".notification__content")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + date), Duration.ofSeconds(15))
                .shouldBe(Condition.visible);

    }
}
