package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObjectMain {

    private final WebDriver driver;

    public PageObjectMain(WebDriver driver) {
        this.driver = driver;
    }


    //Локатор для верхней кнопки "Заказать"
    private final By upperOrderButton = By.xpath(".//button[@class = 'Button_Button__ra12g']");
    //Локатор для нижней кнопки "Заказать"
    private final By lowerOrderButton = By.xpath(".//button[@class= 'Button_Button__ra12g Button_Middle__1CSJM']");
    //Локатор для кнопки "Да все привыкли"
    private final By acceptCookie = By.xpath (".//button[@class= 'App_CookieButton__3cvqF']");


    //Поиск и нажатие на кнопку выпадающего списка в разделе "Вопросы о важном"
    public void clickOnQuestionButton(int questionIndexNumber) {
        WebElement element = driver.findElement(By.xpath(".//div[@id='accordion__heading-" + questionIndexNumber + "']")); //Локатор для выпадающих списков в разделе "Вопросы о важном"
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    //Поиск и получение текста в выпадающем списке в разделе "Вопросы о важном"
    public String getAnswerText(int answerIndexNumber) {
        WebElement element = driver.findElement(By.xpath(".//div[@id='accordion__panel-" + answerIndexNumber + "']"));//Локатор для поиска ответа на вопрос в выпадающем списке в разделе "Вопросы о важном"
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
        return element.getText();
    }

    //Поиск и нажатие на верхнюю кнопку "Заказать"
    public void clickUpperOrderButton( ) {
        driver.findElement(upperOrderButton).click();
    }

    //Поиск и нажатие на нижнюю кнопку "Заказать"
    public void clickLowerOrderButton( ) {
        WebElement element = driver.findElement(lowerOrderButton);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
    }

    //Поиск и нажатие на кнопку "Да все привыкли"
    public void clickAcceptCookies() {
        driver.findElement(acceptCookie).click();
    }
}
