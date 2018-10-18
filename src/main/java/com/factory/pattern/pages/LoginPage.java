package com.factory.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage() {
        driver.navigate().to("https://the-internet.herokuapp.com/login");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    @FindBy(id = "username")
    @CacheLookup
    private WebElement username;

    @FindBy(id = "password")
    @CacheLookup
    private WebElement password;

    @FindBy(className = "radius")
    @CacheLookup
    private WebElement loginBtn;

    public LoginPage setUsername(String username) {
        this.username.sendKeys(username);
        return this;
    }

    public LoginPage setPassword(String password) {
        this.password.sendKeys(password);
        return this;
    }

    public LoginPage submit(){
        loginBtn.click();
        return this;  // When you navigate you should return the a page object for the next page.
    }
}
