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


//Coverage Criteria
///Can sign in and log out of a user account
// Can sign up
//Can see a list of gigs with band details, dates and locations
//Can filter by location and see a map of the gig location
//Can filter gigs based on chosen dates
//Past gigs should be clearly labelled
//Can see all gigs from a specific band
///Can book a number of tickets to a specific gig that's not yet been booked
//Can see a list of the accounts booked gigs
//Can cancel a booking
//Can read information about the site
//API providing access to gigs, bookings,accounts etc (returned in JSON)


public class Giga_Tests {
    private static ChromeDriver driver;

    @BeforeAll
    static void launchBrowser() {
        driver = new ChromeDriver();
    }

    @Test
    void testTopMenuBarWorks() throws Exception{
        GigaPOM page = new GigaPOM(driver);
        page.navigate();
        assertEquals("Welcome to Giga",page.getTitle());

        page.clickLinktext("Gigs");
        assertEquals("Gigs",page.getTitle());


        if (!page.isLinkTextPresent("Log In")) {
            page.SignOut();
        }
        page.clickLinktext("Log In");
        assertEquals("Log In",page.getTitle());


        page.clickLinktext("About");
        assertEquals("About Giga",page.getTitle());

        page.clickLinktext("About");
        assertEquals("About Giga",page.getTitle());

        page.clickLinktext("Home");
        assertEquals("Welcome to Giga",page.getTitle());

        page.clickLinktext("Account");
        assertEquals("401 Unauthorised",page.getTitle());
    }


    @Test
    /// sometimes fails if no thread.sleep
    /// can csv file different inputs for username and password
    void testSignInandOut()throws Exception{
        GigaPOM test = new GigaPOM(driver);

        test.SignIn("username","password");
        Thread.sleep(500);
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
        ///account has to have NO bookings for the gig you attempt to book for
    void bookingTickets() throws Exception{
        GigaPOM test = new GigaPOM(driver);
        test.navigate();
        test.SignIn("username","password");
        Thread.sleep(1000);
        test.clickLinktext("Gigs");

        Thread.sleep(1000);

        test.bookTickets("2", "6"); //need to refactor for when you need to scroll to reach element
        driver.findElement(By.xpath("//input[@value='Book gig']")).click();
        driver.findElement(By.cssSelector(".cancel > a")).click();
        //need to assert that the gig i booked, is the gig that shows in booking list
        //remove tickets at end to repeat test
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
