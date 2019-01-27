package YahooPrg;

import org.openqa.selenium.By;

import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import YahooProp.ComposeP;
import YahooProp.LoginP;

public class Compose extends MainClass
{
	
 public void sendmail() throws Exception
 {
	driver.findElement(By.xpath(ComposeP.xcompose)).click();
	Thread.sleep(2000);
	try
	{
		if(driver.findElement(By.id(ComposeP.ito)).isDisplayed())
		{
			log=ext.createTest("passTest");
			log.log(Status.PASS, "Compose is working");
			takescreenshot(imagepath+"compose.png");
			log.addScreenCaptureFromPath(imagepath+"compose.png");
			
			
			driver.findElement(By.id(ComposeP.ito)).sendKeys("abcd@gmail.com");
			driver.findElement(By.id(ComposeP.isub)).sendKeys("sample mail");
			driver.findElement(By.name(ComposeP.nbody)).sendKeys("This is test mail for checking");
			driver.findElement(By.id(ComposeP.isend)).click();
		}
	}
	catch(Exception e)
	{
		log=ext.createTest("failTest");
		log.log(Status.FAIL, "Compose NOT working");
		takescreenshot(imagepath+"compose.png");
		log.addScreenCaptureFromPath(imagepath+"compose.png");	
	}	
 }
 public void signout()
 {
	 driver.findElement(By.linkText(LoginP.lsignout)).click();
	 driver.close();
 }
}
