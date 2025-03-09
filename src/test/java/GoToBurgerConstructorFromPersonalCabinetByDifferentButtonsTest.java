import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.UserData;
import utilities.WebDriverFactory;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest {
    private WebDriver driver;
    private String email = UserData.TEST_EMAIL;
    private String password = UserData.TEST_PASSWORD;
    private MainPage mainPage;
    private Header header;
    private ProfilePage profilePage;
    private LoginPage loginPage;

    // поля параметризации
    private final String browser;
    private final By locator;
    private final String testName;
    // конструктор
    public GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest (String browser, By locator, String testName) {
        this.browser=browser;
        this.locator=locator;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{1}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {WebDriverFactory.YANDEX, Header.HEADER_CONSTRUCTOR_BUTTON, "Проверка перехода через \"Конструктор\" в Яндекс Браузере"},
                {WebDriverFactory.YANDEX, Header.HEADER_LOGO, "Проверка перехода через Лого в Яндекс Браузере"},
                {WebDriverFactory.CHROME, Header.HEADER_CONSTRUCTOR_BUTTON, "Проверка перехода через \"Конструктор\" в Гугл Хроме"},
                {WebDriverFactory.CHROME, Header.HEADER_LOGO, "Проверка перехода через Лого в Гугл Хроме"},
        };
    }

    @Before
    public void setUpAndLogin () {
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
        // логинимся в системе
        loginPage = new LoginPage(driver);
        loginPage
                .openLoginPage()
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndEnter(email, password);
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через взаимодействие с разными элементами в хэдере.")
    @Description("Проверяется возможность перехода в конструктор бургера из личного кабинете через кнопку \"Конструктор\" и при нажатии на логотип в хэдере страницы.")
    public void goToConstructorFromPersonalCabinetByConstructorButtonTest () {
        profilePage = new ProfilePage(driver);
        header = new Header(driver);
        mainPage = new MainPage(driver);

        // выполнение
        mainPage
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();

        // выбор кнопки взаимодействия
        if (locator.equals(Header.HEADER_CONSTRUCTOR_BUTTON)) {
            header.clickConstructorButton();
        } else if (locator.equals(Header.HEADER_LOGO)) {
            header.clickLogo();
        }
        mainPage
                .waitForBurgerConstructorIsVisible();

        /// Проверка видимости конструктора бургера
        assertTrue(driver.findElement(MainPage.BURGER_CONSTRUCTOR_SECTION).isDisplayed());

        // выход из профиля
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible()
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}
