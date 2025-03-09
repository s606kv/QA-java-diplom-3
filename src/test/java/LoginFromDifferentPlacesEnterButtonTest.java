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
public class LoginFromDifferentPlacesEnterButtonTest {
    // поля класса
    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private Header header;
    private ProfilePage profilePage;
    private String email = "ludgi@yandex.ru";
    private String password = "123456";

    // поля параметризации
    private final String browser;
    private final String testName;
    // конструктор
    public LoginFromDifferentPlacesEnterButtonTest(String browser, String testName) {
        this.browser=browser;
        this.testName=testName;
    }

    // метода входа в систему и перехода в личный профиль
    private void loginAndGoToProfile (String email, String password) {
        loginPage
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndEnter(email, password);
        mainPage
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();
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
        mainPage = new MainPage(driver);
        header = new Header(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему по кнопке \"Войти в аккаунт\" с главной страницы.")
    @Description("Проверяется возможность перехода на страницу входа в систему по кнопке \"Войти в аккаунт\" с главной страницы.")
    public void checkEnterAccountButtonFromMainPage () {
        // выполнение
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible()
                .clickEnterToAccountButton();

        loginAndGoToProfile(email, password);

        // проверка
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему по кнопке \"Личный кабинет\" с главной страницы.")
    @Description("Проверяется возможность перехода на страницу входа в систему по кнопке \"Личный кабинет\" с главной страницы.")
    public void checkEnterAccountFromPersonalCabinetButtonFromMainPage () {
        // выполнение
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();

        loginAndGoToProfile(email, password);

        // проверка
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());
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

        loginAndGoToProfile(email, password);

        // проверка
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());
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

        loginAndGoToProfile(email, password);

        // проверка
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());
    }

    @After
    public void tearDown () {
        // выход из профиля
        profilePage.clickExitButton();
        loginPage.waitForEmailFieldIsVisible();
        // закрытие браузера
        driver.quit();
    }


}
