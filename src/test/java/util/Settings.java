package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Settings {

    protected static WebDriver driver;

    protected static void uploadApp() {
        System.setProperty("webdriver.gecko.driver", "./drivers/Mac/geckodriver");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://vk.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
