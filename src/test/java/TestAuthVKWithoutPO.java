import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestAuthVKWithoutPO {

    static WebDriver driver;
    static Wait<WebDriver> wait;

    @BeforeClass
    public static void setUp() {
        System.setProperty("webdriver.gecko.driver", "./drivers/Mac/geckodriver");
        driver = new FirefoxDriver();
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

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("l_pr")));

        // Сравниваем текущий url с ожидаемым знчением
        assertThat(driver.getCurrentUrl(), is("https://vk.com/feed"));
    }
}
