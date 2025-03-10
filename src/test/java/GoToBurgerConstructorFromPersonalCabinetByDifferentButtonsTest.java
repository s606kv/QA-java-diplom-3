import api.UserAPI;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.UserData;
import utilities.WebDriverFactory;

import static api.servise.UtilitiesAPI.checkSuccessAssertTrue;

@RunWith(Parameterized.class)
public class GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest {
    private WebDriver driver;
    private UserAPI userAPI;
    private String email;
    private String password;
    private String name;
    private String accessToken;
    private LoginPage loginPage;
    private MainPage mainPage;
    private Header header;
    private ProfilePage profilePage;

    // поля параметризации
    private final String browser;
    private final By locator;
    private final String testName;
    // конструктор
    public GoToBurgerConstructorFromPersonalCabinetByDifferentButtonsTest (String browser, By locator, String testName) {
        this.browser=browser;
        this.locator=locator;
        this.testName=testName;
    }

    // параметры
    @Parameterized.Parameters (name = "{2}")
    public static Object[][] setTestData () {
        return new Object[][] {
                {WebDriverFactory.YANDEX, Header.HEADER_CONSTRUCTOR_BUTTON, "Проверка перехода через \"Конструктор\" в Яндекс Браузере"},
                {WebDriverFactory.YANDEX, Header.HEADER_LOGO, "Проверка перехода через Лого в Яндекс Браузере"},
                {WebDriverFactory.CHROME, Header.HEADER_CONSTRUCTOR_BUTTON, "Проверка перехода через \"Конструктор\" в Гугл Хроме"},
                {WebDriverFactory.CHROME, Header.HEADER_LOGO, "Проверка перехода через Лого в Гугл Хроме"},
        };
    }

    @Before
    public void setUp () {
        /// Инициализация данных пользователя
        email = UserData.TEST_USER_EMAIL;
        password = UserData.TEST_USER_PASSWORD;
        name = UserData.TEST_USER_NAME;
        /// Создание нового пользователя и получение токена
        userAPI = new UserAPI();
        accessToken = userAPI.userCreateAngGetAccessToken(email, password, name);
        /// Настройка браузера
        driver = WebDriverFactory.setBrowser(browser);
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор через взаимодействие с разными элементами в хэдере.")
    @Description("Проверяется возможность перехода в конструктор бургера из личного кабинете через кнопку \"Конструктор\" и при нажатии на логотип в хэдере страницы.")
    public void goToConstructorFromPersonalCabinetByConstructorButtonTest () {
        // создание объектов страниц
        loginPage = new LoginPage(driver);
        mainPage = new MainPage(driver);
        header = new Header(driver);
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
        if (locator.equals(Header.HEADER_CONSTRUCTOR_BUTTON)) {
            header.clickConstructorButton();
        } else if (locator.equals(Header.HEADER_LOGO)) {
            header.clickLogo();
        }
        // ожидание отображения главной страницы
        mainPage
                .waitForBurgerConstructorIsVisible();

        /// Проверка видимости конструктора бургера
        checkSuccessAssertTrue(driver.findElement(MainPage.BURGER_CONSTRUCTOR_SECTION).isDisplayed());
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
