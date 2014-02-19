import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.*;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import java.net.MalformedURLException;
import java.net.URL;

public class TestWithTestNG {
    RemoteWebDriver driver = null;
    private StringBuffer verificationErrors = new StringBuffer();
    private Credentials sms_account;


    @Parameters({ "platform","browser","version", "url" })

    @BeforeTest(alwaysRun=true)
    public void setup(String platform, String browser, String
            version, String url) throws MalformedURLException
    {
        DesiredCapabilities caps = new DesiredCapabilities();

        sms_account=new Credentials();
        //Platforms
        if(platform.equalsIgnoreCase("VISTA")){
            caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
        }
       /* if(platform.equalsIgnoreCase("MAC")){
            caps.setPlatform(org.openqa.selenium.Platform.MAC);
        }
        if(platform.equalsIgnoreCase("Android")){
            caps.setPlatform(org.openqa.selenium.Platform.ANDROID);
        }*/
        //Browsers
        if(browser.equalsIgnoreCase("Safari")){
            caps = DesiredCapabilities.safari();
        }
        if(browser.equalsIgnoreCase("Firefox")){
            caps = DesiredCapabilities.firefox();
        }
        /*
        if(browser.equalsIgnoreCase("iPad")){
            caps = DesiredCapabilities.ipad();
        }
        if(browser.equalsIgnoreCase("Android")){
            caps = DesiredCapabilities.android();
        }*/
        //Version
        caps.setVersion(version);

        driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), caps);

        // Open the BMI Calculator Application
        driver.get(url);

    }

    @Test(description="Log in with Errors")
    public void logInWithErrors() {

        final String TICKET_LOGIN=sms_account.ticket_url + "/user/sign_in";

        driver.get(TICKET_LOGIN);

        LoginPage login_page = new LoginPage(driver);
        login_page.typePassword(sms_account.password);
        login_page.typeUserName(sms_account.telno);
        login_page.signIn();

        assertThat(login_page.loginUnsuccessfulMessageIsPresent(), is(true));
    }


    @Test(description = "Forgotten Password Function")
    public void forgottenPasswordWithErrors(){
        driver.findElement(By.linkText("Har du glömt ditt lösenord?")).click();

        Assert.assertTrue(driver.findElement(By.id("phone_confirmation_phone_number")).isDisplayed());


        ForgottenPassword forgotten_pwd = new ForgottenPassword(driver);
        forgotten_pwd.typePhoneNumber("0700012869");
        forgotten_pwd.getTemporaryPassword();
        assertThat(forgotten_pwd.temporaryPasswordUnsuccessfulMessageIsPresent(), is(true));
    }


    @AfterTest
    public void afterTest() {
        //Close the browser
        driver.quit();

        String verificationErrorString =
                verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }

    }
}