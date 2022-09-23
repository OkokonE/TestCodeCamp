import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JumiaSignInTest {

    private WebDriver driver;

    @BeforeTest
    public void start() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");

        //1. Launch firefox browser
        driver = new ChromeDriver();

        //2. Input Jumia URL
        driver.get("https://www.jumia.com.ng/");

        //3. Maximize the window
        driver.manage().window().maximize();

        //4. Get the title
        System.out.println("The Page Title is:" + driver.getTitle());

        //5. Click on Account button and Sign In button
           // Account button
        driver.findElement(By.xpath("/html/body/div[1]/header/section/div[2]/div[1]/label")).click();
        Thread.sleep(1000);
           // Sign In button
        driver.findElement(By.xpath("/html/body/div[1]/header/section/div[2]/div[1]/div/div/div/a")).click();
                //Test 1 - Verify user is taken to sign in page
        if(driver.getCurrentUrl().contains("https://www.jumia.com.ng/customer/account/login/?return=%2F"))
            //Pass
            System.out.println("Correct sign in page");
        else
            //Fail
            System.out.println("Wrong sign in page");
        Thread.sleep(1000);
    }

    @Test (priority = 0)
    public void NegativeTest1() throws InterruptedException {
                //Test 2 - Verify user cannot sign in with both fields blank
        //6. Input E-mail Address
        driver.findElement(By.id("fi-email")).sendKeys("");
        Thread.sleep(1000);

        //7. Input Password
        driver.findElement(By.id("fi-password")).sendKeys("");
        Thread.sleep(1000);

        //8. Click on Login
        driver.findElement(By.xpath("//*[@id='authForm']/button")).click();
        if(driver.getCurrentUrl().contains("https://www.jumia.com.ng/customer/account/login/?return=%2F"))
            //Pass
            System.out.println("Negative Test1 - Login not successful");
        else
            //Fail
            System.out.println("Negative Test1 - Login successful");
        Thread.sleep(10000);
    }

    @Test (priority = 1)
    public void NegativeTest2() throws InterruptedException {
        //Test 3 - Verify user cannot sign in with invalid e-mail and valid password

        //6. Input E-mail Address
        driver.findElement(By.id("fi-email")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-email")).sendKeys("unicokokon@gmail.com");
        Thread.sleep(1000);

        //7. Input Password
        driver.findElement(By.id("fi-password")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-password")).sendKeys("Victory1");
        Thread.sleep(1000);

        //8. Click on Login
        driver.findElement(By.xpath("//*[@id='authForm']/button")).click();
        if(driver.getCurrentUrl().contains("https://www.jumia.com.ng/customer/account/login/?return=%2F"))
            //Pass
            System.out.println("Negative Test2 - Login not successful");
        else
            //Fail
            System.out.println("Negative Test2 - Login successful");
        Thread.sleep(10000);
    }

    @Test (priority = 2)
    public void NegativeTest3() throws InterruptedException {
        //Test 4 - Verify user cannot sign in with valid e-mail and invalid password

        //6. Input E-mail Address
        driver.findElement(By.id("fi-email")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-email")).sendKeys("unycokokon@gmail.com");
        Thread.sleep(1000);

        //7. Input Password
        driver.findElement(By.id("fi-password")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-password")).sendKeys("Victory");
        Thread.sleep(2000);

        //8. Click on Login
        driver.findElement(By.xpath("//*[@id='authForm']/button")).click();
        if(driver.getCurrentUrl().contains("https://www.jumia.com.ng/customer/account/login/?return=%2F"))
            //Pass
            System.out.println("Negative Test3 - Login not successful");
        else
            //Fail
            System.out.println("Negative Test3 - Login successful");
        Thread.sleep(10000);
    }

    @Test (priority = 3)
    public void PositiveTest() throws InterruptedException {
        //Test 5 - Verify user can sign in with valid e-mail and password

        //6. Input E-mail Address
        driver.findElement(By.id("fi-email")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-email")).sendKeys("unycokokon@gmail.com");
        Thread.sleep(1000);

        //7. Input Password
        driver.findElement(By.id("fi-password")).clear();
        Thread.sleep(100);
        driver.findElement(By.id("fi-password")).sendKeys("Victory1");
        Thread.sleep(5000);

        //8. Click on Login
        driver.findElement(By.xpath("//*[@id='authForm']/button")).click();
        System.out.println("Login Page Title is " + driver.getTitle());
        if (driver.getTitle().contains("Login"))
            //Pass
            System.out.println("Positive Test - Login successful");
        else
            //Fail
            System.out.println("Positive Test - Login not successful");
        Thread.sleep(10000);
    }

    @Test (priority = 4)
    public void Logout() throws InterruptedException {
        //9. Click on Hi, Eunice button and Logout button
           // Hi, Eunice button
        driver.findElement(By.xpath("/html/body/div[1]/header/section/div[2]/div[1]/label")).click();
       Thread.sleep(5000);
           // Logout button
        driver.findElement(By.xpath("/html/body/div[1]/header/section/div[2]/div[1]/div/div/form/button")).click();
                //Test 6 - Verify user is logged out and taken to home page
        if(driver.getTitle().contains("Jumia Nigeria | Online Shopping for Groceries, Appliances & More!"))
            //Pass
            System.out.println("Logout successful");
        else
            //Fail
            System.out.println("Logout not successful");
        Thread.sleep(1000);
    }

    @AfterTest
    public void CloseBrowser() {
        //10. Quit the browser
        driver.quit();

    }
}
