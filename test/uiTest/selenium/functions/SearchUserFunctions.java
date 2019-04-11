package uiTest.selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchUserFunctions extends SeleniumFunctionsBase{

	public void searchUserSuccessFunction(WebDriver driver, String lastname) {
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Search_lastname_txt"))).sendKeys(lastname);

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(prop.getProperty("Search_search_btn"))).sendKeys(Keys.ENTER);
		driver.findElement(By.linkText("johnsmith")).click();
	}
	
	public void editViolationsSuccessFunction(WebDriver driver) {

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_btn"))).sendKeys(Keys.ENTER);
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_noShow_txt"))).sendKeys("2");
	    
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).clear();
	    driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolations_overdue_txt"))).sendKeys("1");	
	    try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editViolationsSubmit_btn"))).sendKeys(Keys.ENTER);
	}
	
	public void changeUserRoleSuccessFunction(WebDriver driver){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editRole_btn"))).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		new Select(driver.findElement(By.id(prop.getProperty("SearchSpecificUser_selectUserRole")))).selectByVisibleText("Manager");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_changeUserRole_btn"))).sendKeys(Keys.ENTER);
	}


}
