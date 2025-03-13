import api.UserAPI;
import api.servise.UserJson;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import net.datafaker.Faker;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

public class RegistrationPageTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getBrowserName();

    private WebDriver driver;
    private RegistrationPage registrationPage;
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp() {
        // инициализация драйвера
        driver = WebDriverFactory.setBrowser(testBrowser);
        driver.manage().window().maximize();
        // задали данные пользователя с валидным паролем ГЗ 6 символов
        Faker faker = new Faker();
        name = faker.name().username();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 10);
    }

    @Test
    @DisplayName("Проверка возможности регистрации.")
    @Description("Проверяется регистрация пользователя при вводе валидных данных в поля формы регистрации.")
    public void successRegistrationTest() {
        // выполнение
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        registrationPage
                .openRegistrationPage()
                .waitForNameFieldIsVisible()
                .fillRegistrationForm(name, email, password)
                .waitForEnterHeader();


        /// Проверка видимости кнопки "Вход" на странице входа в профиль
        boolean isEnterButtonIsDisplayed = driver.findElement(loginPage.getEnterButtonLocator()).isDisplayed();
        checkSuccessAssertTrue(isEnterButtonIsDisplayed);
    }

    @After
    public void printInfo () {
        // закрывается браузер
        driver.quit();
        System.out.println("Запускается блок кода для удаления пользователя.");
        // получаем токен через ручку логина для последующего удаления пользователя
        UserAPI userAPI = new UserAPI();
        UserJson userJson = new UserJson(email, password, name);
        Response loginUserResponse = userAPI.loginUser(userJson);
        String accessToken = loginUserResponse
                .then()
                .extract()
                .body()
                .path("accessToken")
                .toString()
                .substring(7);
        // удаляем пользователя
        userAPI.deleteUser(accessToken);
        System.out.println("Тест завершен.");
    }
}
