package YahooPrg;

import org.openqa.selenium.By
;

import YahooProp.InboxP;

public class Inbox extends MainClass
{
  public void deletemail() throws Exception
  {
	  driver.findElement(By.xpath(InboxP.xmail)).click();
	  Thread.sleep(3000);
	  driver.findElement(By.id(InboxP.idelete)).click();
  }
}
