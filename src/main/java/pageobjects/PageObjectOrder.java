package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class PageObjectOrder {

    private final WebDriver driver;

    public PageObjectOrder(WebDriver driver) {
        this.driver = driver;
    }

    //Локатор поля "Имя"
    private final By inputFirstName = By.xpath(".//input[@placeholder= '* Имя']");

    //Локатор поля "Фамилия"
    private final By inputLastName = By.xpath(".//input[@placeholder= '* Фамилия']");

    //Локатор поля "Адрес"
    private final By inputAddress = By.xpath(".//input[@placeholder= '* Адрес: куда привезти заказ']");

    //Локатор поля "Метро"
    private final By inputMetro = By.xpath(".//input[@placeholder= '* Станция метро']");

    //Локатор поля "Телефон"
    private final By inputPhone = By.xpath(".//input[@placeholder= '* Телефон: на него позвонит курьер']");

    //Локатор кнопки "Далее"
    private final By nextButton = By.xpath(".//div[starts-with(@class,'Order_NextButton')]//button[contains(text(), 'Далее')]");

    //Локатор поля "Календарь"
    private final By inputCalendar = By.xpath(".//input[@placeholder= '* Когда привезти самокат']");

    //Локатор поля "Срок аренды"
    private final By rentTime = By.className("Dropdown-control");

    //Локатор поля "Комментарий"
    private final By inputComment = By.xpath(".//input[@placeholder='Комментарий для курьера']");

    //Локатор кнопки "Заказать"
    private final By orderButton = By.xpath(".//div[starts-with(@class,'Order_Buttons')]//button[contains(text(), 'Заказать')]");

    //Локатор кнопки "Да"
    private final By yesButton = By.xpath("//button[contains(text(), 'Да')]");

    //Локатор окна, указывающего, что заказ оформлен
    private final By modalOrderPlaced = By.xpath(".//*[text()='Заказ оформлен']");




    //Поиск и заполнение поля "Имя"
    public void inputName(String firstName) {
        driver.findElement(inputFirstName).sendKeys(firstName);
    }

    //Поиск и заполнение поля "Фамилия"
    public void inputLastname(String lastName) {
        driver.findElement(inputLastName).sendKeys(lastName);
    }

    //Поиск и заполнение поля "Адрес"
    public void inputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    //Поиск и заполнение поля "Метро"
    public void inputMetro(String station) {
        driver.findElement(inputMetro).sendKeys(station);
        driver.findElement(inputMetro).sendKeys(Keys.ARROW_DOWN);
        driver.findElement(inputMetro).sendKeys(Keys.ENTER);
    }

    //Поиск и заполнение поля "Телефон"
    public void inputPhone(String phone) {
        driver.findElement(inputPhone).sendKeys(phone);
    }

    //Поиск и нажатие кнопки "Далее"
    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    //Поиск и заполнение поля "Календарь"
    public void inputCalendar(String date) {
        driver.findElement(inputCalendar).sendKeys(date);
        driver.findElement(inputCalendar).sendKeys(Keys.ENTER);
    }

    //Поиск и заполнение поля "Срок аренды"
    public void inputTimeRent(String rent) {
        driver.findElement(rentTime).click();
        driver.findElement(By.xpath(".//div[text()='" + rent + "']")).click();
    }

    //Поиск и нажатие элементов выбора цвета самоката
    public void colorInput(String color) {
        if (color.equals("серая безысходность")) {
            driver.findElement(By.id("grey")).click();
        } else {
            driver.findElement(By.id("black")).click();
        }
    }

    //Поиск и заполнение поля комментария
    public void inputComments(String comment) {
        driver.findElement(inputComment).sendKeys(comment);
    }

    //Поиск и нажатие кнопки "Заказать"
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }

    //Поиск и нажатие кнопки "Да"
    public void clickYesButton() {
        driver.findElement(yesButton).click();
    }

    //Поиск и получение текста из окна об успешном оформлении заказа
    public String checkPlacedOrder() {
        return driver.findElement(modalOrderPlaced).getText();
    }

    //Выполнение заполнения форм при оформлении заказа
    public void makeOrder(String firstName, String lastName, String address, String station, String phone, String date, String rent, String color, String comment) {
        inputName(firstName);
        inputLastname(lastName);
        inputAddress(address);
        inputMetro(station);
        inputPhone(phone);
        clickNextButton();
        inputCalendar(date);
        inputTimeRent(rent);
        colorInput(color);
        inputComments(comment);
        clickOrderButton();
        clickYesButton();
    }
}
