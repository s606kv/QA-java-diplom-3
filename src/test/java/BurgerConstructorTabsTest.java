import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

public class BurgerConstructorTabsTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getBrowserName();

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp () {
        // инициализация драйвера
        driver = WebDriverFactory.setBrowser(testBrowser);
        driver.manage().window().maximize();
        // открывается главная страница
        mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible();
    }

    @Test
    @DisplayName("Проверка работы раздела \"Булки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Булки\" и отображение заголовка списка.")
    public void bunTabTest () {
            mainPage
                    .waitForBunListHeaderIsVisible();

        /// Проверка видимости заголовка списка
        boolean isBunListHeaderIsDisplayed = driver.findElement(mainPage.getBunListHeaderLocator()).isDisplayed();
        checkSuccessAssertTrue(isBunListHeaderIsDisplayed);
    }

    @Test
    @DisplayName("Проверка работы раздела \"Соусы\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Соусы\" и отображение заголовка списка.")
    public void sauceTabTest () {
        mainPage
                .clickSaucesTabSwitcher()
                .waitForSauceListHeaderIsVisible();

        /// Проверка видимости заголовка списка
        boolean isSauceListHeaderIsDisplayed = driver.findElement(mainPage.getSauceListHeaderLocator()).isDisplayed();
        checkSuccessAssertTrue(isSauceListHeaderIsDisplayed);
    }

    @Test
    @DisplayName("Проверка работы раздела \"Начинки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Начинки\" и отображение заголовка списка.")
    public void fillingTabTest () {
        mainPage
                .clickFillingsTabSwitcher()
                .waitForFillingListHeaderIsVisible();

        /// Проверка видимости заголовка списка
        boolean isFillingListHeaderIsDisplayed = driver.findElement(mainPage.getFillingListHeaderLocator()).isDisplayed();
        checkSuccessAssertTrue(isFillingListHeaderIsDisplayed);
    }

    @After
    public void tearDown () {
        driver.quit();
        System.out.println("Тест завершен.");
    }

}
