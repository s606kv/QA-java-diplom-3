import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static utilities.Links.FORGOT_PASSWORD_PAGE_URL;

public class ForgotPasswordPage {
    /// Конструктор
    private WebDriver driver;
    public ForgotPasswordPage (WebDriver driver) {
        this.driver=driver;
    }

    ///  Локаторы
    // кнопка "Войти"
    private static final By ENTER_BUTTON = By.xpath(".//a[contains(@class, 'Auth_link__1fOlj') and contains(text(), 'Войти')]");

    /// Шаги
    @Step("**Страница восстановления пароля**. Открытие страницы.")
    public ForgotPasswordPage openForgotPasswordPage  () {
        driver.get(FORGOT_PASSWORD_PAGE_URL);
        return this;
    }
    @Step("**Страница восстановления пароля**. Клик по кнопке \"Войти\".")
    public void clickEnterButton () {
        driver.findElement(ENTER_BUTTON).click();
    }
    @Step("**Страница восстановления пароля**. Ожидание видимости кнопки \"Войти\".")
    public ForgotPasswordPage waitForEnterButtonIsVisible () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(ENTER_BUTTON));
        return this;
    }
}
