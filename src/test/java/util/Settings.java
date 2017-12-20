package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Settings {

    protected static WebDriver driver;
    private static String OS;
    private static Wait<WebDriver> wait;

    private static String URL = "https://vk.com";

    protected static void init() {

        OS = System.getProperty("os.name").toLowerCase();
        identifyOS();

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("target/test-classes/application.properties");
            property.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }

        String driverName = property.getProperty("driver");
        String driverPath = property.getProperty("driverPath");

        int browser = Integer.parseInt(property.getProperty("browser"));

        DesiredCapabilities capabilities = null;
        System.setProperty(driverName, "./drivers/" + OS + driverPath);

        switch (browser){
            case 1:
                capabilities = DesiredCapabilities.chrome();
                driver = new ChromeDriver(capabilities);
                break;
            case 2:
                capabilities = DesiredCapabilities.firefox();
                driver = new FirefoxDriver(capabilities);
                break;
        }

        driver.get(URL);


        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wait = new WebDriverWait(driver, 7, 500);
    }

    private static void identifyOS(){
        if (isUnix())
            OS = "Unix";
        else if (isWindows())
            OS = "Windows";
        else if (isMac())
            OS = "Mac";
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isUnix() {
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0);
    }
}
