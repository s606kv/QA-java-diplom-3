import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import utilities.UserData;
import utilities.WebDriverFactory;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class PersonalCabinetTest {
    private WebDriver driver;
    private String email = UserData.TEST_EMAIL;
    private String password = UserData.TEST_PASSWORD;
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
        // инициализация драйвера и объектов страниц
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        header = new Header(driver);
        // логинимся в системе
        loginPage
                .openLoginPage()
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndEnter(email, password);
        // переходим в личный кабинет
        mainPage
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    @Description("Проверяется возможность перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    public void personalCabinetButtonTest () {
        /// Проверка видимости кнопки "Выход" в личном профиле
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());

        // выход из профиля
        profilePage
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета по кнопке \"Выход\".")
    @Description("Проверяется выход из личного кабинета при нажатии кнопки \"Выход\".")
    public void checkExitFromAccountByExitButton () {
        // выход из профиля
        profilePage
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
        /// Проверка видимости элемента после нажатия кнопки выхода
        assertTrue(driver.findElement(LoginPage.EMAIL_FIELD).isDisplayed());
    }

    @After
    public void tearDown () {
        driver.quit();
    }
}
