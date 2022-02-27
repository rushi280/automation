package com.test.signup;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vel.config.Configurations;

public class TestCase02 {
	
public static void main(String[] args) throws InterruptedException, EncryptedDocumentException, IOException {
	System.setProperty("webdriver.chrome.driver", Configurations.driverPath);
	WebDriver driver = new ChromeDriver();	
	driver.get(Configurations.appURL);
	driver.manage().window().maximize();
	
	SignUpPOM signup  = new SignUpPOM(driver); 
	Utility util = new Utility();
	
	//TC01
	signup.enterFirstName(util.readExcelSheet(1, 1));
	signup.enterLastName(util.readExcelSheet(2, 1));
	signup.enterEmail(util.readExcelSheet(3, 1));
	signup.selectGender();
	signup.selectMonth(util.readExcelSheet(4, 1));
	
	
	Thread.sleep(4000);
	driver.get(Configurations.appURL);
	//TC02
	signup.enterFirstName(util.readExcelSheet(1, 2));
	signup.enterLastName(util.readExcelSheet(2, 2));
	signup.enterEmail(util.readExcelSheet(3, 2));
	signup.selectGender();
	signup.selectMonth(util.readExcelSheet(4, 2));
	
	
	Thread.sleep(4000);
	
	//TC03
	driver.get(Configurations.appURL);
	signup.enterFirstName(util.readExcelSheet(1, 3));
	signup.enterLastName(util.readExcelSheet(2, 3));
	signup.enterEmail(util.readExcelSheet(3, 3));
	signup.selectGender();
	signup.selectMonth(util.readExcelSheet(4, 3));
}




}
--
package com.test.signup;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.vel.config.Configurations;

public class Utility {
	
	public String readExcelSheet(int row, int col) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream(Configurations.excelSheetPath);

		Sheet sh = WorkbookFactory.create(file).getSheet("TC01");
		String data = sh.getRow(row).getCell(col).getStringCellValue();
		return data;
	}
	

}
package com.test.signup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class SignUpPOM {
	
	//Declaration
	
	@FindBy(xpath="//input[@name='firstname']") private WebElement userName;
	
	@FindBy(xpath="//input[@name='lastname']") private WebElement lastName;
	
	@FindBy(xpath="//input[@name='reg_email__']") private WebElement email;
	
	@FindBy(xpath="(//input[@type='radio'])[2]") private WebElement gender;
	
	@FindBy(xpath="//select[@name='birthday_month']") private WebElement month;
	
	
	//Initilization
	
	SignUpPOM(WebDriver dri)
	{
		PageFactory.initElements(dri, this);
	}
	
	
	//Usage
	
	public void enterFirstName(String name) {
		userName.sendKeys(name);
	}
	
	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}
	
	public void enterEmail(String email1) {
		email.sendKeys(email1);
	}
	
	public void selectGender() {
		gender.click();
	}
	
	public void selectMonth(String value) {
		Select s1 = new Select(month);
		s1.selectByVisibleText(value);
		
	}
	



}
