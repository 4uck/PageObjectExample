package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage {

    By idLogin = By.id("index_email");
    By idPassword = By.id("index_pass");
    By idSubmit = By.id("index_login_button");
    By idGoToMyPage = By.id("l_pr");


    private WebDriver driver;
    private Wait<WebDriver> wait;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 7, 1000);
    }

    public void auth(String login, String password) {
        // Пишем email и пароль
        driver.findElement(idLogin).sendKeys(login);
        driver.findElement(idPassword).sendKeys(password);

        // Нажимаем войти
        driver.findElement(idSubmit).submit();

        wait.until(ExpectedConditions.visibilityOfElementLocated(idGoToMyPage));
    }
}
