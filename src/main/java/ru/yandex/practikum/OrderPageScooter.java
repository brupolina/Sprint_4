package ru.yandex.practikum;

import org.hamcrest.MatcherAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.is;

public class OrderPageScooter {

    private WebDriver driver;

    // Заголовки этапов заказа
    private By orderHeader = By.xpath(".//div[text()='Для кого самокат']");
    private By aboutOrderHeader = By.xpath(".//div[text()='Про аренду']");

    // Кнопка Принять куки
    private By acceptCookieButton = By.xpath(".//button[text()='да все привыкли']");

    // Имя
    private By nameField = By.xpath(".//input[@placeholder='* Имя']");
    // Фамилия
    private By surnameField = By.xpath(".//input[@placeholder='* Фамилия']");
    // Адрес: куда привезти заказ
    private By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    // Раскрывающийся список для выбора станции метро
    private By subwayField = By.xpath(".//input[@placeholder='* Станция метро']");
    // Телефон: на него позвонит курьер
    private By phoneNumberField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    // Кнопка Далее
    private By orderNextButton = By.xpath(".//button[text()='Далее']");

    // Когда привезти самокат
    private By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    // Раскрывающийся список для выбора срока аренды
    private By rentalPeriodField = By.xpath(".//div[@class='Dropdown-placeholder']");
    // Комментарий для курьера
    private By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    // Кнопка Заказать
    private By orderCreateButton = By.xpath("//div[contains(@class,'Order_Buttons')]/button[text()='Заказать']");

    // Кнопка подтверждения заказа
    private By orderConfirmButton = By.xpath(".//button[text()='Да']");
    // Кнопка Посмотреть статус
    private By confirmHeader = By.xpath(".//button[text()='Посмотреть статус']");

    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }

    // Получить текста заголовка страницы заказа
    public String getOrderHeader() {
        return driver.findElement(orderHeader).getText();
    }

    // Получить текст на кнопке для просмотра статуса заказа
    public String getConfirmHeader() {
        return driver.findElement(confirmHeader).getText();
    }

    // Открыть страницу
    public void isPageOpen(String headerText, String text) {
        MatcherAssert.assertThat(headerText, is(text));
    }

    // Принять куки
    public void acceptCookieButtonClick() {
        driver.findElement(acceptCookieButton).click();
    }
    // Заполнить поле Имя
    public void setName(String name) {
        driver.findElement(nameField).sendKeys(name);
    }
    // Заполнить поле Фамилия
    public void setSurname(String surname) {
        driver.findElement(surnameField).sendKeys(surname);
    }
    // Заполнить поле Адрес: куда привезти заказ
    public void setAddress(String address) {
        driver.findElement(addressField).sendKeys(address);
    }
    // Заполнить поле Станция метро
    public void setSubway(String subway) {
        driver.findElement(subwayField).click();
        driver.findElement(By.xpath(".//div[text()='"+subway+"']")).click();
    }
    // Заполнить поле Телефон: на него позвонит курьер
    public void setPhoneNumber(String phoneNumber) {
        driver.findElement(phoneNumberField).sendKeys(phoneNumber);
    }
    // Перейти ко второму этапу создания заказа
    public void clickOrderNextButton() {
        driver.findElement(orderNextButton).click();
    }
    // Заполнить поле Когда привезти самокат
    public void setDate(String date) {
        driver.findElement(dateField).sendKeys(date);
    }
    // Заполнить поле Срок аренды
    public void setRentalPeriod(String rentalPeriod) {
        driver.findElement(aboutOrderHeader).click();
        driver.findElement(rentalPeriodField).click();
        driver.findElement(By.xpath(".//div[text()='"+rentalPeriod+"']")).click();
    }
    // Заполнить поле Цвет самоката
    public void setColor(String color) {
        driver.findElement(By.xpath(".//label[text()='"+color+"']")).click();
    }
    // Заполнить поле Комментарий для курьера
    public void setComment(String comment) {
        driver.findElement(commentField).sendKeys(comment);
    }
    // Нажать кнопку Заказать
    public void clickOrderCreateButton() {
        driver.findElement(orderCreateButton).click();
    }
    // Подтвердить заказ
    public void clickOrderConfirmButton() {
        driver.findElement(orderConfirmButton).click();
    }
}
