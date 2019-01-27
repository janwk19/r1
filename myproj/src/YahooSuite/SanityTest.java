package YahooSuite;

import java.io.FileInputStream;

import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import YahooPrg.Compose;
import YahooPrg.Login;
import YahooPrg.MainClass;

public class SanityTest extends MainClass
{
  @Test	
  @Parameters({"browser"})
  public void sanitytesting(String str) throws Exception
  {
	  if(str.matches("firefox"))
	  {
		    System.setProperty("webdriver.gecko.driver","d:\\janwk05\\mylocalrep\\myproj\\geckodriver.exe");
			driver=new FirefoxDriver();
	  }
	  if(str.matches("chrome"))
	  {
		    System.setProperty("webdriver.chrome.driver","d:\\janwk05\\mylocalrep\\myproj\\chromedriver.exe");
			driver=new ChromeDriver();
	  }
	  FileInputStream fin = new FileInputStream("D:\\janwk05\\testcases.xlsx");
		XSSFWorkbook wb=new XSSFWorkbook(fin); //workbook in the file
		
		XSSFSheet ws=wb.getSheet("SanityTest");  //get sheet in the workbook
		Row row;
		
		String classname,methodname;
		
		for(int r=1; r<=ws.getLastRowNum();r++)  //for all rows in the worksheet
		{
			row=ws.getRow(r);
			if(row.getCell(5).getStringCellValue().matches("yes"))
			{
				classname=row.getCell(3).getStringCellValue();
				methodname=row.getCell(4).getStringCellValue();
				Class c=Class.forName(classname);  //Load the Sample class into memory
				Method m=c.getMethod(methodname,null); //get method in the class
				Object obj=c.newInstance();  //create object for the class
				m.invoke(obj,null); 				
			}
		}
		fin.close();
  }
}
