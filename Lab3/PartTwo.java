import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


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

import java.util.List;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

// Part one is required to run part two

public class PartTwo {
    private WebDriver webDriver;
    private LoginHand loginHand;

    @Before
    public void setWebDriver(){
        webDriver = new ChromeDriver();
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
    public void isSiteLoading(){
        WebElement title = webDriver.findElement(By.cssSelector(".login_logo"));
        assertTrue(title.getText().contains("Swag Labs"));
    }

    @Test
    public void loginTesting(){
        isCorrectLogin();
        incorrectLoginInfo();
        incorrectInfo();
        lockedLoginInfo();
        blankLoginInfo();
        blankPasswordInfo();
        specialLetters();
    }
    public void isCorrectLogin(){
        loginHand.sendKeys("standard_user", "secret_sauce");
        List<WebElement> elements = webDriver.findElements(By.cssSelector(".header_secondary_container"));
        assert(elements.size() > 0);
    }

    public void incorrectLoginInfo(){
        loginHand.sendKeys("standard_user", "incorrect");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }
    public void incorrectInfo(){
        loginHand.sendKeys("incorrect", "incorrect");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }

    public void incorrectLofinInfo(){
        loginHand.sendKeys("incorrect", "secret_sauce");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }
    public void lockedLoginInfo(){
        loginHand.sendKeys("locked_out_user", "secret_sauce");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Sorry, this user has been locked out."));
    }
    public void blankLoginInfo(){
        loginHand.sendKeys("", "secret_sauce");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Username is required"));
    }
    public void blankPasswordInfo(){
        loginHand.sendKeys("standard_user", "");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Password is required"));
    }
    public void specialLetters(){
        loginHand.sendKeys("!@#$%^&*()", "!@#$%^&*()");
        WebElement error = webDriver.findElement(By.cssSelector("*[data-test=\"error\"]"));
        assertTrue(error.getText().contains("Epic sadface: Username and password do not match any user in this service"));
    }
    // --- Cart ---
    @Test
    public void cardTest(){
        addToCartTest();
        removeFromCart();
    }
    private void logIn(){
        loginHand.sendKeys("standard_user", "secret_sauce");
    }
    public void addToCartTest(){
        logIn();
        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector(".shopping_cart_link")).click();
        assertTrue(webDriver.findElement(By.cssSelector(".shopping_cart_badge")).getText().contains("1"));
    }
    public void removeFromCart(){
        logIn();
        webDriver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        webDriver.findElement(By.cssSelector(".shopping_cart_link")).click();
        webDriver.findElement(By.id("remove-sauce-labs-backpack")).click();
        assertTrue(webDriver.findElement(By.cssSelector(".shopping_cart_badge")).getText().contains(""));
    }
}
