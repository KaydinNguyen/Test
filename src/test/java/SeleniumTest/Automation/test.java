package SeleniumTest.Automation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import resources.base;

public class test extends base {
    
    private final static String button = "//div[@class='testFlowExplorer-flows']//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[2]//div[1]//div[1]//div[1]//div[1]//div[1]//div[2]";

    @Test
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        
       startBrowser();
       runTest(By.xpath(button)).click();
       getReport();
        
        

    }

}
