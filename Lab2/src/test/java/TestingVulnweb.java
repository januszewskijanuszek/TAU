import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

/*
Correct informacions:
Username: student
Password: Password123
 */

public class TestingVulnweb {
    private WebDriver webDriver;
    private LoginHand loginHand;

    @Before
    public void setWebDriver(){
        webDriver = new ChromeDriver();
        webDriver.get("https://practicetestautomation.com/practice-test-login/");
        loginHand = new LoginHand(
                webDriver.findElement(By.id("username")),
                webDriver.findElement(By.id("password")),
                webDriver.findElement(By.id("submit")));
    }

    @After
    public void shutdown(){
        webDriver.quit();
    }

    @Test
    public void CanUserLoginWithCorrectLoginAndPassword(){
        loginHand.sendKeys("student", "Password123");
        WebElement result = webDriver.findElement(By.className("post-title"));
        assertTrue(result.getText().contains("Logged In Successfully"));
    }
    @Test
    public void WrongPasswordCase(){
        loginHand.sendKeys("student", "wrongPassword123");
        WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
        assertTrue(wrongPasswordMessage.getText().contains("Your password is invalid!"));
    }
    @Test
    public void AllWrongInfo(){
        loginHand.sendKeys("WrongLogin", "essa");
        WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
        assertTrue(wrongPasswordMessage.getText().contains("Your username is invalid!"));
    }
    @Test
    public void NoUserNameProvidedButCorrectPassword(){
        loginHand.sendKeys("", "Password123");
        WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
        assertTrue(wrongPasswordMessage.getText().contains("Your username is invalid!"));
    }
    @Test
    public void TryRandomNumbersGenerationsAsLoginWithCorrectPassword(){
        boolean mistake = false;
        String remember = null;
        String message = null;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            loginHand.washHands();
            remember = String.format("%08d", random.nextInt(100000000));
            loginHand.sendKeys(remember, "Password123");
            WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
            if(!wrongPasswordMessage.getText().contains("Your username is invalid!")){
                message = wrongPasswordMessage.getText();
                mistake = true;
                break;
            }
        }
        if(mistake) System.out.println(remember + " - " + message);
        assertTrue(!mistake);
    }
    @Test
    public void AllEmpty(){
        loginHand.sendKeys("", "");
        WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
        assertTrue(wrongPasswordMessage.getText().contains("Your username is invalid!"));
    }
    @Test
    public void sqlLoginIjections(){
        boolean mistake = false;
        List<String> sqlList = List.of("\" or \"\"=\"", "-- ", "DROP database;#", "# ", "admin'--", "SELECT * FROM members WHERE username = 'admin'--' AND password = 'password'");
        for (String val: sqlList) {
            loginHand.sendKeys(val, "Password123");
            WebElement wrongPasswordMessage = webDriver.findElement(By.className("show"));
            if(!wrongPasswordMessage.getText().contains("Your username is invalid!")){
                mistake = true;
                break;
            }
        }
        assertTrue(!mistake);
    }
}
