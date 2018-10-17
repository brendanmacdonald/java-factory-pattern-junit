package com.factory.pattern.tests;

import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class FirstFactoryPatternTest {

    static DriverManager driverManager;
    WebDriver driver;

    @BeforeClass
    public static void beforeTest() {
        driverManager = DriverManagerFactory.getManager(DriverManagerFactory.DriverType.CHROME);
    }

    @Before
    public void beforeMethod() {
        driver = driverManager.getDriver();
    }

    @AfterClass
    public static void afterMethod() {
        driverManager.quitDriver();
    }

    @Test
    public void googleTitleTest() {
        driver.get("https://www.google.com");
        assertEquals("Google", driver.getTitle());
    }

    @Test
    public void launchSeleniumJavaAPITest() {
        driver.get("https://seleniumhq.github.io/selenium/docs/api/java/");
        assertEquals("Overview", driver.getTitle());
    }
}
