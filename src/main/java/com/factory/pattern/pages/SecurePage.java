package com.factory.pattern.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SecurePage extends BasePage{

    public SecurePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "radius")
    private WebElement logoutBtn;

    public LoginPage logout(){
        logoutBtn.click();
        return new LoginPage(driver);
    }
}
