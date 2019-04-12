package selenium.functions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PaymentFunctions extends SeleniumFunctionsBase {

	
	public void makeSuccessPayment(WebDriver driver, String firstname, String lastname, String address,
			String cardNumber, String type, String expMonth, String expYear, String cvv) {
					
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_firstname_txt"))).sendKeys(firstname);
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_lastname_txt"))).sendKeys(lastname);
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_address_txt"))).sendKeys(address);
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cardnum_txt"))).sendKeys(cardNumber);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_type_select")))).selectByVisibleText(type);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpMonth_select")))).selectByVisibleText(expMonth);
		new Select(driver.findElement(By.id(prop.getProperty("Payment_ExpYear_select")))).selectByVisibleText(expYear);
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).clear();
		driver.findElement(By.id(prop.getProperty("Payment_cvv_txt"))).sendKeys(cvv);
		if(testDelay.equals("delay")) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		driver.findElement(By.id(prop.getProperty("Payment_pay_btn"))).sendKeys(Keys.ENTER);
	}

}
