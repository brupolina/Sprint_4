package ru.yandex.practikum.scooterTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.yandex.practikum.HomePageScooter;
import ru.yandex.practikum.OrderPageScooter;

import static ru.yandex.practikum.Resources.CONFIRM_HEADER;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String subway;
    private final String phoneNumber;
    private final String date;
    private final String rentalPeriod;
    private final String color;
    private final String comment;


    public OrderTest(String name, String surname, String address, String subway, String phoneNumber, String date, String rentalPeriod, String color, String comment) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.subway = subway;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.rentalPeriod = rentalPeriod;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getDateSetForOrder() {
        return new Object[][] {
                {"Роман", "Токунов", "Хорошевское шоссе, д. 34, к. 2", "Хорошевская", "89775688964", "02.06.2025", "сутки", "чёрный жемчуг", "Позвонить за полчаса до приезда"},
                {"Михаил", "Некрасов", "ул. Старокачаловская, д. 5, подъезд 2, этаж 7, кв. 65", "Бульвар Дмитрия Донского", "79683121564", "25.04.2025", "шестеро суток", "серая безысходность", "Не звонить в звонок, спит ребенок!"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    @Test
    public void OrderPositiveTest() {
        HomePageScooter objHomePage = new HomePageScooter(driver);
        objHomePage.clickUpOrderButton();
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        // Принять куки
        objOrderPage.acceptCookieButtonClick();
        // Позитивный сценарий оформления заказа
        objOrderPage.setName(name);
        objOrderPage.setSurname(surname);
        objOrderPage.setAddress(address);
        objOrderPage.setSubway(subway);
        objOrderPage.setPhoneNumber(phoneNumber);
        objOrderPage.clickOrderNextButton();
        objOrderPage.setDate(date);
        objOrderPage.setRentalPeriod(rentalPeriod);
        objOrderPage.setColor(color);
        objOrderPage.setComment(comment);
        objOrderPage.clickOrderCreateButton();
        objOrderPage.clickOrderConfirmButton();
        // Проверить открытие страницы с успешным созданием заказа
        objOrderPage.isPageOpen(objOrderPage.getConfirmHeader(), CONFIRM_HEADER);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}