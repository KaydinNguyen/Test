package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;



public class base {

    public static WebDriver driver;
    public static Properties prop;

    public static WebDriver InitializeDriver() throws IOException {

        FileInputStream fis = new FileInputStream("src/main/java/resources/data.properties");
        prop = new Properties();
        prop.load(fis);
        String browserName = prop.getProperty("browser");
        if (browserName.equals("chrome"))

        {
//        	WebDriverManager.
            System.setProperty("webdriver.chrome.driver","/Users/kaydinnguyen/downloads/chromedriver");
            driver = new ChromeDriver();

        }

        return driver;
    }

    public static void startBrowser() throws IOException {
        driver = InitializeDriver();
        driver.get(prop.getProperty("url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.name("email")).sendKeys("agent1@dealtap.ca");
        driver.findElement(By.name("password")).sendKeys("Dealtap1");
        driver.findElement(By.xpath("//div[text()='Log In']")).click();
        driver.findElement(By.xpath("//div[contains(text(),'Test: Manager')]")).click();

    }

    public static WebElement runTest(By element) {
        return driver.findElement(element);
    }

    public static void getReport() throws IOException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return driver.findElement(By.xpath("//div[@class='testReport-caption']"));

            }
        });

        WebElement tm = driver.findElement(By.xpath("//div[@class='testReport-caption']"));
        List<WebElement> pr = driver.findElements(By.xpath("//div[@class='testReportResult-caption']"));
        List<WebElement> header = driver.findElements(By.cssSelector("div[class='testReportTest-caption dt--isClickable']"));
        
        if (tm.isDisplayed()) {

            System.out.println("Test case finished");
            
            for (int k = 0; k < header.size(); k++) {
            	
            	header.get(k).click();            
                //System.out.println(header.get(k).getText().trim());
            	System.out.println(header.get(k).getAttribute("innerText").trim());
                for (int i = 0; i < pr.size(); i++) {
                	
                	//System.out.println(pr.get(i).getText().trim());
                	System.out.println(pr.get(i).getAttribute("innerText").trim());
                }
                                
            }
        }

        else {
            System.out.println("Test case failed");
        }

        driver.close();
        // TODO Auto-generated method stub
    }

}
