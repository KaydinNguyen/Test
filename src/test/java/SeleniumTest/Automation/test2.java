package SeleniumTest.Automation;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
//import org.testng.xml.Parser;

import com.google.gson.JsonParser;

import resources.base;

public class test2 extends base {
    
    private final static String button = "//div[11]//div[1]//div[2]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]";

    @Test
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
       startBrowser();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.qaRunSuites(['fue'])()");
       getReports();
       driver.close();
       
    }
    
    }