import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {

    ///  Локаторы
    private static final String REGISTER_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    // поле "Имя"
    private static final By NAME_FIELD = By.xpath(".//form/fieldset//label[(text()='Имя')]/following-sibling::input");
    // поле "Email"
    private static final By EMAIL_FIELD = By.xpath(".//form/fieldset//label[(text()='Email')]/following-sibling::input");
    // поле "Пароль"
    private static final By PASSWORD_FIELD = By.xpath(".//form/fieldset//label[(text()='Пароль')]/following-sibling::input");
    // кнопка "Зарегистрироваться"
    private static final By REGISTER_BUTTON = By.xpath(".//form/button[contains(@class, 'button_button__33qZ0') and contains(text(), 'Зарегистрироваться')]");
    // кнопка "Войти"
    private static final By ENTER_BUTTON = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj') and contains(text(), 'Войти')]");

    /// Конструктор
    WebDriver driver;
    public RegisterPage (WebDriver driver) {
        this.driver=driver;
    }

    /// Шаги
    @Step ("Страница регистрации. Открытие страницы.")
    public void openRegisterPage () {
        driver.get(REGISTER_PAGE_URL);
    }

    @Step ("Страница регистрации. Ожидание появления поля \"Имя\".")
    public RegisterPage waitForNameFieldIsVisible () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(NAME_FIELD));
        return this;
    }

    @Step ("Страница регистрации. Заполнение поля \"Имя\".")
    public RegisterPage fillNameField (String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
        return this;
    }

    @Step ("Страница регистрации. Заполнение поля \"Email\".")
    public RegisterPage fillEmailField (String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }

    @Step ("Страница регистрации. Заполнение поля \"Пароль\".")
    public RegisterPage fillPasswordField (String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }

    @Step ("Страница регистрации. Клик по кнопке \"Зарегистрироваться\".")
    public void clickRegisterButton () {
        driver.findElement(REGISTER_BUTTON).click();
    }

    @Step ("Страница регистрации. Скролл до кнопки \"Войти\".")
    public RegisterPage scrollToEnterButton () {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(ENTER_BUTTON));
        return this;
    }

    @Step ("Страница регистрации. Клик по кнопке \"Войти\".")
    public void clickEnterButton () {
        driver.findElement(ENTER_BUTTON).click();
    }

}
