package utilities;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class WebDriverFactory {
    @Getter
    private static String browserName;

    // браузер будет указываться в файле browser.txt и автоматически меняться в каждом тесте
    static {
        try {
            browserName = new String(Files.readAllBytes(Paths.get("src/main/resources/browser.txt")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static WebDriver setBrowser (String browserName) {
        if (browserName.equals("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equals("yandex")) {
            /// Настройка Я.Браузера
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:/Users/SKV/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
            return new ChromeDriver(options);
        } else {
            throw new RuntimeException("Не удалось определить драйвер для указанного браузера.");
        }
    }

}
