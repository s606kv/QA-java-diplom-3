import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class FirstTest {
    WebDriver driver = WebDriverFactory.setBrowser(WebDriverFactory.CHROME);

    @Test
    public void simpleTest() {
        MainPage mainPage = new MainPage(driver);
        driver.manage().window().maximize();
        mainPage
                .openMainPage()
                .waitForBurgerConstructorIsVisible()
                .clickSauceSectionSwitcher()
                .waitForFirstSauceIsVisible()
                .clickBunSectionSwitcher()
                .waitForFirstBunIsVisible();
        driver.quit();
    }
}
