import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginHand {
    private WebElement login;
    private WebElement password;
    private WebElement loginButton;

    public LoginHand(WebElement login, WebElement password, WebElement loginButton) {
        this.login = login;
        this.password = password;
        this.loginButton = loginButton;
    }
    public void washHands(){
        this.login.clear();
        this.password.clear();
    }
    public void sendKeys(String login, String password){
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        this.loginButton.click();
    }
    public WebElement getLogin() {
        return login;
    }
    public WebElement getPassword() {
        return password;
    }
    public WebElement getLoginButton() {
        return loginButton;
    }
}
