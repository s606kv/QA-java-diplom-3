import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    /// Конструктор
    WebDriver driver;
    public Header (WebDriver driver) {
        this.driver=driver;
    }

    /// Локаторы
    // кнопка "Конструктор"
    public static final By HEADER_CONSTRUCTOR_BUTTON = By.xpath(".//p[contains (text(), 'Конструктор')]");
    // кнопка с логотипом
    public static final By HEADER_LOGO = By.className("AppHeader_header__logo__2D0X2");
    // кнопка "Личный кабинет"
    private static final By HEADER_PERSONAL_CABINET_BUTTON = By.xpath(".//p[contains (text(), 'Личный Кабинет')]");

    /// Шаги
    @Step("**Хэдер сервиса**. Клик по кнопке \"Конструктор\".")
    public void clickConstructorButton () {
        driver.findElement(HEADER_CONSTRUCTOR_BUTTON).click();
    }

    @Step("**Хэдер сервиса**. Клик по логотипу сервиса.")
    public void clickLogo () {
        driver.findElement(HEADER_LOGO).click();
    }

    @Step("**Хэдер сервиса**. Клик по кнопке \"Личный кабинет\".")
    public void clickPersonalCabinetButton () {
        driver.findElement(HEADER_PERSONAL_CABINET_BUTTON).click();
    }

}
