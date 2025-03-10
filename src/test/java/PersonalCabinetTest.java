import api.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

@RunWith(Parameterized.class)
public class PersonalCabinetTest {
    private WebDriver driver;
    private String email;
    private String password;
    private String name;
    private UserAPI userAPI;
    private String accessToken;
    private MainPage mainPage;
    private Header header;
    private ProfilePage profilePage;
    private LoginPage loginPage;

    // поля параметризации
    private final String browser;
    private final String testName;
    // конструктор
    public PersonalCabinetTest(String browser, String testName) {
        this.browser=browser;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{1}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {WebDriverFactory.YANDEX, "Проверка в Яндекс Браузере"},
                {WebDriverFactory.CHROME, "Проверка в Гугл Хроме"},
        };
    }

    @Before
    public void setUpAndLogin () {
        /// Инициализация данных пользователя
        Faker faker = new Faker();
        email = faker.internet().emailAddress();;
        password = faker.lorem().characters(6, 6, false, false, true);;
        name = faker.name().username();
        /// Создание нового пользователя и получение токена
        userAPI = new UserAPI();
        accessToken = userAPI.userCreateAngGetAccessToken(email, password, name);
        /// Настройка браузера
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
        /// Создание объектов страниц
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        header = new Header(driver);
        /// Логинимся в системе и проверяем, что оказались на главной странице
        loginPage
                .openLoginPage()
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndPressRegisterButton(email, password);
        mainPage
                .waitForBurgerConstructorIsVisible();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    @Description("Проверяется возможность перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    public void personalCabinetHeaderButtonTest () {
        // Переходим в личный кабинет через хэдер
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();

        /// Проверка видимости кнопки "Выход" в личном профиле
        checkSuccessAssertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());

        // Выход из профиля
        profilePage
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета по кнопке \"Выход\".")
    @Description("Проверяется выход из личного кабинета при нажатии кнопки \"Выход\".")
    public void checkExitFromPersonalAccountByExitButton () {
        // Переходим в личный кабинет через хэдер
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();
        // выход из профиля
        profilePage
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
        /// Проверка видимости элемента после нажатия кнопки выхода
        checkSuccessAssertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @After
    public void tearDown () {
        // закрытие браузера
        driver.quit();
        // удаление пользователя
        userAPI.deleteUser(accessToken);
        System.out.println("Тест завершен.");
    }
}
