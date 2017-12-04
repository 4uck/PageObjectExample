import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestAuthVKWithoutPO {

    private static WebDriver driver;
    private static Wait<WebDriver> wait;

    @BeforeClass
    public static void setUp() {
//        System.setProperty("webdriver.gecko.driver", "./drivers/Mac/geckodriver");
        System.setProperty("webdriver.gecko.driver", "./drivers/Unix/geckodriver");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new RemoteWebDriver(capabilities);
        driver.manage().window().maximize();
        driver.get("https://vk.com");
        wait = new WebDriverWait(driver, 7, 1000);
    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

    @Test
    public void test_success_auth() {

        // Пишем email и пароль
        driver.findElement(By.id("index_email")).sendKeys("email");
        driver.findElement(By.id("index_pass")).sendKeys("password");

        // Нажимаем войти
        driver.findElement(By.id("index_login_button")).submit();

        // Проверяем, что не вылезла каптча
        if (!isCaptcha()) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("l_pr")));
        }

        // Сравниваем текущий url с ожидаемым знчением
        assertThat(driver.getCurrentUrl(), is("https://vk.com/feed"));
    }

    private boolean isCaptcha() {
        try {
            driver.findElement(By.className("popup_box_container")).isEnabled();
            System.out.println("This is captcha!!!!!!!!!! Тобi пiзда");
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}
