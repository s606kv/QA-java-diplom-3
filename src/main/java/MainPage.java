import io.qameta.allure.Step;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
    private static final By BUN_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Булки')]");
    // первый элемент в "Булках"
    private static final By BUN_LIST_HEADER = By.xpath(".//h2[contains(@class, 'text_type_main-medium') and contains (text(), 'Булки')]");
    // раздел "Соус" в конструкторе
    private static final By SAUCE_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Соусы')]");
    // первый элемент в "Соусах"
    private static final By SAUCE_LIST_HEADER = By.xpath(".//h2[contains(@class, 'text_type_main-medium') and contains (text(), 'Соусы')]");
    // раздел "Начинки" в конструкторе
    private static final By FILLING_TAB = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Начинки')]");
    // первый элемент в "Начинках"
    private static final By FILLING_LIST_HEADER = By.xpath(".//h2[contains(@class, 'text_type_main-medium') and contains (text(), 'Начинки')]");
    // таймер ожидания
    private static final Duration TIMER_3_SEC = Duration.ofSeconds(3);

    /// Геттеры для локаторов
    @Step("**Главная страница**. Получение локатора конструктора бургеров.")
    public By getBurgerConstructorSectionLocator () {
        return BURGER_CONSTRUCTOR_SECTION;
    }
    @Step("**Главная страница**. Получение локатора заголовка списка булок.")
    public By getBunListHeaderLocator () {
        return BUN_LIST_HEADER;
    }
    @Step("**Главная страница**. Получение локатора заголовка списка соусов.")
    public By getSauceListHeaderLocator () {
        return SAUCE_LIST_HEADER;
    }
    @Step("**Главная страница**. Получение локатора заголовка списка начинок.")
    public By getFillingListHeaderLocator () {
        return FILLING_LIST_HEADER;
    }

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

    @Step("**Главная страница**. Ожидание видимости заголовка списка \"Булки\".")
    public MainPage waitForBunListHeaderIsVisible() {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(BUN_LIST_HEADER));
        return this;
    }

    @Step("**Главная страница**. Клик по кнопке переключения раздела \"Соусы\".")
    public MainPage clickSaucesTabSwitcher () {
        driver.findElement(SAUCE_TAB).click();
        return this;
    }

    @Step("**Главная страница**. Ожидание видимости заголовка списка \"Соусы\".")
    public MainPage waitForSauceListHeaderIsVisible() {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(SAUCE_LIST_HEADER));
        return this;
    }

    @Step("**Главная страница**. Клик по кнопке переключения раздела \"Начинки\".")
    public MainPage clickFillingsTabSwitcher () {
        driver.findElement(FILLING_TAB).click();
        return this;
    }

    @Step("**Главная страница**. Ожидание видимости заголовка списка \"Начинки\".")
    public MainPage waitForFillingListHeaderIsVisible() {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING_LIST_HEADER));
        return this;
    }

}
