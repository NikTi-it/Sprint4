import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
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

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {

    private WebDriver driver;

    private final int questionIndexNumber;
    private final int answerIndexNumber;
    private final String expectedText;

    public ImportantQuestionsTest(int questionIndexNumber, int answerIndexNumber, String expectedText) {
        this.questionIndexNumber = questionIndexNumber;
        this.answerIndexNumber = answerIndexNumber;
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {0, 0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {1, 1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {2, 2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {3, 3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {4, 4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {5, 5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {6, 6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {7, 7, "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @Test
    @Description("Проверка выпадающих списков в разделе 'Вопросы о важном' для Google Chrome")
    public void importantQuestionCheckChrome() {

        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        PageObjectMain pageObjectMain = new PageObjectMain(driver);
        pageObjectMain.clickOnQuestionButton(questionIndexNumber);
        String actualText = pageObjectMain.getAnswerText(answerIndexNumber);
        Assert.assertEquals("Ошибка, полученный текст: " + actualText + " -  не совпадает с ожидаемым: " + expectedText, expectedText, actualText);
    }

    @Test
    @Description("Проверка выпадающих списков в разделе 'Вопросы о важном' для Mozilla Firefox")
    public void importantQuestionCheckFireFox() {

        System.setProperty("webdriver.gecko.driver", "C:\\Users\\tft19\\WebDriverFireFox\\bin\\geckodriver.exe");//Пришлось указать путь к папке с драйвером, так как даже не смотря на указание корректной переменной в Path, без точного указания он не работал
        driver = new FirefoxDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");

        PageObjectMain pageObjectMain = new PageObjectMain(driver);
        pageObjectMain.clickOnQuestionButton(questionIndexNumber);
        String actualText = pageObjectMain.getAnswerText(answerIndexNumber);
        Assert.assertEquals("Ошибка, полученный текст: " + actualText + " -  не совпадает с ожидаемым: " + expectedText, expectedText, actualText);
    }

    @After
    public void Teardown() {
        driver.quit();
    }
}
