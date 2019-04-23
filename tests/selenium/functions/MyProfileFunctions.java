package selenium.functions;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;

public class MyProfileFunctions extends SeleniumFunctionsBase {
	
	
	public void MyProfileFunction(String ErrMsg,String UpdatedZip) throws Exception
	{
		
		driver.findElement(By.xpath(prop.getProperty("StudentFaculty_myProfile_link"))).click();
		assertEquals("76010", driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).getAttribute("value").toString());
		driver.findElement(By.id(prop.getProperty("Myprofile_edit_btn"))).click();
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).sendKeys("");
		driver.findElement(By.id(prop.getProperty("Myprofile_update_btn"))).click();
		assertEquals(ErrMsg, driver.findElement(By.id(prop.getProperty("Myprofile_ZipCodeError_txt"))).getAttribute("value").toString());
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).clear();
		driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).sendKeys(UpdatedZip);
		driver.findElement(By.id(prop.getProperty("Myprofile_update_btn"))).click();
		assertEquals(UpdatedZip, driver.findElement(By.id(prop.getProperty("Myprofile_Zip_num"))).getAttribute("value").toString());
		driver.findElement(By.xpath(prop.getProperty("Myprofile_Logout_link"))).click();
	}

}
