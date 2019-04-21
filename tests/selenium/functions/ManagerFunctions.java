package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class ManagerFunctions extends SeleniumFunctionsBase{

	public void searchUserSuccessFunction(String lastname) {
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).sendKeys(lastname);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Search_search_btn"))).sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("johnsmith")).click();
	}
	
	public void editViolationsSuccessFunction() {
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_btn"))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).sendKeys("2");
	    
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).sendKeys("1");	
	    if(testDelay.equals("delay")) {
		    try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolationsSubmit_btn"))).sendKeys(Keys.ENTER);
	}
}
