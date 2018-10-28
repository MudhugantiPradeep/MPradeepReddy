package Sel_Package;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Update_employee {
	public static void main(String[] args) throws Exception 
{
	System.setProperty("webdriver.chrome.driver", "D:\\Complete Selenium\\Software\\chromedriver_win32\\chromedriver.exe");
	ChromeDriver driver= new ChromeDriver();
	//Maximize Window
	driver.manage().window().maximize();
	//-------------
	WebDriverWait wait=new  WebDriverWait(driver,60);
	//Actions class object
	Actions Act=new Actions(driver);
	//-----------------------------------------------------------------
	//1. Open Application
	driver.get("http://apps.qaplanet.in/qahrm/login.php");
	//-----------------------------------------------------------------
	//2. Verify Home Page
	if(wait.until(ExpectedConditions.titleIs("OrangeHRM - New Level of HR Management"))) 
	{
		System.out.println("OrangeHRM - New Level of HR Management is Displayed");
	}
	else {
		System.out.println("OrangeHRM - New Level of HR Management is not Displayed");
		return;
		
	}
// ------------------
	//-- Create Web element
	WebElement objUN=wait.until(ExpectedConditions.presenceOfElementLocated(By.name("txtUserName")));
	WebElement objPWD=driver.findElement(By.name("txtPassword"));
	WebElement objSubmit=driver.findElement(By.name("Submit"));
	WebElement objClear=driver.findElement(By.name("clear"));
	// Verify User name
	if(objUN.isDisplayed()) {
		System.out.println("Username displayed");
	}
	//verify password
	if(objPWD.isDisplayed()) {
		System.out.println("Password is displayed");
	}
	//verify submit and clear button 
	if(objSubmit.isDisplayed() && objClear.isDisplayed()) {
		System.out.println("Submit & Clear are displayed");
	}
	//-------
	String sUN="qaplanet1";
	String sPWD="lab1";
	//------------
	//Login to Orange HRM
	objUN.sendKeys(sUN);
	objPWD.sendKeys(sPWD);
	objSubmit.click();
	//-----------
	
	//**********---------------
	//Verify OrangeHRM page
	if(wait.until(ExpectedConditions.titleIs("OrangeHRM")))
	{
	System.out.println("OrangeHRM Page Displayed");
	}
	else
	{
	System.out.println("Failed to login");
	return;
	} 
	//Get welcome text
	String sWelText=driver.findElement(By.xpath("//ul[@id='option-menu']/li[1]")).getText();
	//Verify Welcome qaplanet1
	if(sWelText.equals("Welcome "+sUN))
	{
	System.out.println("Welcome "+sUN+" displayed");
	}
	//Way 2: Verify only user name
	String[] Arr=sWelText.split(" ");
	if(Arr[1].equals(sUN))
	{
	System.out.println(sUN+" displayed");
	}
	//---------------------------------------------------------
	WebElement objCP=driver.findElement(By.linkText("Change Password"));
	WebElement objLogout=driver.findElement(By.linkText("Logout"));
	//---------------------------------------------------------
	if(objCP.isDisplayed() && objLogout.isDisplayed())
	{
	System.out.println("Change Password and Logout are displayed");
	}
	//----------------------------------------------------------------------
	//Create webElement for PIM
	WebElement objPIM=driver.findElement(By.linkText("PIM"));
	//Mouse over on PIM
	Act.moveToElement(objPIM).perform();
	//Click on addemployee
	driver.findElement(By.linkText("Add Employee")).click();
	//wait 2 sec
	Thread.sleep(2000);
	//Switch to frame
	driver.switchTo().frame("rightMenu");
	//Verify PIM : Add Employee
	if(driver.findElement(By.xpath("//div[@class='mainHeading']/h2")).getText().equals("PIM : Add Employee"))
	{
	System.out.println("PIM : Add Employee displayed");
	}
	else
	{
	System.out.println("Failed to display PIM : Add Employee text");
	}
	//--------------------------------------------------------------------------
	//Create webelement for Save button
	WebElement objSave=driver.findElement(By.id("btnEdit"));
	//Get employee id
	String sEmpCode=driver.findElement(By.name("txtEmployeeId")).getAttribute("value");
	//Click on save
	objSave.click();
	//wait for alert
	Alert A=wait.until(ExpectedConditions.alertIsPresent());
	//Verify alert message
	if(A.getText().equals("Last Name Empty!"))
	{
	System.out.println("Last Name Empty! displayed");
	A.accept();
	}
	//-------------------------------------------------
	String sFN="Sreedhar";
	String sLN="M";
	//--------------------------------------------------
	//Enter last name
	driver.findElement(By.name("txtEmpLastName")).sendKeys(sLN);
	//Click on save
	objSave.click();
	//wait for alert
	Alert A1=wait.until(ExpectedConditions.alertIsPresent());
	//Verify alert message
	if(A1.getText().equals("First Name Empty!"))
	{
	System.out.println("First Name Empty! displayed");
	A1.accept();
	} 
	//Enter first name
	WebElement objFN=driver.findElement(By.name("txtEmpFirstName"));
	objFN.sendKeys(sFN);
	//Click on save
	objSave.click();
	//wait for Personal Details
	if(wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@class='mainHeading']/h2"), "Personal Details")))
	{
	System.out.println("Personal Details text displayed");
	}
	else
	{
	System.out.println("Personal Details did not displayed");
	}
	//switch to parent frame
	driver.switchTo().parentFrame();
	//------------------------------------------------------------------------------
	//Mouse over on PIM
	Act.moveToElement(objPIM).perform();
	//Click on Employee List
	driver.findElement(By.linkText("Employee List")).click();
	//wait 2 sec
	Thread.sleep(2000);
	//Switch to frame
	wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("rightMenu")); 
	//Verify Employee Information
	if(driver.findElement(By.xpath("//div[@class='mainHeading']/h2")).getText().equals("Employee Information"))
	{
	System.out.println("Employee Information displayed");
	}
	else
	{
	System.out.println("Failed to display Employee Information text");
	} 
	//--------------------------------------------------------------------------
	//Get row count
	int rc=driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
	int i;
	for(i=1;i<=rc;i++)
	{
	//Get second column data
	String sEmpID=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[2]")).getText();
	//Get third column data
	String sEmpName=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a")).getText();
	if(sEmpID.equals(sEmpCode) && sEmpName.equals(sFN+" "+sLN))
	{
	System.out.println(sEmpCode+", "+sEmpName+" displayed at: "+i);
	WebElement objU=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a"));
	objU.click();
	break;
	}
	
	}
	//update employee

		WebElement objEdit=driver.findElement(By.id("btnEditPers"));
	    objEdit.click();
	    System.out.println("Clicked on edit");
	    Thread.sleep(2000);

	    WebElement objUFN=driver.findElement(By.name("txtEmpFirstName"));
	    String sUFN="Pradeep";
	    objUFN.clear();
	    objUFN.sendKeys(sUFN);
	    System.out.println("Value updated");
	  //Create webelement for Save button
	    WebElement objUSave=driver.findElement(By.id("btnEditPers"));
	    objUSave.click();
	    driver.findElement(By.xpath("//input[@class=\"backbutton\"]")).click();
	   //////////////verify updated or not
	    int urc=driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		int j;
		for(j=1;j<=rc;j++)
		{
//		//Get First column data
		String sCB=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+j+"]/td[1]")).getText();
		//Get second column data
		String sUEmpID=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+j+"]/td[2]")).getText();
		//Get third column data
		String sUEmpName=driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+j+"]/td[3]/a")).getText();
		if(sUEmpID.equals(sEmpCode) && sUEmpName.equals(sUFN+" "+sLN))
		{
		System.out.println(sEmpCode+", "+sUEmpName+" displayed at: "+j);
		WebElement objDE=driver.findElement(By.xpath("//table[@class='data-table']/descendant::tr/td[contains(text(),'"+sEmpCode+"')]/preceding-sibling::td/input"));
//		objDE.getText();
//	    System.out.println(objDE);
	    objDE.click();
		break;
		}
		
		}

}}
