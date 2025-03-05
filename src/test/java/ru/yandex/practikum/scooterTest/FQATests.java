package ru.yandex.practikum.scooterTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practikum.HomePageScooter;

import java.util.Arrays;
import java.util.Collection;

import static ru.yandex.practikum.Resources.*;

@RunWith(Parameterized.class)
public class FQATests {

    private WebDriver driver;
    private HomePageScooter objHomePage;

    // Параметр для передачи в тест
    private final By questionMethod;
    private final String answerMethod;
    private final String expectedText;

    // Конструктор для получения параметров
    public FQATests(By questionMethod, String answerMethod, String expectedText) {
        this.questionMethod = questionMethod;
        this.answerMethod = answerMethod;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"clickCostQuestion", "getCostAnswer", COST_ANSWER},
                {"clickMultipleScootersQuestion", "getMultipleScootersAnswer", MULTIPLE_SCOOTERS_ANSWER},
                {"clickRentalPeriodQuestion", "getRentalPeriodAnswer", RENTAL_PERIOD_ANSWER},
                {"clickTodayRentalQuestion", "getTodayRentalAnswer", TODAY_RENTAL_ANSWER},
                {"clickModifyRentalQuestion", "getModifyRentalAnswer", MODIFY_RENTAL_ANSWER},
                {"clickIncludeChargerQuestion", "getIncludeChargerAnswer", INCLUDE_CHARGER_ANSWER},
                {"clickOrderCancellationQuestion", "getOrderCancellationAnswer", ORDER_CANCELLATION_ANSWER},
                {"clickDeliveryBeyondMkadQuestion", "getDeliveryBeyondMkadAnswer", DELIVERY_BEYOND_MKAD_ANSWER}
        });
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");

        // Перемещение к секции FAQ
        scrollToFAQSection();

        objHomePage = new HomePageScooter(driver);
    }

    private void scrollToFAQSection() {
        ((JavascriptExecutor)driver).executeScript(
                "document.querySelector('.accordion').scrollIntoView();"
        );
    }

    @Test
    public void QACorrectAnswerTextTest() {
        try {
            // Шаг 1: Кликаем по вопросу
            objHomePage.clickQuestion(questionMethod);

            // Шаг 2: Получаем текст ответа
            String actualText = objHomePage.getAnswerText(By.id(answerMethod));

            // Проверка: сравниваем ожидаемый и фактический текст
            if (!actualText.equals(expectedText)) {
                throw new AssertionError("Текст ответа не совпадает с ожидаемым\n" +
                        "Ожидаемый: " + expectedText + "\n" +
                        "Фактический: " + actualText);
            }
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при выполнении теста: " + e.getMessage());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}

