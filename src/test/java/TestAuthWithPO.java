import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import pages.AuthPage;
import util.Settings;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class TestAuthWithPO extends Settings {

    private static AuthPage authPage;

    @BeforeClass
    public static void setUp() throws Exception {
        uploadApp();
        authPage = new AuthPage(driver);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void test_success_auth() {
        // Авторизуемся по логину и паролю
        authPage.auth("email", "password");

        // Сравниваем текущий url с ожидаемым значением
        assertThat(driver.getCurrentUrl(), is("https://vk.com/"));
    }
}
