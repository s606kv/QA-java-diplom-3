import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

public class RegistrationPageWrongPasswordTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getChrome();
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = WebDriverFactory.setBrowser(testBrowser);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка длины пароля.")
    @Description("Проверяется возможность ввода в поле регистрации пользователя пароля длиной меньше 6 символов.")
    public void incorrectPasswordTest() {
        // задали невалидный пароль, ГЗ 5 символов
        Faker faker = new Faker();
        String password = faker.internet().password(5, 5);

        // заполнили только поле с паролем и кликнули на кнопку регистрации для снятия фокуса с поля
        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage
                .openRegistrationPage()
                .fillPasswordField(password)
                .clickRegistrationButton()
                .waitForIncorrectPasswordMessage();

        /// Проверка отображения предупреждающего сообщения о некорректном пароле
        checkSuccessAssertTrue(driver.findElement(RegistrationPage.getIncorrectPasswordMessageLocator()).isDisplayed());
    }

    @After
    public void printInfo () {
        driver.quit();
        System.out.println("Тест завершен.");
    }
}
