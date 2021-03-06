import com.sun.javafx.runtime.SystemProperties;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;



public class TestClassTest {
    //private static WebDriver driver;
    private Credentials sms_account;
    private static GridSetup grid_driver;
    private static RemoteWebDriver driver;
    private static DesiredCapabilities remote_capabilities;

    @BeforeClass
    public static void startWebDriver(){
        remote_capabilities=new DesiredCapabilities().firefox();
        //remote_capabilities=new DesiredCapabilities().safari();
        grid_driver=new GridSetup(driver,remote_capabilities);
        grid_driver.setBrowserCapabilities();
        grid_driver.setRemoteWebDriver();
    }

    @Before
    public void prepForTest(){

    sms_account=new Credentials();


    }


    @Test
    public void logInWithoutErrors(){


    }


    @Test
    public void logInWithErrors() {

        final String TICKET_LOGIN=sms_account.ticket_url + "/user/sign_in";

        grid_driver.remote_driver.get(TICKET_LOGIN);

        LoginPage login_page = new LoginPage(grid_driver.remote_driver);
        login_page.typePassword(sms_account.password);
        login_page.typeUserName(sms_account.telno);
        login_page.signIn();

        assertThat(login_page.loginUnsuccessfulMessageIsPresent(), is(true));
    }


    @Test
    public void forgottenPasswordWithErrors(){
        grid_driver.remote_driver.findElement(By.linkText("Har du glömt ditt lösenord?")).click();

        Assert.assertTrue(grid_driver.remote_driver.findElement(By.id("phone_confirmation_phone_number")).isDisplayed());


        ForgottenPassword forgotten_pwd = new ForgottenPassword(grid_driver.remote_driver);
        forgotten_pwd.typePhoneNumber("0700012869");
        forgotten_pwd.getTemporaryPassword();
        assertThat(forgotten_pwd.temporaryPasswordUnsuccessfulMessageIsPresent(), is(true));
    }


       /*
        driver.get(baseUrl + "/user/sign_in");
        driver.findElement(By.id("user_phone_number")).clear();
        driver.findElement(By.id("user_phone_number")).sendKeys("0700012869");
        driver.findElement(By.id("user_password")).clear();
        driver.findElement(By.id("user_password")).sendKeys("abc123");
        driver.findElement(By.name("commit")).click();



      /*  driver.findElement(By.id("phone_confirmation_phone_number")).clear();
        driver.findElement(By.id("phone_confirmation_phone_number")).sendKeys("0734481365");

        driver.findElement(By.name("commit")).click();
        driver.findElement(By.linkText("Klicka här")).click();
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.id("phone_confirmation_confirmation_code")).clear();
        driver.findElement(By.id("phone_confirmation_confirmation_code")).sendKeys("532465");
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.id("registration_email")).clear();
        driver.findElement(By.id("registration_email")).sendKeys("tommy.eklund@klarna.com");
        driver.findElement(By.id("registration_pno")).clear();
        driver.findElement(By.id("registration_pno")).sendKeys("4103219202");
        driver.findElement(By.name("commit")).click();
        driver.findElement(By.cssSelector("div.small-12.columns > input[name=\"commit\"]")).click();
        driver.findElement(By.id("registration_password")).clear();
        driver.findElement(By.id("registration_password")).sendKeys("abc123");
        driver.findElement(By.id("registration_password_confirmation")).clear();
        driver.findElement(By.id("registration_password_confirmation")).sendKeys("abc123");
        driver.findElement(By.id("submit-details")).click();
        driver.findElement(By.linkText("Mitt konto")).click();
        driver.findElement(By.linkText("Radera ditt konto")).click();
        //assertTrue(closeAlertAndGetItsText().matches("^Är du säker på att du vill radera ditt konto[\\s\\S]$"));
*/

        @AfterClass
        public static void quitDriver(){
            grid_driver.remote_driver.quit();
        }

        /*String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString))
        {
            fail(verificationErrorString);
        }*/



}
