import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

public class FirstTest {
    private WebDriver driver;
    private Faker faker = new Faker();
    private String name;
    private String email;
    private String password;

    @Before
    public void setUp () {
        driver = WebDriverFactory.setBrowser(WebDriverFactory.YANDEX);
        driver.manage().window().maximize();
        name = faker.name().username();
        email = faker.internet().emailAddress();
        password = faker.internet().password(6, 10);
    }

    @Test
    public void simpleTest() {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        registrationPage
                .openRegistrationPage()
                .waitForNameFieldIsVisible()
                .fillRegistrationForm(name, email, password)
                .clickRegisterButton();

        loginPage.waitForEmailFieldIsVisible();

        boolean isEmailFieldIsVisible = driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed();
        assertTrue(isEmailFieldIsVisible);
    }

    @After
    public void quit () {
        driver.quit();
    }
}
