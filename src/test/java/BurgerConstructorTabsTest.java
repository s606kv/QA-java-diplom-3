import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static api.servise.Utilities.checkSuccessAssertTrue;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class BurgerConstructorTabsTest {
    // поля класса
    private WebDriver driver;
    private MainPage mainPage;

    // поля параметризации
    private final String browser;
    private final By tab;
    private final By firstElement;
    private final String testName;
    // конструктор
    public BurgerConstructorTabsTest(String browser, By tab, By firstElement, String testName) {
        this.browser=browser;
        this.tab=tab;
        this.firstElement=firstElement;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{3}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {WebDriverFactory.YANDEX, MainPage.BUN_TAB, MainPage.BUN_FIRST_ELEMENT, "Проверка вкладки \"Булки\" в Яндекс Браузере"},
                {WebDriverFactory.YANDEX, MainPage.SAUCE_TAB, MainPage.SAUCE_FIRST_ELEMENT, "Проверка вкладки \"Соусы\" в Яндекс Браузере"},
                {WebDriverFactory.YANDEX, MainPage.FILLING_TAB, MainPage.FILLING_FIRST_ELEMENT, "Проверка вкладки \"Начинки\" в Яндекс Браузере"},
                {WebDriverFactory.CHROME, MainPage.BUN_TAB, MainPage.BUN_FIRST_ELEMENT, "Проверка вкладки \"Булки\" в Яндекс Браузере"},
                {WebDriverFactory.CHROME, MainPage.SAUCE_TAB, MainPage.SAUCE_FIRST_ELEMENT, "Проверка вкладки \"Соусы\" в Яндекс Браузере"},
                {WebDriverFactory.CHROME, MainPage.FILLING_TAB, MainPage.FILLING_FIRST_ELEMENT, "Проверка вкладки \"Начинки\" в Яндекс Браузере"},
        };
    }


    @Before
    public void setUp () {
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
        // открывается главная страница
        mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible();
    }

    @Test
    @DisplayName("Проверка работы разделов \"Булки\", \"Соусы\", \"Начинки\" в конструкторе бургера.")
    @Description("Проверяется активация разделов \"Булки\", \"Соусы\", \"Начинки\" и отображение первого элемента каждого раздела.")
    public void tabTest () {
        /// Определяются условия
        if (tab.equals(MainPage.BUN_TAB)) {
            mainPage
                    .clickBunsTabSwitcher()
                    .waitForFirstBunIsVisible();
        }
        else if (tab.equals(MainPage.SAUCE_TAB)) {
            mainPage
                    .clickSaucesTabSwitcher()
                    .waitForFirstSauceIsVisible();
        }
        else if (tab.equals(MainPage.FILLING_TAB)) {
            mainPage
                    .clickFillingsTabSwitcher()
                    .waitForFirstFillingIsVisible();
        }

        /// Проверка видимости первого элемента выбранного раздела
        checkSuccessAssertTrue(driver.findElement(firstElement).isDisplayed());
    }

    @After
    public void tearDown () {
        driver.quit();
    }

}
