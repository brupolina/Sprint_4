package ru.yandex.practikum;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.is;

public class HomePageScooter {

    private WebDriver driver;

    // Вопросы о важном
    // Сколько это стоит? И как оплатить?
    private By costQuestion = By.xpath(".//div[@class='accordion__item']");
    // Хочу сразу несколько самокатов! Так можно?
    private By multipleScootersQuestion = By.xpath(".//div[@class='accordion__item']");
    // Как рассчитывается время аренды?
    private By rentalPeriodQuestion = By.xpath(".//div[@class='accordion__item']");
    // Можно ли заказать самокат прямо на сегодня?
    private By todayRentalQuestion = By.xpath(".//div[@class='accordion__item']");
    // Можно ли продлить заказ или вернуть самокат раньше?
    private By modifyRentalQuestion = By.xpath(".//div[@class='accordion__item']");
    // Вы привозите зарядку вместе с самокатом?
    private By includeChargerQuestion = By.xpath(".//div[@class='accordion__item']");
    // Можно ли отменить заказ?
    private By orderCancellationQuestion = By.xpath(".//div[@class='accordion__item']");
    // Я живу за МКАДом, привезёте?
    private By deliveryBeyondMkadQuestion = By.xpath(".//div[@class='accordion__item']");

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
    public void clickQuestion(By locator) {
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
        return getAnswerText(modifyRentalAnswer);
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
    public String getAnswerText(By locator) {
        return driver.findElement(locator).getText();
    }

    // Сравнить ответы на вопросы о важном с ожидаемым ответом
    public void isCorrectText(String answer, String text) {
        MatcherAssert.assertThat(answer, is(text));
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