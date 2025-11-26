import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Giga_Tests {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void topMenuBarWorks(){
        GigaPOM test = new GigaPOM(driver);
        test.navigate();
        assertEquals("Welcome to Giga",test.getTitle());

        test.clickLinktext("Gigs");
        assertEquals("Gigs",test.getTitle());

        test.clickLinktext("Log In");
        assertEquals("Log In",test.getTitle());

        test.clickLinktext("About");
        assertEquals("About Giga",test.getTitle());

        test.clickLinktext("About");
        assertEquals("About Giga",test.getTitle());

        test.clickLinktext("Home");
        assertEquals("Welcome to Giga",test.getTitle());

        test.clickLinktext("Account");
        assertEquals("401 Unauthorised",test.getTitle());
    }


    @Test
    /// sometimes fails if no thread.sleep
    /// can csv file different inputs for username and password
    void signInandOut()throws Exception{
        GigaPOM test = new GigaPOM(driver);

        test.SignIn("username","password");
        Thread.sleep(1000);
        assertEquals("Welcome to Giga",test.getTitle());

        test.clickLinktext("Account");
        assertEquals("Account", test.getTitle());

        /// check to log out too
        test.SignOut();
        assertEquals("Log Out",test.getTitle());

        test.clickLinktext("Account");
        assertEquals("401 Unauthorised",test.getTitle());

    }

    @Test
    void signUp(){

    }

    @Test
    ///waiting to automate more bookings until a delete booking func is possible, rn just manually reseeding on terminal
    void bookingTickets() throws Exception{
        GigaPOM test = new GigaPOM(driver);
        test.navigate();
        test.SignIn("username","password");
        test.clickLinktext("Gigs");

        Thread.sleep(1000);

        test.bookTickets("2", "7");
        takeScreenshot(driver, "input.png");
        driver.findElement(By.xpath("//input[@value='Book gig']")).click();
        takeScreenshot(driver, "bookings.png");

    }

    @Test
    void dateRange(){

    }









    @AfterAll
    public static void closeBrowser() {
        driver.quit();
    }

    /// prob wont need screenshot
    public static void takeScreenshot(WebDriver webdriver,String desiredPath) throws Exception{
        TakesScreenshot screenshot = ((TakesScreenshot)webdriver);
        File screenshotFile = screenshot.getScreenshotAs(OutputType.FILE);
        File targetFile = new File(desiredPath);
        FileUtils.copyFile(screenshotFile, targetFile);
    }
}
