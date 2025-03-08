import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class GoToLoginFromDifferentPlacesTest {
    // поля класса
    private WebDriver driver;
    private LoginPage loginPage;

    // поля параметризации
    private final String browser;
    private final String testName;
    // конструктор
    public GoToLoginFromDifferentPlacesTest(String browser, String testName) {
        this.browser=browser;
        this.testName=testName;
    }

    @Parameterized.Parameters (name = "{1}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {WebDriverFactory.YANDEX, "Проверка в Яндекс Браузере"},
                {WebDriverFactory.CHROME, "Проверка в Гугл Хроме"},
        };
    }

    @Before
    public void setUp () {
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему по кнопке \"Войти в аккаунт\" с главной страницы.")
    @Description("Проверяется возможность перехода на страницу входа в систему по кнопке \"Войти в аккаунт\" с главной страницы.")
    public void checkEnterAccountButtonFromMainPage () {
        MainPage mainPage = new MainPage(driver);

        // выполнение
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible()
                .clickEnterToAccountButton();

        // проверка
        assertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему по кнопке \"Личный кабинет\" с главной страницы.")
    @Description("Проверяется возможность перехода на страницу входа в систему по кнопке \"Личный кабинет\" с главной страницы.")
    public void checkEnterAccountFromPersonalCabinetButtonFromMainPage () {
        MainPage mainPage = new MainPage(driver);

        // выполнение
        Header header = new Header(driver);
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();

        // проверка
        assertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему через кнопку в форме регистрации.")
    @Description("Проверяется возможность перехода на страницу входа в систему через кнопку в форме регистрации.")
    public void checkEnterAccountFromRegistrationPage () {
        RegistrationPage registrationPage = new RegistrationPage(driver);

        // выполнение
        registrationPage
                .openRegistrationPage()
                .waitForNameFieldIsVisible()
                .scrollToEnterButton()
                .clickEnterButton();

        // проверка
        assertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему через кнопку в форме восстановления пароля.")
    @Description("Проверяется возможность перехода на страницу входа в систему через кнопку в форме восстановления пароля.")
    public void checkEnterAccountFromForgotPasswordPage () {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // выполнение
        forgotPasswordPage
                .openForgotPasswordPage()
                .clickEnterButton();

        // проверка
        assertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @After
    public void closeBrowser () {
        driver.quit();
    }


}
