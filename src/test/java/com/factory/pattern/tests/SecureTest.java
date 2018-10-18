package com.factory.pattern.tests;

import com.factory.pattern.Utils.Configuration;
import com.factory.pattern.Utils.ConfigurationHelper;
import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import com.factory.pattern.pages.LoginPage;
import com.factory.pattern.pages.SecurePage;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SecureTest {

    private static DriverManager driverManager;
    private static WebDriver driver;
    private static Configuration configParams = new Configuration();
    private LoginPage lp = new LoginPage(driver);
    private SecurePage sp = new SecurePage(driver);

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
    public static  void tearDown() {
        driverManager.quitDriver();
    }

    @Test
    @DisplayName("Verify logout functionality")
    public void verifyLogoutTest() {
        lp.openLoginPage();
        lp.setUsername("tomsmith");
        lp.setPassword("SuperSecretPassword!");
        lp.submit();
        sp.logout();

        assertThat(lp.getFlashText(), containsString("You logged out of the secure area!"));
    }
}
