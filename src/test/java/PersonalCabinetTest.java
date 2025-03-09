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
//                {WebDriverFactory.YANDEX, "Проверка в Яндекс Браузере"},
                {WebDriverFactory.CHROME, "Проверка в Гугл Хроме"},
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
    @DisplayName("Проверка перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    @Description("Проверяется возможность перехода в личный кабинет через кнопку \"Личный кабинет\" в хэдере страницы.")
    public void personalCabinetButtonTest () {
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        header = new Header(driver);

        // выполнение
        mainPage
                .waitForBurgerConstructorIsVisible();
        header
                .clickPersonalCabinetButton();
        profilePage
                .waitForExitButtonIsVisible();

        /// Проверка видимости кнопки "Выход" в личном профиле
        assertTrue(driver.findElement(ProfilePage.EXIT_BUTTON).isDisplayed());

        // выход из профиля
        profilePage
                .clickExitButton();
        loginPage
                .waitForEmailFieldIsVisible();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор.")
    @Description("Проверяется возможность перехода в конструктор бургера из личного кабинете через кнопку \"Конструктор\" в хэдере страницы.")
    public void goToConstructorFromPersonalCabinetTest () {
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
        header
                .clickConstructorButton();
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
