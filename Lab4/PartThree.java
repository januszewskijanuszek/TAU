import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

// Part one is required to run part two

public class PartThree {
    private WebDriver webDriver;
    private LoginHand loginHand;

    List<String> avaliableNamesList = new ArrayList<>(){{
        add("chrome");
        add("firefox");
        add("edge");
    }};
    Map<String, WebDriver> webDriverMap = new HashMap<>(){{
        put("chrome", new ChromeDriver());
        put("firefox", new FirefoxDriver());
        put("edge", new EdgeDriver());
    }};
    public void setWebDriver(String browserName){
        webDriver = webDriverMap.get(browserName);
        webDriver.get("https://www.saucedemo.com/");
        loginHand = new LoginHand(
                webDriver.findElement(By.id("user-name")),
                webDriver.findElement(By.id("password")),
                webDriver.findElement(By.id("login-button"))
        );
    }
    @After
    public void shutdown(){
        webDriver.quit();
    }

    @Test
    public void correctLogin(){
        for (String val: avaliableNamesList) {
            setWebDriver(val);
            loginHand.sendKeys("standard_user", "secret_sauce");
            WebElement addToCartButton = webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack"));
            assertTrue(addToCartButton.getText().contains("Add to cart"));
        }
    }
    @Test
    public void cssSelectorTestp(){
        for(String val: avaliableNamesList){
            setWebDriver(val);
            WebElement user = webDriver.findElement(By.cssSelector("#user-name"));
            user.sendKeys("standard_user");
            WebElement password = webDriver.findElement(By.cssSelector("#password"));
            password.sendKeys("secret_sauce");
            WebElement submit = webDriver.findElement(By.cssSelector("#login-button"));
            submit.click();
            WebElement title = webDriver.findElement(By.cssSelector(".app_logo"));
            assertTrue(title.getText().contains("Swag Labs"));
        }
    }
}
