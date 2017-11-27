package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AuthPage {

    private By idLogin = By.id("index_email");
    private By idPassword = By.id("index_pass");
    private By idSubmit = By.id("index_login_button");
    private By idGoToMyPage = By.id("l_pr");


    private WebDriver driver;
    private Wait<WebDriver> wait;

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 7, 1000);
    }

    public void auth(String login, String password) {
        // Пишем email и пароль
        setLogin(login);
        setPassword(password);

        // Нажимаем войти
        clickAuth();

        wait.until(ExpectedConditions.visibilityOfElementLocated(idGoToMyPage));
    }

    private void setLogin(String login){
        driver.findElement(idLogin).sendKeys(login);
    }

    private void setPassword(String password){
        driver.findElement(idPassword).sendKeys(password);
    }

    private void clickAuth(){
        driver.findElement(idSubmit).submit();
    }
}
