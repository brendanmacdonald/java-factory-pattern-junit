package com.factory.pattern.tests;

import com.factory.pattern.Utils.Configuration;
import com.factory.pattern.Utils.ConfigurationHelper;
import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import com.factory.pattern.pages.LoginPage;
import com.factory.pattern.pages.SecurePage;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

public class SecureTest {

    private static DriverManager driverManager;
    private static WebDriver driver;
    private static Configuration configParams = new Configuration();
    private LoginPage lp = new LoginPage(driver, "tomsmith", "SuperSecretPassword!");
    private SecurePage sp = new SecurePage(driver);

    @BeforeClass
    public static void setUp() {
        configParams = ConfigurationHelper.getCommandLineParams();
        DriverType driverType = configParams.getBrowser();
        driverManager = DriverManagerFactory.getManager(driverType); // DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
    }

    @AfterClass
    public static  void tearDown() {
        driverManager.quitDriver();
    }

    @Test
    @DisplayName("Human-readable test name")
    @Description("Test Description: verify a user can logout.")
    public void verifyLogoutTest() {
        lp.openLoginPage();
        lp.fillAndSubmit();
        sp.logout();

        assertThat(lp.getFlashText(), containsString("You logged out of the secure area!"));
    }
}
