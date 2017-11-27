package util;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

public class Settings {

    protected static WebDriver driver;

    protected static void uploadApp(){
        System.setProperty("webdriver.gecko.driver", "./drivers/Mac/geckodriver");
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        driver = new FirefoxDriver(capabilities);
        driver.get("https://vk.com");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
