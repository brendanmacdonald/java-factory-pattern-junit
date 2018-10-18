package com.factory.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(className = "radius")
    private WebElement loginBtn;

    public void setUsername(String username) {
        this.username.sendKeys(username);
    }

    public void setPassword(String password) {
        this.password.sendKeys(password);
    }

    public SecurePage submit(){
        loginBtn.click();
        return new SecurePage(driver);  // When you navigate you should return the a page object for the next page.
    }
}
