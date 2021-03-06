package com.webdriverutilities;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.Init;
import com.common.Util;


public class Click extends Annotation implements Init {

	private final Logger log = Logger.getLogger(Click.class);

	public void Webdriver_Click(By by, boolean wait_For_Page_To_Load) {
		if (wait_For_Page_To_Load)
			visible.Wait_For_Page_To_Load();
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		driver.findElement(by).click();
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		if (wait_For_Page_To_Load)
			visible.Wait_For_Page_To_Load();
	}


	
	public void Webdriver_Hover(By by) {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by)).build().perform();
	}
	
	public void Webdriver_Click_By_Action(By by) {
		Actions action = new Actions(driver);
		action.click(driver.findElement(by)).build().perform();
	}
	
	public void Webdriver_Click_By_Action(WebElement ele) {
		Actions action = new Actions(driver);
		action.click(ele).build().perform();
	}
	public void Webdriver_Click_By_Action_WithMoveTo(By by){
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(by)).click().build().perform();
	}

	public void Webdriver_Click_By_Action_index(String ele, int i) {
		Actions action = new Actions(driver);
		action.click(driver.findElements(By.xpath(ele)).get(i)).build().perform();
	}
	public void Webdriver_Double_Click_By_Action(By by) {
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		Actions action = new Actions(driver);
		action.doubleClick(driver.findElement(by)).build().perform();
	}

	public void Webdriver_Double_Click(By by) {
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		WebElement ele = driver.findElement(by);
		ele.click();
		ele.click();
	}

	public void Webdriver_Popup_Click_Ok() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 5);
			Alert alert = wait.until(ExpectedConditions.alertIsPresent());
			String message = alert.getText();
			alert.accept();
			log.info("Alert with message: '" + message + "' generated and accepted!");
		} catch (Exception e) {

		}
//		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
//				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
	}

	public void Webdriver_Javascipt_Click(By by) {
		visible.Wait_For_Page_To_Load();
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		WebElement e = driver.findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", e);
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		visible.Wait_For_Page_To_Load();

	}
	public void Webdriver_Javascipt_Click_Without_Wait(By by) {
		WebElement e = driver.findElement(by);
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", e);


	}

	public void Webdriver_Click_And_Accept_Popup(By by, boolean wait_For_Load) {
		visible.Wait_For_Page_To_Load();
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		driver.findElement(by).click();
		Webdriver_Popup_Click_Ok();
		if(wait_For_Load)
		  visible.Wait_For_Page_To_Load();
	}

	public void Webdriver_Select_Checkbox(By by, boolean select) {
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		if (select) {
			if (!driver.findElement(by).isSelected())
				driver.findElement(by).click();
		} else {
			if (driver.findElement(by).isSelected())
				driver.findElement(by).click();
		}
	}

	public void Webdriver_Open_In_New_Tab(By by) {
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		String select_Link_Open_In_New_Tab = Keys.chord(Keys.CONTROL, Keys.RETURN);
		driver.findElement(by).sendKeys(select_Link_Open_In_New_Tab);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		int tab_Size = tabs2.size();
		driver.switchTo().window(tabs2.get(tab_Size - 1));
		visible.Wait_For_Page_To_Load();
	}

	public void Webdriver_Open_In_Tab_By_JavaScript(By by) {
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.elementToBeClickable(driver.findElement(by)));
		String attribute = driver.findElement(by).getAttribute("href");
		((JavascriptExecutor) driver).executeScript("window.open('" + attribute + "','_blank');");
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		int tab_Size = tabs2.size();
		driver.switchTo().window(tabs2.get(tab_Size - 1));
		visible.Wait_For_Page_To_Load();
	}

	public void Webdriver_Close_Current_Tab() {
		driver.close();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		int tab_Size = tabs2.size();
		driver.switchTo().window(tabs2.get(tab_Size - 1));
	}

	public void Webdriver_Click_Link_Text(String link_Text) {
		visible.Wait_For_Page_To_Load();
		driver.findElement(By.linkText(link_Text)).click();
		(new WebDriverWait(driver, Constants.expicit_Wait_Time))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body")));
		visible.Wait_For_Page_To_Load();
	}

	public void Webdriver_Switch_To_New_Tab(int tab_Index) {
		visible.Wait_For_Page_To_Load();
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(tab_Index));
	}
	
	
	public void Webdriver_Context_Click(By by){
		(new WebDriverWait(driver, Constants.expicit_Wait_Time)).until(ExpectedConditions.presenceOfElementLocated(by));
		Actions action = new Actions(driver);
		action.contextClick(driver.findElement(by)).build().perform();
	}

	public void Click_And_Accept_Swirl_Popup(By by)
	{
		Webdriver_Click(by, true);
		Webdriver_Accept_Swirl_Popup();
	}
	
	public void Webdriver_Accept_Swirl_Popup()
	{
		Util.Report_Log(Status.INFO, "Swirl Pop-Up Appeard With Message : " + text.Webdriver_Get_Text(By.id("swal2-content")) + " and Accepted.");
		Webdriver_Click(By.xpath("//button[contains(@class,'swal2-confirm')]"), true);
	}
	

	
	
	
}
