import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestClass1 {

    WebDriver driver;

    @Before
    public void setUp() throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("http://automationexercise.com");
        Thread.sleep(1000);

        WebElement mainPageCarousel = driver.findElement(By.className("carousel-inner"));
        Assert.assertTrue(mainPageCarousel.isDisplayed());
    }

    @Test
    public void registerUserTest(){

        driver.findElement(By.xpath("//*[@class='fa fa-lock']")).click();

        Assert.assertTrue(driver.findElement(By.xpath("//*[@class='signup-form']/h2")).isDisplayed());
        Assert.assertEquals("New User Signup!", driver.findElement(By.xpath("//*[@class='signup-form']/h2")).getText());
    }



}
