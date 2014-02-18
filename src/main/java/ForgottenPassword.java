import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ForgottenPassword {

    private final RemoteWebDriver driver;

    By user_phoneno_text_field=By.id("phone_confirmation_phone_number");
    By get_temporary_pwd_button=By.name("commit");

    public ForgottenPassword(RemoteWebDriver driver){
        this.driver=driver;

    }

    public void typePhoneNumber(String telephoneNumber){
        WebElement userPhone=driver.findElement(user_phoneno_text_field);
        userPhone.clear();
        userPhone.sendKeys(telephoneNumber);
    }

    public void getTemporaryPassword(){
        WebElement tempPasswordButton=driver.findElement(get_temporary_pwd_button);
        tempPasswordButton.click();
    }

    public boolean temporaryPasswordSuccessfulMessageIsPresent(){
        WebElement messageholder;

        try{
            messageholder=driver.findElement(By.cssSelector("p.alert-box.notice"));
        }catch (NoSuchElementException e){
            return false;
        }

        if(!messageholder.isDisplayed()){
            return false;
        }

        if(messageholder.getText().equals("Vi skickade precis ett sms med ditt lösenord.")){
            return true;
        }
        return false;
    }
    public boolean temporaryPasswordUnsuccessfulMessageIsPresent(){
        WebElement messageholder;

        try{
            messageholder=driver.findElement(By.cssSelector("p.alert-box.alert"));
        }catch (NoSuchElementException e){
            return false;
        }

        if(!messageholder.isDisplayed()){
            return false;
        }
        if(messageholder.getText().contains("This phone is not registered. Please sign up first.")){
            return true;
        }
        return false;
    }
}
