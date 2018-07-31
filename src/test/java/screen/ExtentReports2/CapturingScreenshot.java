package screen.ExtentReports2;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class CapturingScreenshot {
	
	public ExtentReports extentReports;
	public ExtentTest extentTest;
	public WebDriver driver;
	
	@BeforeTest
	public void init(){
		
		extentReports = new ExtentReports("./Reports/learn_automation2.html");
	}
	
	
	@Test
	public void captureScreenshot(){
		extentTest = extentReports.startTest("capture screen shot");
		
		driver = new FirefoxDriver();
		driver.get("https://www.freecrm.com/index.html");
		
		driver.findElement(By.xpath("//*[@id='loginForm']/div/input[1]")).sendKeys("naveendommata");
		driver.findElement(By.xpath("//*[@id='loginForm']/div/input[2]")).sendKeys("Welcome@123");
		driver.findElement(By.xpath("//*[@id='loginForm']/div/div/input")).click();
		driver.manage().window().maximize();
		
		String title= driver.getTitle();
		
		Assert.assertEquals("CRMPR", title);
		extentTest.log(LogStatus.PASS, "Test is passed as title is matched");
		
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			
			String screenshotpath= GetScreenShot.capture(driver, "Screenshot for Extent reports");
			
			extentTest.log(LogStatus.FAIL, result.getThrowable());
			extentTest.log(LogStatus.FAIL, "Screenshot Below"+extentTest.addScreenCapture(screenshotpath));
		}
		extentReports.endTest(extentTest);
		
	}
	
	public void endtest(){
		extentReports.flush();
		
	}
	

}
