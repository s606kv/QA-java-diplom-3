import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {
    public static final String CHROME_BROWSER = "Chrome";
    public static final String YANDEX_BROWSER = "Yandex";
    public static final String FIREFOX = "Firefox";

    public static WebDriver setBrowser (String browserName) {
        // настройка я.браузера
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Users/SKV/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");

        if (browserName.equals("Chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("Yandex")) {
            return new ChromeDriver(options);
        } else {
            throw new RuntimeException("Не удалось определить браузер.");
        }
    }
}
