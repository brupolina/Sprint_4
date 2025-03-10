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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practikum.HomePageScooter;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.Collection;

import static ru.yandex.practikum.Resources.*;

@RunWith(Parameterized.class)
public class FQATests {

    private WebDriver driver;
    private HomePageScooter objHomePage;
    private WebDriverWait wait;
    // Параметр для передачи в тест
    private final By questionMethod;
    private final By answerMethod;
    private final String expectedText;

    // Конструктор для получения параметров
    public FQATests(By questionMethod, By answerMethod, String expectedText) {
        this.questionMethod = questionMethod;
        this.answerMethod = answerMethod;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {By.id("accordion__heading-0"), By.id("accordion__panel-0"), COST_ANSWER},
                {By.id("accordion__heading-1"), By.id("accordion__panel-1"), MULTIPLE_SCOOTERS_ANSWER},
                {By.id("accordion__heading-2"), By.id("accordion__panel-2"), RENTAL_PERIOD_ANSWER},
                {By.id("accordion__heading-3"), By.id("accordion__panel-3"), TODAY_RENTAL_ANSWER},
                {By.id("accordion__heading-4"), By.id("accordion__panel-4"), MODIFY_RENTAL_ANSWER},
                {By.id("accordion__heading-5"), By.id("accordion__panel-5"), INCLUDE_CHARGER_ANSWER},
                {By.id("accordion__heading-6"), By.id("accordion__panel-6"), ORDER_CANCELLATION_ANSWER},
                {By.id("accordion__heading-7"), By.id("accordion__panel-7"), DELIVERY_BEYOND_MKAD_ANSWER}
        });
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
        wait = new WebDriverWait(driver, 3);
        objHomePage = new HomePageScooter(driver);

        // Перемещение к секции FAQ
        scrollToFAQSection();
    }

    private void scrollToFAQSection() {
        ((JavascriptExecutor) driver).executeScript(
                "document.querySelector('.accordion').scrollIntoView();"
        );
    }

    @Test
    public void QACorrectAnswerTextTest() {
        WebElement questionElement = wait.until(ExpectedConditions.presenceOfElementLocated(questionMethod));
        questionElement.click();
        // Шаг 1: Кликаем по вопросу
        objHomePage.clickQuestion(questionMethod);

        // Шаг 2: Получаем текст ответа
        String actualText = objHomePage.getAnswerText(answerMethod);

        // Проверка: сравниваем ожидаемый и фактический текст
        assert actualText.equals(expectedText) :
                "Текст ответа не совпадает с ожидаемым\n" +
                        "Ожидаемый: " + expectedText + "\n" +
                        "Фактический: " + actualText;
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}