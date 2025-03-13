import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

import static org.junit.Assert.assertTrue;

public class BurgerConstructorTabsTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getBrowserName();

    private WebDriver driver;
    private MainPage mainPage;

    @Before
    public void setUp () {
        // инициализация драйвера
        driver = WebDriverFactory.setBrowser(testBrowser);
        driver.manage().window().maximize();
        // открывается главная страница
        mainPage = new MainPage(driver);
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible();
    }

    @Test
    @DisplayName("Проверка работы раздела \"Булки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Булки\" и отображение заголовка списка.")
    public void bunTabTest () {
        // получаем начальные координаты заголовка списка с булками
        Rectangle firstRect = mainPage.getBunListHeaderWebElement().getRect();
        // выводим начальное положение элемента по оси Y
        int startCoordinateY = firstRect.getY();
        System.out.println("Первоначальная Y координата: " + startCoordinateY);
        // кликаем по другой вкладке и убеждаемся, что заголовок списка с булками сместился
        mainPage.clickSaucesTabSwitcher();
        Rectangle secondRect = mainPage.getBunListHeaderWebElement().getRect();
        int secondCoordinateY = secondRect.getY();
        System.out.println("Новая Y координата: " + secondCoordinateY);

        if (secondCoordinateY<startCoordinateY) {
            // снова клик по разделу с булками и проверяется координата
            mainPage.clickBunsTabSwitcher();
            Rectangle thirdRect = mainPage.getBunListHeaderWebElement().getRect();
            int thirdCoordinateY = thirdRect.getY();
            System.out.println("Новая Y координата: " + thirdCoordinateY);

            /// Проверяем, что произошло смещение элемента вверх
            assertTrue(thirdCoordinateY>secondCoordinateY);
        } else {
            throw new RuntimeException("Положение элемента не поменялось.");
        }
    }

    @Test
    @DisplayName("Проверка работы раздела \"Соусы\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Соусы\" и отображение заголовка списка.")
    public void sauceTabTest () {
        // получаем начальные координаты заголовка списка с соусами
        Rectangle firstRect = mainPage.getSauceListHeaderWebElement().getRect();
        // выводим начальное положение элемента по оси Y
        int startCoordinateY = firstRect.getY();
        System.out.println("Первоначальная Y координата: " + startCoordinateY);
        // кликаем по вкладке и убеждаемся, что заголовок списка с соусами сместился выше
        mainPage.clickSaucesTabSwitcher();
        Rectangle secondRect = mainPage.getSauceListHeaderWebElement().getRect();
        int newCoordinateY = secondRect.getY();
        System.out.println("Новая Y координата: " + newCoordinateY);

        /// Проверяем, что произошло смещение элемента вверх
        assertTrue(newCoordinateY<startCoordinateY);
    }

    @Test
    @DisplayName("Проверка работы раздела \"Начинки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Начинки\" и отображение заголовка списка.")
    public void fillingTabTest () {
        // получаем начальные координаты заголовка списка с начинками
        Rectangle firstRect = mainPage.getFillingListHeaderWebElement().getRect();
        // выводим начальное положение элемента по оси Y
        int startCoordinateY = firstRect.getY();
        System.out.println("Начальная Y координата: " + startCoordinateY);
        // кликаем по вкладке и убеждаемся, что заголовок списка с начинками сместился выше
        mainPage.clickFillingsTabSwitcher();
        Rectangle secondRect = mainPage.getFillingListHeaderWebElement().getRect();
        int newCoordinateY = secondRect.getY();
        System.out.println("Новая Y координата: " + newCoordinateY);

        /// Проверяем, что произошло смещение элемента вверх
        assertTrue(newCoordinateY<startCoordinateY);
    }

    @After
    public void tearDown () {
        driver.quit();
        System.out.println("Тест завершен.");
    }

}
