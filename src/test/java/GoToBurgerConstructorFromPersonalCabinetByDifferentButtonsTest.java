import api.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import net.datafaker.Faker;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

@RunWith(Parameterized.class)
public class GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest {
    // выбор браузера
    private static String testBrowser = WebDriverFactory.getBrowserName();

    private WebDriver driver;
    private Header header;
    private LoginPage loginPage;
    private MainPage mainPage;
    private ProfilePage profilePage;
    private UserAPI userAPI;
    private String email;
    private String password;
    private String name;
    private String accessToken;

    // поля параметризации
    private final By locator;
    private final String testName;
    // конструктор
    public GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest (By locator, String testName) {
        this.locator=locator;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{1}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {Header.getHeaderConstructorButtonLocator(), "Проверка перехода через \"Конструктор\"."},
                {Header.getHeaderLogoLocator(), "Проверка перехода через Лого."},
        };
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
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через взаимодействие с разными элементами в хэдере.")
    @Description("Проверяется возможность перехода в конструктор бургера из личного кабинете через кнопку \"Конструктор\" и при нажатии на логотип в хэдере страницы.")
    public void goToConstructorFromPersonalCabinetByConstructorButtonTest () {
        // создание объектов страниц
        header = new Header(driver);
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);

        /// Выполнение
        // залогинились в системе
        loginPage
                .openLoginPage()
                .waitForEmailFieldIsVisible()
                .fillLoginFormAndPressRegisterButton(email, password);
        // ожидание отображения главной страницы
        mainPage
                .waitForBurgerConstructorIsVisible();
        // перешли в личный кабинет
        header
                .clickPersonalCabinetButton();
        // ожидание отображения личного кабинете
        profilePage
                .waitForExitButtonIsVisible();
        // выбор кнопки взаимодействия
        if (locator.equals(header.getHeaderConstructorButtonLocator())) {
            header.clickConstructorButton();
        } else if (locator.equals(header.getHeaderLogoLocator())) {
            header.clickLogo();
        }
        // ожидание отображения главной страницы
        mainPage
                .waitForBurgerConstructorIsVisible();

        /// Проверка видимости конструктора бургера
        WebElement burgerConstructorWebElement = mainPage.getBurgerConstructorWebElement();
        boolean isBurgerConstructorIsDisplayed = burgerConstructorWebElement.isDisplayed();
        checkSuccessAssertTrue(isBurgerConstructorIsDisplayed);
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
