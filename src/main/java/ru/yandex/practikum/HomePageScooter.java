package ru.yandex.practikum;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePageScooter {

    private WebDriver driver;

    // Вопросы о важном
    // Сколько это стоит? И как оплатить?
    private By costQuestion = By.id("accordion__heading-0");
    // Хочу сразу несколько самокатов! Так можно?
    private By multipleScootersQuestion = By.id("accordion__heading-1");
    // Как рассчитывается время аренды?
    private By rentalPeriodQuestion = By.id("accordion__heading-2");
    // Можно ли заказать самокат прямо на сегодня?
    private By todayRentalQuestion = By.id("accordion__heading-3");
    // Можно ли продлить заказ или вернуть самокат раньше?
    private By modifyRentalQuestion = By.id("accordion__heading-4");
    // Вы привозите зарядку вместе с самокатом?
    private By includeChargerQuestion = By.id("accordion__heading-5");
    // Можно ли отменить заказ?
    private By orderCancellationQuestion = By.id("accordion__heading-6");
    // Я живу за МКАДом, привезёте?
    private By deliveryBeyondMkadQuestion = By.id("accordion__heading-7");

    // Ответы на вопросы о важном
    // Сутки — 400 рублей. Оплата курьеру — наличными или картой.
    private By costAnswer = By.id("accordion__panel-0");
    // Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.
    private By multipleScootersAnswer = By.id("accordion__panel-1");
    // Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
    private By rentalPeriodAnswer = By.id("accordion__panel-2");
    // Только начиная с завтрашнего дня. Но скоро станем расторопнее.
    private By todayRentalAnswer = By.id("accordion__panel-3");
    // Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
    private By modifyRentalAnswer = By.id("accordion__panel-4");
    // Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
    private By includeChargerAnswer = By.id("accordion__panel-5");
    // Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
    private By orderCancellationAnswer = By.id("accordion__panel-6");
    // Да, обязательно. Всем самокатов! И Москве, и Московской области.
    private By deliveryBeyondMkadAnswer = By.id("accordion__panel-7");

    // Верхняя кнопка Заказать
    private By UpOrderButton = By.xpath(".//button[text()='Заказать']");
    // Нижняя кнопка Заказать
    private By DownOrderButton = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button");

    public HomePageScooter(WebDriver driver) {
        this.driver = driver;
    }

    // Метод для клика на вопрос
    public void clickQuestion(By locator) {
        driver.findElement(locator).click();
    }

    // Метод для получения текста ответов
    public String getAnswerText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Кликнуть по кнопкам Заказать
    public void clickUpOrderButton() {
        driver.findElement(UpOrderButton).click();
    }

    public void clickDownOrderButton() {

        // Проскролить до появления кнопки
        WebElement bigButton = driver.findElement(DownOrderButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", bigButton);
        driver.findElement(DownOrderButton).click();
    }
}