package com.factory.pattern.tests;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import com.factory.pattern.Utils.Configuration;
import com.factory.pattern.Utils.ConfigurationHelper;
import com.factory.pattern.drivers.DriverManager;
import com.factory.pattern.drivers.DriverManagerFactory;
import com.factory.pattern.drivers.DriverType;
import com.factory.pattern.utils.ScreenShotOnFailure;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import com.factory.pattern.pages.LoginPage;
import org.openqa.selenium.WebDriver;

@Epic("Login Epic")
@Feature("Login Feature")
@Link("https://jira.org")
public class LoginTest {

    private static DriverManager driverManager;
    private static WebDriver driver;
    private LoginPage lp = new LoginPage(driver);

    @Rule
    public ScreenShotOnFailure failure = new ScreenShotOnFailure(driver);

    @BeforeClass
    public static void setUp() {
        Configuration configParams = ConfigurationHelper.getCommandLineParams();
        DriverType driverType = configParams.getBrowser();
        driverManager = DriverManagerFactory.getManager(driverType); // DriverManagerFactory.getManager(DriverType.CHROME);
        driver = driverManager.getDriver();
    }

    @AfterClass
    public static void tearDown() {
        driverManager.quitDriver();
    }

    @Test
    public void theInternetTest() {
        lp.openLoginPage();
        assertEquals("The Internet", lp.getPageTitle());
    }

    @Test
    @DisplayName("Human-readable test name")
    @Description("Test Description: Login test with the correct username and correct password.")
    @Issue("JIRA-1234")
    @Story("Some Story id")
    @Severity(SeverityLevel.NORMAL)
    public void formAuthenticationTest() {
        lp.openLoginPage();
        lp.fillAndSubmit("tomsmith", "SuperSecretPassword!");

        assertThat(lp.getFlashText(), containsString("You logged into a secure area! - force error"));
    }
}
