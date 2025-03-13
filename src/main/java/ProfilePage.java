import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProfilePage {
    /// Конструктор
    WebDriver driver;
    public ProfilePage (WebDriver driver) {
        this.driver=driver;
    }

    ///  Локаторы
    // кнопка "Выход"
    private static final By EXIT_BUTTON = By.xpath(".//button[contains(@class, 'Account_button__14Yp3') and contains(text(), 'Выход')]");

    /// Геттеры
    public static By getExitButtonLocator () {
        return EXIT_BUTTON;
    }

    /// Шаги
    @Step("**Страница профиля**. Ожидание появления кнопки \"Выход\".")
    public ProfilePage waitForExitButtonIsVisible () {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(EXIT_BUTTON));
        return this;
    }

    @Step("**Страница профиля**. Нажатие кнопки \"Выход\".")
    public void clickExitButton () {
        driver.findElement(EXIT_BUTTON).click();
    }
}
