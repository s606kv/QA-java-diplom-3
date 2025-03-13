import api.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

public class LoginFromDifferentPlacesEnterButtonTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getBrowserName();

    private WebDriver driver;
    private LoginPage loginPage;
    private MainPage mainPage;
    private Header header;
    private ProfilePage profilePage;
    private String email;
    private String password;
    private String name;
    private UserAPI userAPI;
    private String accessToken;

    // метода входа в систему и перехода в личный профиль
    private void loginAndGoToProfile (String email, String password) {
        loginPage
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndPressRegisterButton(email, password);
        mainPage
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();
    }

    @Before
    public void setUp () {
        /// Инициализация данных пользователя
        Faker faker = new Faker();
        email = faker.internet().emailAddress();;
        password = faker.lorem().characters(6, 6, false, false, true);;
        name = faker.name().username();
        /// Создание нового пользователя и получение токена
        userAPI = new UserAPI();
        accessToken = userAPI.userCreateAngGetAccessToken(email, password, name);
        // инициализация драйвера
        driver = WebDriverFactory.setBrowser(testBrowser);
        driver.manage().window().maximize();
        /// Создание объектов страниц
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

        /// Проверка видимости кнопки "Выход" в личном профиле
        boolean exitButtonIsDisplayed = driver.findElement(profilePage.getExitButtonLocator()).isDisplayed();
        checkSuccessAssertTrue(exitButtonIsDisplayed);
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

        /// Проверка видимости кнопки "Выход" в личном профиле
        boolean exitButtonIsDisplayed = driver.findElement(profilePage.getExitButtonLocator()).isDisplayed();
        checkSuccessAssertTrue(exitButtonIsDisplayed);
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

        /// Проверка видимости кнопки "Выход" в личном профиле
        boolean exitButtonIsDisplayed = driver.findElement(profilePage.getExitButtonLocator()).isDisplayed();
        checkSuccessAssertTrue(exitButtonIsDisplayed);
    }

    @Test
    @DisplayName("Проверка перехода на страницу входа в систему через кнопку в форме восстановления пароля.")
    @Description("Проверяется возможность перехода на страницу входа в систему через кнопку в форме восстановления пароля.")
    public void checkEnterAccountFromForgotPasswordPage () {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);

        // выполнение
        forgotPasswordPage
                .openForgotPasswordPage()
                .waitForEnterButtonIsVisible()
                .clickEnterButton();

        loginAndGoToProfile(email, password);

        /// Проверка видимости кнопки "Выход" в личном профиле
        boolean exitButtonIsDisplayed = driver.findElement(profilePage.getExitButtonLocator()).isDisplayed();
        checkSuccessAssertTrue(exitButtonIsDisplayed);
    }

    @After
    public void tearDown () {
        // выход из профиля
        profilePage.clickExitButton();
        loginPage.waitForEmailFieldIsVisible();
        // закрытие браузера
        driver.quit();
        // удаление пользователя
        userAPI.deleteUser(accessToken);
        System.out.println("Тест завершен.");
    }
}
