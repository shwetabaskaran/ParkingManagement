package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

public class AdminFunctions extends SeleniumFunctionsBase{

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
	
	public void changeUserRoleSuccessFunction(){
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_editRole_btn"))).click();
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Select(driver.findElement(By.id(prop.getProperty("SearchSpecificUser_selectUserRole")))).selectByVisibleText("Manager");
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("SearchSpecificUser_changeUserRole_btn"))).sendKeys(Keys.ENTER);
	}


}
