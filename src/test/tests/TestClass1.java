import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Random;

public class TestClass1 {

    WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {

        // Launch browser

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Navigate to url 'http://automationexercise.com'

        driver.get("http://automationexercise.com");
        Thread.sleep(1000);

        // Verify that home page is visible successfully

        WebElement mainPageCarousel = driver.findElement(By.className("carousel-inner"));
        Assert.assertTrue(mainPageCarousel.isDisplayed());
    }

    @Test
    public void registerUserTest() throws InterruptedException {

        // Click on 'Signup / Login' button

        driver.findElement(By.xpath("//*[@class='fa fa-lock']")).click();

        // Verify 'New User Signup!' is visible

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='signup-form']/h2")).isDisplayed());
        Assert.assertEquals("New User Signup!", driver.findElement(By.xpath("//*[@class='signup-form']/h2")).getText());

        // Enter name and email address

        driver.findElement(By.xpath("//*[@name='name']")).sendKeys("Fakename");

        Thread.sleep(500);

        Random random = new Random();
        int randomNumber = random.nextInt(1000);
        driver.findElement(By.xpath("(//*[@name='email'])[2]")).sendKeys("fakemail" + randomNumber + "@gmail.com");

        // Click 'Signup' button
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@class='signup-form']//*[@class='btn btn-default']")).click();

        // Verify that 'ENTER ACCOUNT INFORMATION' is visible
        Thread.sleep(500);
        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='title text-center'])[1]")).isDisplayed());
        Assert.assertEquals("ENTER ACCOUNT INFORMATION", driver.findElement(By.xpath("(//*[@class='title text-center'])[1]")).getText());

        // Fill details: Title, Name, Email, Password, Date of birth
        Thread.sleep(500);
        WebElement titleMr=driver.findElement(By.xpath("//input[@id='id_gender1']"));
        if(!titleMr.isSelected()){
            titleMr.click();
        }

        Assert.assertEquals("Fakename", driver.findElement(By.xpath("//*[@id='name']")).getAttribute("value"));
        Assert.assertTrue(driver.findElement(By.xpath("//*[@id='email']")).getAttribute("value").contains("@gmail.com"));

        driver.findElement(By.xpath("//*[@id='password']")).sendKeys("123456789");

        WebElement dropdownDay= driver.findElement(By.id("days"));
        Select selectDay = new Select(dropdownDay);
        selectDay.selectByIndex(7);

        WebElement dropdownMonth= driver.findElement(By.id("months"));
        Select selectMonth = new Select(dropdownMonth);
        selectMonth.selectByVisibleText("April");

        WebElement dropdownYear= driver.findElement(By.id("years"));
        Select selectYear = new Select(dropdownYear);
        selectYear.selectByValue("1980");

        // Select checkbox 'Sign up for our newsletter!'

        WebElement newsletter = driver.findElement(By.xpath("//input[@id='newsletter']"));
        if(!newsletter.isSelected()) {
            newsletter.click();
        }

        // Select checkbox 'Receive special offers from our partners!'

        WebElement specialOffers = driver.findElement(By.xpath("//input[@id='optin']"));
        if(!specialOffers.isSelected()) {
            specialOffers.click();
        }

        // Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number

        driver.findElement(By.id("first_name")).sendKeys("Fakename");

        driver.findElement(By.id("last_name")).sendKeys("Fakelastname");

        driver.findElement(By.id("company")).sendKeys("Fakecompany");

        driver.findElement(By.id("address1")).sendKeys("Fake District");

        driver.findElement(By.id("address2")).sendKeys("Fake Street 10/22");

        WebElement dropdownCountry= driver.findElement(By.id("country"));
        Select selectCountry = new Select(dropdownCountry);
        selectCountry.selectByVisibleText("Canada");

        Thread.sleep(500);
        driver.findElement(By.id("state")).sendKeys("Ontario");

        driver.findElement(By.id("city")).sendKeys("Toronto");

        driver.findElement(By.id("zipcode")).sendKeys("66777");

        driver.findElement(By.id("mobile_number")).sendKeys("0014371234567");

        //  Click 'Create Account button'

        driver.findElement(By.xpath("(//*[@class='btn btn-default'])[1]")).click();

        //  Verify that 'ACCOUNT CREATED!' is visible

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='title text-center']")).isDisplayed());
        Assert.assertEquals("ACCOUNT CREATED!", driver.findElement(By.xpath("//*[@class='title text-center']")).getText());

        // Click 'Continue' button

        driver.findElement(By.xpath("//*[@class='btn btn-primary']")).click();

        // Verify that 'Logged in as username' is visible

        Assert.assertTrue(driver.findElement(By.xpath("(//*[@class='col-sm-8']//a)[10]")).isDisplayed());
        Assert.assertEquals("Logged in as Fakename", driver.findElement(By.xpath("(//*[@class='col-sm-8']//a)[10]")).getText());

        // Click 'Delete Account' button

        driver.findElement(By.xpath("//*[@class='fa fa-trash-o']")).click();
        driver.findElement(By.xpath("//*[@class='btn btn-danger button-form js-tooltip']")).click();

        // Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
        // (There is a problem in delete function in the page, this step does not work)

    }



}
