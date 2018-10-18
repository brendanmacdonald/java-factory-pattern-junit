package com.factory.pattern.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import com.factory.pattern.Utils.Configuration;
import com.factory.pattern.Utils.ConfigurationHelper;
import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import org.junit.*;
import com.factory.pattern.pages.LoginPage;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;

public class LoginTest {

    private static DriverManager driverManager;
    private static WebDriver driver;
    private static Configuration configParams = new Configuration();
    private LoginPage lp = new LoginPage(driver);

    @BeforeClass
    public static void setUp() {
        // Hard code to use Chrome
        // driverManager = DriverManagerFactory.getManager(DriverType.CHROME);
        // OR read in value from command line
        configParams = ConfigurationHelper.getCommandLineParams();
        DriverType driverType = configParams.getBrowser();
        driverManager = DriverManagerFactory.getManager(driverType);
        driver = driverManager.getDriver();
    }

    @AfterClass
    public static void tearDown() {
        driverManager.quitDriver();
    }

    @Test
    @DisplayName("Verify login page title")
    public void theInternetTest() {
        lp.openLoginPage();
        assertEquals("The Internet", lp.getPageTitle());
    }

    @Test
    @DisplayName("Verify basic form authentication")
    public void formAuthenticationTest() {
        lp.openLoginPage();
        lp.setUsername("tomsmith");
        lp.setPassword("SuperSecretPassword!");
        lp.submit();

        assertThat(lp.getFlashText(), containsString("You logged into a secure area!"));
    }
}
