package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    public static final String CHROME = "Chrome";
    public static final String YANDEX = "Yandex";

    public static WebDriver setBrowser (String browserName) {
        /// Настройка Я.Браузера
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/SKV/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
        /// Блок условий
        if (browserName.equals(CHROME)) {
            return new ChromeDriver();
        } else if (browserName.equals(YANDEX)) {
            return new ChromeDriver(options);
        } else {
            throw new RuntimeException("Не удалось определить драйвер для указанного браузера.");
        }
    }

}
