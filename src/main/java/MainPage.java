import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    ///  Локаторы
    private static final String MAIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/";
    // кнопка "Войти в аккаунт"
    private static final By ENTER_TO_ACCOUNT_BUTTON = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    // конструктор для сборки бургера
    private static final By BURGER_CONSTRUCTOR_SECTION = By.name("BurgerIngredients_ingredients__1N8v2");
    // раздел "Булки" в конструкторе
    private static final By BUN_SECTION_SWITCHER = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Булки')]");
    // первый элемент в "Булках"
    private static final By BUN_FIRST_ELEMENT = By.xpath(".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][1]/a[1]");
    // раздел "Соус" в конструкторе
    private static final By SAUCE_SECTION_SWITCHER = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Соусы')]");
    // первый элемент в "Соусах"
    private static final By SAUCE_FIRST_ELEMENT = By.xpath(".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][2]/a[1]");
    // раздел "Начинки" в конструкторе
    private static final By FILLING_SECTION_SWITCHER = By.xpath(".//section[@class='BurgerIngredients_ingredients__1N8v2']//span[contains(text(), 'Начинки')]");
    // первый элемент в "Начинках"
    private static final By FILLING_FIRST_ELEMENT = By.xpath(".//ul[@class='BurgerIngredients_ingredients__list__2A-mT'][3]/a[1]");
    // таймер ожидания
    private static final Duration TIMER_3_SEC = Duration.ofSeconds(3);

    /// Конструктор
    WebDriver driver;
    public MainPage (WebDriver driver) {
        this.driver=driver;
    }

    /// Методы
    // открытие страницы
    public MainPage openPage () {
        driver.get(MAIN_PAGE_URL);
        return this;
    }
    // клик по кнопке "Войти в аккаунт"
    public void enterToAccountButtonClick () {
        driver.findElement(ENTER_TO_ACCOUNT_BUTTON).click();
    }
    // ожидание появления секции с конструктором бургера
    public MainPage waitForBurgerConstructorIsVisible () {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(BURGER_CONSTRUCTOR_SECTION));
        return this;
    }
    // клик по кнопке переключения раздела с булками
    public MainPage bunSectionSwitcherClick () {
        driver.findElement(BUN_SECTION_SWITCHER);
        return this;
    }
    // ожидание видимости первого элемента секции "Булки"
    public MainPage waitForFirstBunIsVisible () {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(BUN_FIRST_ELEMENT));
        return this;
    }
    // клик по кнопке переключения раздела с соусами
    public MainPage sauceSectionSwitcherClick () {
        driver.findElement(SAUCE_SECTION_SWITCHER);
        return this;
    }
    // ожидание видимости первого элемента секции "Соусы"
    public MainPage waitForFirstSauceIsVisible () {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(SAUCE_FIRST_ELEMENT));
        return this;
    }
    // клик по кнопке переключения раздела с соусами
    public MainPage fillingSectionSwitcherClick () {
        driver.findElement(FILLING_SECTION_SWITCHER);
        return this;
    }
    // ожидание видимости первого элемента секции "Соусы"
    public MainPage waitForFirstFillingIsVisible () {
        new WebDriverWait(driver, TIMER_3_SEC)
                .until(ExpectedConditions.visibilityOfElementLocated(FILLING_FIRST_ELEMENT));
        return this;
    }










}
