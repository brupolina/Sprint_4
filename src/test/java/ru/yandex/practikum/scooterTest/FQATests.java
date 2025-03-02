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

// Page Object класс
class HomePageScooter {
    private WebDriver driver;

    // Локаторы вопросов
    private final By costQuestion = By.xpath("//div[contains(.,'Сколько стоит прокат')]");
    private final By multipleScootersQuestion = By.xpath("//div[contains(.,'Можно ли взять несколько самокатов')]");
    private final By rentalPeriodQuestion = By.xpath("//div[contains(.,'На какой срок можно взять')]");
    private final By todayRentalQuestion = By.xpath("//div[contains(.,'Можно ли оформить прокат сегодня')]");
    private final By modifyRentalQuestion = By.xpath("//div[contains(.,'Можно ли изменить срок проката')]");
    private final By includeChargerQuestion = By.xpath("//div[contains(.,'В стоимость аренды входит зарядка')]");
    private final By orderCancellationQuestion = By.xpath("//div[contains(.,'Как отменить заказ')]");
    private final By deliveryBeyondMkadQuestion = By.xpath("//div[contains(.,'Доставляют ли самокаты за МКАД')]");

    // Локаторы ответов
    private final By costAnswer = By.xpath("//div[contains(.,'Стоимость проката зависит')]");
    private final By multipleScootersAnswer = By.xpath("//div[contains(.,'Да, вы можете взять')]");
    private final By rentalPeriodAnswer = By.xpath("//div[contains(.,'От суток до месяца')]");
    private final By todayRentalAnswer = By.xpath("//div[contains(.,'Оформить прокат можно')]");
    private final By modifyRentalQuestionAnswer = By.xpath("//div[contains(.,'Да, вы можете изменить')]");
    private final By includeChargerAnswer = By.xpath("//div[contains(.,'Да, базовая зарядка')]");
    private final By orderCancellationAnswer = By.xpath("//div[contains(.,'Отменить заказ можно')]");
    private final By deliveryBeyondMkadAnswer = By.xpath("//div[contains(.,'Доставка доступна')]");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // Методы для клика по вопросам
    public void clickCostQuestion() {
        clickQuestion(costQuestion);
    }

    public void clickMultipleScootersQuestion() {
        clickQuestion(multipleScootersQuestion);
    }

    public void clickRentalPeriodQuestion() {
        clickQuestion(rentalPeriodQuestion);
    }

    public void clickTodayRentalQuestion() {
        clickQuestion(todayRentalQuestion);
    }

    public void clickModifyRentalQuestion() {
        clickQuestion(modifyRentalQuestion);
    }

    public void clickIncludeChargerQuestion() {
        clickQuestion(includeChargerQuestion);
    }

    public void clickOrderCancellationQuestion() {
        clickQuestion(orderCancellationQuestion);
    }

    public void clickDeliveryBeyondMkadQuestion() {
        clickQuestion(deliveryBeyondMkadQuestion);
    }

    // Вспомогательный метод для клика
    void clickQuestion(By locator) {
        driver.findElement(locator).click();
    }

    // Методы для получения текста ответов
    public String getCostAnswer() {
        return getAnswerText(costAnswer);
    }

    public String getMultipleScootersAnswer() {
        return getAnswerText(multipleScootersAnswer);
    }

    public String getRentalPeriodAnswer() {
        return getAnswerText(rentalPeriodAnswer);
    }

    public String getTodayRentalAnswer() {
        return getAnswerText(todayRentalAnswer);
    }

    public String getModifyRentalAnswer() {
        return getAnswerText(modifyRentalQuestionAnswer);
    }

    public String getIncludeChargerAnswer() {
        return getAnswerText(includeChargerAnswer);
    }

    public String getOrderCancellationAnswer() {
        return getAnswerText(orderCancellationAnswer);
    }

    public String getDeliveryBeyondMkadAnswer() {
        return getAnswerText(deliveryBeyondMkadAnswer);
    }

    // Вспомогательный метод для получения текста
    String getAnswerText(By locator) {
        return driver.findElement(locator).getText();
    }

}
