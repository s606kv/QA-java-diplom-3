import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.Links.LOGIN_PAGE_URL;

public class LoginPage {
    /// Конструктор
    WebDriver driver;
    public LoginPage (WebDriver driver) {
        this.driver=driver;
    }

    ///  Локаторы
    // поле "Email"
    public static final By EMAIL_FIELD = By.xpath(".//label[contains(text(), 'Email')]/following-sibling::input");
    // поле "Пароль"
    public static final By PASSWORD_FIELD = By.xpath(".//label[contains(text(), 'Пароль')]/following-sibling::input");
    // кнопка входа
    public static final By ENTER_BUTTON = By.xpath(".//form/button[contains(text(), 'Войти')]");

    /// Шаги
    @Step("**Страница регистрации**. Открытие страницы.")
    public LoginPage openLoginPage () {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }
    @Step("**Страница регистрации**. Ожидание видимости поля ввода почты.")
    public LoginPage waitForEmailFieldIsVisible () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(EMAIL_FIELD));
        return this;
    }
    @Step("**Страница регистрации**. Заполнение поля \"Email\".")
    public LoginPage fillEmail (String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
        return this;
    }
    @Step("**Страница регистрации**. Заполнение поля \"Пароль\".")
    public LoginPage fillPassword (String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        return this;
    }
    @Step("**Страница регистрации**. Клик по кнопке \"Войти\".")
    public void clickEnterButton () {
        driver.findElement(ENTER_BUTTON).click();
    }
    @Step("**Страница регистрации**. Заполнение всей формы входа и нажатие кнопки \"Войти\".")
    public LoginPage fillLoginFormAndPressRegisterButton (String email, String password) {
        fillEmail(email);
        fillPassword(password);
        clickEnterButton();
        return this;
    }

    /// Веб-элементы
    @Step("**Страница регистрации**. Получение веб-элемента поля заполнения емэйла.")
    public WebElement getEmailFieldWebElement () {
        WebElement emailFieldWebElement = driver.findElement(EMAIL_FIELD);
        return emailFieldWebElement;
    }
    @Step("**Страница регистрации**. Получение веб-элемента кнопки входа.")
    public WebElement getEnterButtonWebElement () {
        WebElement enterButtonWebElement = driver.findElement(ENTER_BUTTON);
        return enterButtonWebElement;
    }
}
