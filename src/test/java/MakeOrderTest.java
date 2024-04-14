import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.PageObjectMain;
import pageobjects.PageObjectOrder;

import static org.hamcrest.CoreMatchers.containsString;

@RunWith(Parameterized.class)
public class MakeOrderTest {

    private WebDriver driver;

    private final int indexButton;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final String station;
    private final String phone;
    private final String date;
    private final String rent;
    private final String color;
    private final String comment;

    public MakeOrderTest(int indexButton, String firstName, String lastName, String address, String station, String phone, String date, String rent, String color, String comment) {
        this.indexButton = indexButton;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.date = date;
        this.rent = rent;
        this.color = color;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0,"Никита", "Титухин", "Кутузовский 32", "Бульва Рокоссовского", "89116966969", "05.05.2024", "сутки", "серая безысходность", "Коммент без логики"},
                {1,"Уолтер", "Хайзенберг", "Прекрасное далеко", "Красносельская", "79649696969", "01.01.2024", "четверо суток", "черный жемчуг", ""}};
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    @Description("Проверка оформления заказа по нижней кнопке 'Заказать' в Google Chrome")
    public void orderCheckChrome() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        PageObjectMain pageObjectMain = new PageObjectMain(driver);
        pageObjectMain.clickAcceptCookies();

        if (indexButton == 0) {
            pageObjectMain.clickUpperOrderButton();
        } else {
            pageObjectMain.clickLowerOrderButton();
        }

        PageObjectOrder pageObjectOrder = new PageObjectOrder(driver);
        pageObjectOrder.makeOrder(firstName, lastName, address, station, phone, date, rent, color, comment);
        Assert.assertEquals("Ошибка, заказ не был оформлен", pageObjectOrder.checkPlacedOrder(),"Заказ оформлен");
    }

    @Test
    @Description("Проверка оформления заказа по нижней кнопке 'Заказать' в Mozilla Firefox")
    public void orderCheckFireFox() {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\tft19\\WebDriverFireFox\\bin\\geckodriver.exe");//Пришлось указать путь к папке с драйвером, так как даже не смотря на указание корректной переменной в Path, без точного указания он не работал
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        PageObjectMain pageObjectMain = new PageObjectMain(driver);
        pageObjectMain.clickAcceptCookies();

        if (indexButton == 0) {
            pageObjectMain.clickUpperOrderButton();
        } else {
            pageObjectMain.clickLowerOrderButton();
        }

        PageObjectOrder pageObjectOrder = new PageObjectOrder(driver);
        pageObjectOrder.makeOrder(firstName, lastName, address, station, phone, date, rent, color, comment);
        MatcherAssert.assertThat("Ошибка, заказ не был оформлен", pageObjectOrder.checkPlacedOrder(),containsString("Заказ оформлен"));
    }

    @After
    public void Teardown() {
        driver.quit();
    }
}
