import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    ///  Локаторы
    public static final String FORGOT_PASSWORD_PAGE_URL = "https://stellarburgers.nomoreparties.site/forgot-password";
    // кнопка "Войти"
    public static final By ENTER_BUTTON = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj') and contains(text(), 'Войти')]");

    /// Конструктор
    WebDriver driver;
    public ForgotPasswordPage (WebDriver driver) {
        this.driver=driver;
    }

    /// Шаги
    @Step("Страница восстановления пароля. Открытие страницы.")
    public ForgotPasswordPage openForgotPasswordPage  () {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }

    @Step("Страница восстановления пароля. Клик по кнопке \"Войти\".")
    public void clickEnterButton () {
        driver.findElement(ENTER_BUTTON).click();
    }

    @Step("Страница восстановления пароля. Ожидание видимости кнопки \"Войти\".")
    public ForgotPasswordPage waitForEnterButtonIsVisible () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON));
        return this;
    }

}
