package screen.ExtentReports2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class GetScreenShot {
	
	
	public static String capture(WebDriver driver, String screenShotName) throws IOException{
		
		File source= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
			
			String dest = System.getProperty("user.dir")+"/ErrorScreenShot/"+screenShotName+".png";
		 
			File destination = new File(dest);
			
		FileUtils.copyFile(source,destination);
		
		return dest;
	}

}
