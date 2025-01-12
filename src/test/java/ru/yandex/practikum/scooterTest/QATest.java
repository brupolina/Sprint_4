package ru.yandex.practikum.scooterTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practikum.HomePageScooter;

import static ru.yandex.practikum.Resources.*;

public class QATest {

    private WebDriver driver;

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void QACorrectAnswerText() {
        WebElement tableFAQ = driver.findElement(By.xpath(".//div[@class='accordion']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", tableFAQ);
        HomePageScooter objHomePage = new HomePageScooter(driver);

        // Проверка текста в ответах на вопросы о важном
        objHomePage.clickQuestion1();
        objHomePage.isCorrectText(objHomePage.getAnswer1(), ANSWER_1);

        objHomePage.clickQuestion2();
        objHomePage.isCorrectText(objHomePage.getAnswer2(), ANSWER_2);

        objHomePage.clickQuestion3();
        objHomePage.isCorrectText(objHomePage.getAnswer3(), ANSWER_3);

        objHomePage.clickQuestion4();
        objHomePage.isCorrectText(objHomePage.getAnswer4(), ANSWER_4);

        objHomePage.clickQuestion5();
        objHomePage.isCorrectText(objHomePage.getAnswer5(), ANSWER_5);

        objHomePage.clickQuestion6();
        objHomePage.isCorrectText(objHomePage.getAnswer6(), ANSWER_6);

        objHomePage.clickQuestion7();
        objHomePage.isCorrectText(objHomePage.getAnswer7(), ANSWER_7);

        objHomePage.clickQuestion8();
        objHomePage.isCorrectText(objHomePage.getAnswer8(), ANSWER_8);

    }

    @After
    public void tearDown() {
        driver.quit();
    }

}
