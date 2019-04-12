package SeleniumTest.Automation;

import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import resources.base;


public class test2 extends base {
    
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
       startBrowser();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.qaRunSuites(['fue'])()");
       getReports();
       driver.close();
       
    }
    
    }