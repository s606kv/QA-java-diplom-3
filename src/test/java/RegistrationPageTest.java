import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import net.datafaker.Faker;
import utilities.WebDriverFactory;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class RegistrationPageTest {
    // поля класса
    private Faker faker;
    private WebDriver driver;
    private RegistrationPage registrationPage;

    // поля параметризации
    private final String browser;
    private final String testName;
    // конструктор
    public RegistrationPageTest(String browser, String testName) {
        this.browser=browser;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name="{1}")
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
        registrationPage = new RegistrationPage(driver);
        faker = new Faker();
    }

    @Test
    @DisplayName("Проверка возможности регистрации.")
    @Description("Проверяется регистрация пользователя при вводе валидных данных в поля формы регистрации.")
    public void successRegistrationTest() {
        // объекты страницы логина
        LoginPage loginPage = new LoginPage(driver);

        // задали данные пользователя с валидным паролем ГЗ 6 символов
        String name = faker.name().username();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(6, 10);

        // выполнение
        registrationPage
                .openRegistrationPage()
                .waitForNameFieldIsVisible()
                .fillRegistrationForm(name, email, password)
                .waitForEnterHeader();

        /// Проверка видимости кнопки "Вход" на странице входа в профиль
        assertTrue(driver.findElement(LoginPage.ENTER_BUTTON).isDisplayed());
    }

    @Test
    @DisplayName("Проверка длины пароля.")
    @Description("Проверяется возможность ввода в поле регистрации пользователя пароля длиной меньше 6 символов.")
    public void incorrectPasswordTest() {
        // задали невалидный пароль ГЗ 5 символов
        String password = faker.internet().password(5, 5);

        // заполнили только поле с паролем и кликнули на кнопку регистрации для снятия фокуса с поля
        registrationPage
                .openRegistrationPage()
                .fillPasswordField(password)
                .clickRegistrationButton()
                .waitForIncorrectPasswordMessage();

        /// Проверка отображения предупреждающего сообщения о некорректном пароле
        assertTrue(driver.findElement(RegistrationPage.INCORRECT_PASSWORD_MESSAGE).isDisplayed());
    }

    @After
    public void closeBrowser () {
        driver.quit();
    }
}
