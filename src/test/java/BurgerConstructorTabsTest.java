import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.assertj.core.api.SoftAssertions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import utilities.WebDriverFactory;

public class BurgerConstructorTabsTest {
    // выбор браузера
    private String testBrowser = WebDriverFactory.getBrowserName();

    SoftAssertions softly = new SoftAssertions();

    private WebDriver driver;
    private MainPage mainPage;
    private String divAttributeClassNameTabInactive;
    private String divAttributeClassNameTabActive;

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
        divAttributeClassNameTabInactive = "tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect";
        divAttributeClassNameTabActive = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";
    }

    @Test
    @DisplayName("Проверка работы раздела \"Булки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Булки\" и отображение заголовка списка.")
    public void bunTabTest () {
        // убираем фокус с вкладки "Булки"
        mainPage.clickSaucesTabSwitcher();

        // ждем смены имени класса тега div на неактивное
        mainPage.waitAttributeClassNameIsChanged(mainPage.getBunTabWebElement(), "class", divAttributeClassNameTabInactive);

        // получаем имя класса тега div у элемента вкладки "Булки" в неактивном состоянии
        String divAttributeClassNameBunTabInactive = mainPage.getBunTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в неактивном состоянии:\n" + divAttributeClassNameBunTabInactive);

        /// Убеждаемся, что имя класса тега div соответствует неактивному состоянию
        softly.assertThat(divAttributeClassNameBunTabInactive).isEqualTo(divAttributeClassNameTabInactive);

        // кликаем по вкладке "Булки"
        mainPage.clickBunsTabSwitcher();

        // ждем смены имени класса тега div на активное
        mainPage.waitAttributeClassNameIsChanged(mainPage.getBunTabWebElement(), "class", divAttributeClassNameTabActive);

        // получаем имя класса тега div у элемента вкладки "Булки" в активном состоянии
        String divAttributeClassNameBunTabActive = mainPage.getBunTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в активном состоянии:\n" + divAttributeClassNameBunTabActive);

        /// Сравниваем имена классов в активном состоянии
        softly.assertThat(divAttributeClassNameBunTabActive).isEqualTo(divAttributeClassNameTabActive);
    }

    @Test
    @DisplayName("Проверка работы раздела \"Соусы\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Соусы\" и отображение заголовка списка.")
    public void sauceTabTest () {
        // получаем имя класса тега div у элемента вкладки "Соусы" в неактивном состоянии
        String divAttributeClassNameSauceTabInactive = mainPage.getSauseTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в неактивном состоянии:\n" + divAttributeClassNameSauceTabInactive);

        /// Убеждаемся, что имя класса тега div соответствует неактивному состоянию
        softly.assertThat(divAttributeClassNameSauceTabInactive).isEqualTo(divAttributeClassNameTabInactive);

        // кликаем по вкладке "Соусы"
        mainPage.clickSaucesTabSwitcher();

        // ждем смены имени класса
        mainPage.waitAttributeClassNameIsChanged(mainPage.getSauseTabWebElement(), "class", divAttributeClassNameTabActive);

        // получаем имя класса тега div у элемента вкладки "Соусы" в активном состоянии
        String divAttributeClassNameSauceTabActive = mainPage.getSauseTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в активном состоянии:\n" + divAttributeClassNameSauceTabActive);

        /// Сравниваем названия классов
        softly.assertThat(divAttributeClassNameSauceTabActive).isEqualTo(divAttributeClassNameTabActive);
    }

    @Test
    @DisplayName("Проверка работы раздела \"Начинки\" в конструкторе бургера.")
    @Description("Проверяется активация раздела \"Начинки\" и отображение заголовка списка.")
    public void fillingTabTest () {
        // получаем имя класса тега div у элемента вкладки "Начинки" в неактивном состоянии
        String divAttributeClassNameFillingTabInactive = mainPage.getFillingTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в неактивном состоянии:\n" + divAttributeClassNameFillingTabInactive);

        /// Убеждаемся, что имя класса тега div соответствует неактивному состоянию
        softly.assertThat(divAttributeClassNameFillingTabInactive).isEqualTo(divAttributeClassNameTabInactive);

        // кликаем по вкладке "Начинки"
        mainPage.clickFillingsTabSwitcher();

        // ждем смены имени класса
        mainPage.waitAttributeClassNameIsChanged(mainPage.getFillingTabWebElement(), "class", divAttributeClassNameTabActive);

        // получаем имя класса тега div у элемента вкладки "Начинки" в активном состоянии
        String divAttributeClassNameFillingTabActive = mainPage.getFillingTabWebElement().getDomAttribute("class");
        System.out.println("Имя класса в активном состоянии:\n" + divAttributeClassNameFillingTabActive);

        /// Сравниваем названия классов
        softly.assertThat(divAttributeClassNameFillingTabActive).isEqualTo(divAttributeClassNameTabActive);
    }

    @After
    public void tearDown () {
        driver.quit();
        System.out.println("Тест завершен.");
    }

}
