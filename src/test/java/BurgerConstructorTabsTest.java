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

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

@RunWith(Parameterized.class)
public class BurgerConstructorTabsTest {
    // инициализация драйвера
    private String testBrowser = WebDriverFactory.getChrome();
    private WebDriver driver = WebDriverFactory.setBrowser(testBrowser);

    private MainPage mainPage;

    // поля параметризации
    private final By tab;
    private final By firstHeader;
    private final String testName;
    // конструктор
    public BurgerConstructorTabsTest(By tab, By firstHeader, String testName) {
        this.tab=tab;
        this.firstHeader=firstHeader;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{2}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {MainPage.getBunTabLocator(), MainPage.getBunListHeaderLocator(), "Проверка вкладки \"Булки\"."},
                {MainPage.getSauceTabLocator(), MainPage.getSauceListHeaderLocator(), "Проверка вкладки \"Соусы\"."},
                {MainPage.getFillingTabLocator(), MainPage.getFillingListHeaderLocator(), "Проверка вкладки \"Начинки\"."},
        };
    }

    @Before
    public void setUp () {
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
        if (tab.equals(MainPage.getBunTabLocator())) {
            mainPage
                    .waitForBunListHeaderIsVisible();
        }
        else if (tab.equals(MainPage.getSauceTabLocator())) {
            mainPage
                    .clickSaucesTabSwitcher()
                    .waitForSauceListHeaderIsVisible();
        }
        else if (tab.equals(MainPage.getFillingTabLocator())) {
            mainPage
                    .clickFillingsTabSwitcher()
                    .waitForFillingListHeaderIsVisible();
        }

        /// Проверка видимости первого элемента выбранного раздела
        checkSuccessAssertTrue(driver.findElement(firstHeader).isDisplayed());
    }

    @After
    public void tearDown () {
        driver.quit();
        System.out.println("Тест завершен.");
    }

}
