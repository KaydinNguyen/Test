package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import org.json.JSONArray;
import org.json.JSONObject;
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
//        driver.findElement(By.name("email")).sendKeys("agent1@dealtap.ca");
//        driver.findElement(By.name("password")).sendKeys("Dealtap1");
//        driver.findElement(By.xpath("//div[text()='Log In']")).click();
//        driver.findElement(By.xpath("//div[contains(text(),'Test: Manager')]")).click();
        
    }
    
    public static WebElement runTest(By element) {
        return driver.findElement(element);
    }
    
    public static void getReports() throws IOException {

        Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(60, TimeUnit.SECONDS)
                .pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
            	return driver.findElement(By.xpath("//div[@class='testReport-caption']"));

            }
        });
        
        
        WebElement tm = driver.findElement(By.xpath("//div[@class='testReport-caption']"));
        WebElement tr = driver.findElement(By.id("dtTestResult"));
        if(tm.isDisplayed()) {
        
        String json = tr.getAttribute("data-report").toString();
	JSONObject obj = new JSONObject(json);
        
        String runId = obj.getString("runId");
        System.out.println("runId: " + runId);
        
        String runHash = obj.getString("runHash");
        System.out.println("runHash: " + runHash);
        
        int timeEnd = obj.getInt("timeEnd");
        System.out.println("timeEnd: " + timeEnd);
        
        String runStatus = obj.getString("runStatus");
        System.out.println("runStatus: " + runStatus);
       
        JSONArray title = obj.getJSONArray("reports");
 
        for(int i = 0; i < title.length(); i++) {
        	JSONObject objectInArray = title.getJSONObject(i);
        	System.out.println("title: " + objectInArray.get("title"));
        	String elementNames = objectInArray.toString();
        	JSONObject reportSuite = new JSONObject(elementNames);
        	JSONArray suiteTitle = reportSuite.getJSONArray("reportSuite");
        	for(int k = 0; k < suiteTitle.length(); k++) {
        		JSONObject suiteInArray = suiteTitle.getJSONObject(k);
        		System.out.println("title: " + suiteInArray.get("title"));
        		System.out.println("isPass: " + suiteInArray.get("isPass"));
        		String reportFlowString = suiteInArray.toString();
        		JSONObject reportFlow = new JSONObject(reportFlowString);
        		JSONArray flowTitle = reportFlow.getJSONArray("reportFlow");
        		for(int l = 0; l < flowTitle.length(); l ++) { 
        		JSONObject flowInArray = flowTitle.getJSONObject(l);
        		System.out.println("title: " + flowInArray.get("title"));
        		System.out.println("isPass: " + flowInArray.get("isPass"));
        		String testInArray = flowInArray.toString();
        		JSONObject testJson = new JSONObject(testInArray);
        		JSONArray reportTest = testJson.getJSONArray("reportTest");

        		
        		for(int m = 0; m < reportTest.length(); m ++) 
        		{
        			JSONObject reportTestArray = reportTest.getJSONObject(m);
        			System.out.println(reportTestArray.get("title"));
        		}

	}

}}}}

}
