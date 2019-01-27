package YahooPrg;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

import YahooProp.LoginP;

public class Login extends MainClass
{
   
   public void open()
   {
		   driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		   driver.get(LoginP.url);	   
   }
   public void login() throws Exception
   {
	     open();		
		 driver.findElement(By.name(LoginP.nname)).sendKeys("venkat123456a");
		 driver.findElement(By.name(LoginP.nnext1)).click();
		 Thread.sleep(3000);
		 driver.findElement(By.name(LoginP.npwd)).sendKeys("mqq987654");
		 driver.findElement(By.name(LoginP.nnext2)).click();
		 Thread.sleep(5000);
   }
   public void login_validate() throws Exception
   {
	     FileInputStream fin=new FileInputStream("d:\\sdtnov18\\data.xlsx");
		 XSSFWorkbook wb=new XSSFWorkbook(fin); //workbook in the excel file		
		 XSSFSheet ws=wb.getSheet("Sheet2");   //get sheet in the workbook
			
		 Row row;
		 for(int r=1;r<=ws.getLastRowNum();r++) //for all the rows in Sheet
		 {
				row=ws.getRow(r);
				open();
				driver.findElement(By.name(LoginP.nname)).sendKeys(row.getCell(0).getStringCellValue());
				driver.findElement(By.name(LoginP.nnext1)).click();
				driver.findElement(By.name(LoginP.npwd)).sendKeys(row.getCell(1).getStringCellValue());
				driver.findElement(By.name(LoginP.nnext2)).click();
				try
				{
					if(driver.findElement(By.linkText(LoginP.lsignout)).isDisplayed())
					{
						row.createCell(2).setCellValue("login is success");
						driver.findElement(By.linkText(LoginP.lsignout)).click();
					}
				}
				catch(Exception obj)
				{
					String str=driver.findElement(By.xpath(LoginP.xerrmsg)).getText();
					row.createCell(2).setCellValue("Login is failed   :"+str);
				}				
			}
			FileOutputStream fout=new FileOutputStream("d:\\sdtnov18\\data.xlsx");
			wb.write(fout);
			fin.close();
			fout.close();
   }
   public void signup_validate() throws Exception
   {
	     open();
		 Thread.sleep(5000);
		 JavascriptExecutor js=(RemoteWebDriver)driver;
		 js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		 
		 new Actions(driver).moveToElement(driver.findElement(By.id(LoginP.isignup)))
		                    .click().perform();
		 
		 Thread.sleep(3000);
		 try
		 {
			 if(driver.findElement(By.name(LoginP.nfname)).isDisplayed())
			 {
				log=ext.createTest("passTest");
				log.log(Status.PASS, "SignUp link is working");
				takescreenshot(imagepath+"regpage.png");
				log.addScreenCaptureFromPath(imagepath+"regpage.png");
			 }
			 					
		 }
		 catch(Exception e)
		 {
			    log=ext.createTest("failTest");
				log.log(Status.FAIL, "SignUp link NOT working");
				takescreenshot(imagepath+"regpage.png");
				log.addScreenCaptureFromPath(imagepath+"regpage.png");
		 }
	 }
   }

