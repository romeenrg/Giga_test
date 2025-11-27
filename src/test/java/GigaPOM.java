import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class GigaPOM {


    protected WebDriver driver;

    public GigaPOM(WebDriver driver){
        this.driver = driver;
    }

    public void navigate(){
        driver.get("http://127.0.0.1:5001/home");
    }

    public void clickLinktext(String text){
        driver.findElement(By.linkText(text)).click();
    }

    public String getTitle(){
        String title = driver.getTitle();
        return title;
    }

    public void SignIn(String text1, String text2){
        driver.get("http://127.0.0.1:5001/login");
        driver.findElement(By.name("username")).sendKeys(text1);
        driver.findElement(By.name("password")).sendKeys(text2);
        driver.findElement(By.xpath("//input[@value='Log in']")).click();

    }

    public void Signup(String text1, String text2){
        driver.get("http://127.0.0.1:5001/login");
        driver.findElement(By.linkText("Sign Up")).click();
        driver.findElement(By.name("username")).sendKeys(text1);
        driver.findElement(By.name("password")).sendKeys(text2);
        driver.findElement(By.name("confirm_password")).sendKeys(text2);
        driver.findElement(By.xpath("//input[@value='Sign up']")).click();
    }

    public void SignOut(){
        driver.findElement(By.linkText("Log Out")).click();
    }

    public void bookTickets(String position , String num){

        driver.findElement(By.xpath("(//a[contains(text(),'More details & booking')])[" + position + "]")).click();
        driver.findElement(By.name("ticket_count")).sendKeys(num); ///LOCATER CHANGED

    }

    public boolean isLinkTextPresent(String linkText) {
        try {
            driver.findElement(By.linkText(linkText));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }


    }

    public boolean isBookingPresent() {
        try {
            driver.findElement(By.xpath("//p[contains(.,'6 tickets for Portishead @ Brixton Academy, London on 2025-12-08 19:30')]"));
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }



}
