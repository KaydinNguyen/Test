package SeleniumTest.Automation;

import org.testng.annotations.Test;
import org.testng.xml.Parser;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.jsoup.Jsoup;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;

import resources.base;

public class test2 extends base {
    
    private final static String button = "//div[11]//div[1]//div[2]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]";

    @Test
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
       startBrowser();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.qaRunSuites(['sign-up'])()");
       getReports();
       
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
        
        if(tm.isDisplayed() ) {
        	
        	Object obj = new JsonParser().parse(tr.getAttribute("data-report"));
        	JsonObject jo = (JsonObject) obj; 
        	
        	JsonArray title = (JsonArray) jo.get("title");
        	
        	Iterator it = title.iterator();
        	
        	while(it.hasNext()) {
        		System.out.println(title);
        	}
        	
        	//String runID = (String) jo.get("runId").toString();
//        	String title = (String) jo.get("title").toString();
//        	
//        	        	
//        	System.out.println("runId" + " " + runID);
//        	System.out.println("title" + " " + title);
        	
        	//System.out.println(Jsoup.parse(tr.getAttribute("data-report")));
        	
        	
        	
        	
        }

}}
;