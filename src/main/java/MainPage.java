import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.Links.MAIN_PAGE_URL;

public class MainPage {
    /// Конструктор
    WebDriver driver;
    public MainPage (WebDriver driver) {
        this.driver=driver;
    }

    ///  Локаторы
    // кнопка "Войти в аккаунт"
    private static final By ENTER_TO_ACCOUNT_BUTTON = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    // кнопка "Оформить заказ"
    private static final By BURGER_CONSTRUCTOR_SECTION = By.className("BurgerIngredients_ingredients__1N8v2");
    // раздел "Булки" в конструкторе
    private static final By BUN_TAB = By.xpath(".//span[contains(@class, 'text_type_main-default') and contains(text(), 'Булки')]/parent::div");
    // раздел "Соус" в конструкторе
    private static final By SAUCE_TAB = By.xpath(".//span[contains(@class, 'text_type_main-default') and contains(text(), 'Соусы')]/parent::div");
    // раздел "Начинки" в конструкторе
    private static final By FILLING_TAB = By.xpath(".//span[contains(@class, 'text_type_main-default') and contains(text(), 'Начинки')]/parent::div");
    // таймер ожидания
    private static final Duration TIMER_3_SEC = Duration.ofSeconds(3);

    /// Шаги
    @Step("**Главная страница**. Открытие страницы.")
    public MainPage openMainPage () {
        driver.get(MAIN_PAGE_URL);
        return this;
    }
    @Step("**Главная страница**. Клик по кнопке \"Войти в аккаунт\".")
    public void clickEnterToAccountButton () {
        driver.findElement(ENTER_TO_ACCOUNT_BUTTON).click();
    }
    @Step("**Главная страница**. Ожидание появления секции с конструктором бургера.")
    public MainPage waitForBurgerConstructorIsVisible () {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCTOR_SECTION));
        return this;
    }
    @Step("**Главная страница**. Клик по кнопке переключения раздела \"Булки\".")
    public MainPage clickBunsTabSwitcher () {
        driver.findElement(BUN_TAB).click();
        return this;
    }
    @Step("**Главная страница**. Клик по кнопке переключения раздела \"Соусы\".")
    public MainPage clickSaucesTabSwitcher () {
        driver.findElement(SAUCE_TAB).click();
        return this;
    }
    @Step("**Главная страница**. Клик по кнопке переключения раздела \"Начинки\".")
    public MainPage clickFillingsTabSwitcher () {
        driver.findElement(FILLING_TAB).click();
        return this;
    }
    @Step("**Главная страница**. Ожидание смены имени класса у атрибута class элемента div.")
    public void waitAttributeClassNameIsChanged (WebElement element, String attribute, String className) {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.attributeToBe(element, attribute, className));
    }

    /// Веб-элементы
    @Step("**Главная страница**. Получение веб-элемента конструктора бургеров.")
    public WebElement getBurgerConstructorWebElement () {
        WebElement burgerConstructorWebElement = driver.findElement(BURGER_CONSTRUCTOR_SECTION);
        return burgerConstructorWebElement;
    }
    @Step("**Главная страница**. Получение веб-элемента переключателя вкладки булок.")
    public WebElement getBunTabWebElement () {
        WebElement bunTabWebElement = driver.findElement(BUN_TAB);
        return bunTabWebElement;
    }
    @Step("**Главная страница**. Получение веб-элемента переключателя вкладки соусов.")
    public WebElement getSauseTabWebElement () {
        WebElement sauseTabWebElement = driver.findElement(SAUCE_TAB);
        return sauseTabWebElement;
    }
    @Step("**Главная страница**. Получение веб-элемента переключателя вкладки начинок.")
    public WebElement getFillingTabWebElement () {
        WebElement fillingTabWebElement = driver.findElement(FILLING_TAB);
        return fillingTabWebElement;
    }
}
