import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Header {
    /// Конструктор
    private WebDriver driver;
    public Header (WebDriver driver) {
        this.driver=driver;
    }

    /// Локаторы
    // кнопка "Конструктор"
    private static final By HEADER_CONSTRUCTOR_BUTTON = By.xpath(".//p[contains (text(), 'Конструктор')]");
    // кнопка с логотипом
    private static final By HEADER_LOGO = By.className("AppHeader_header__logo__2D0X2");
    // кнопка "Личный кабинет"
    private static final By HEADER_PERSONAL_CABINET_BUTTON = By.xpath(".//p[contains (text(), 'Личный Кабинет')]");

    /// Геттеры
    @Step("**Хэдер сервиса**. Получение локатора кнопки перехода в конструктор.")
    public static By getHeaderConstructorButtonLocator () {
        return HEADER_CONSTRUCTOR_BUTTON;
    }
    @Step("**Хэдер сервиса**. Получение локатора логотипа в хэдере.")
    public static By getHeaderLogoLocator () {
        return HEADER_LOGO;
    }

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
