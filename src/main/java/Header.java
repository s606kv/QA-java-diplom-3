import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    /// Локаторы
    // кнопка "Конструктор"
    private static final By HEADER_CONSTRUCTOR_BUTTON = By.xpath(".//p[contains (text(), 'Конструктор')]");
    // кнопка с логотипом
    private static final By HEADER_LOGO = By.className("AppHeader_header__logo__2D0X2");
    // кнопка "Личный кабинет"
    private static final By HEADER_PERSONAL_CABINET_BUTTON = By.xpath(".//p[contains (text(), 'Личный Кабинет')]");

    /// Конструктор
    WebDriver driver;
    public Header (WebDriver driver) {
        this.driver=driver;
    }

    /// Методы
    // клик по кнопке "Конструктор"
    public void constructorButtonClick () {
        driver.findElement(HEADER_CONSTRUCTOR_BUTTON).click();
    }
    // клик по логотипу
    public void logoClick () {
        driver.findElement(HEADER_LOGO).click();
    }
    // клик по кнопке "Личный кабинет"
    public void personalCabinetButtonClick () {
        driver.findElement(HEADER_PERSONAL_CABINET_BUTTON).click();
    }

}
