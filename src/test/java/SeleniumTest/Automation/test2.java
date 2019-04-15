package SeleniumTest.Automation;

import org.testng.annotations.Test;
import java.io.IOException;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;

import resources.base;


public class test2 extends base {
    
	@Test
    public void fue() throws IOException {
        // TODO Auto-generated method stub
        
       startBrowser();
       JavascriptExecutor js = (JavascriptExecutor) driver;
       js.executeScript("window.qaRunSuites(['fue'])()");
       getReports();
       driver.close();
       
    }
    
    }