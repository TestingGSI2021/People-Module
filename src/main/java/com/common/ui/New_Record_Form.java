package com.common.ui;

import static com.common.locators.Help_Page_Locators.help_Close_Button;
import static com.common.locators.New_Record_Form_Locators.close_Icon;
import static com.common.locators.New_Record_Form_Locators.new_record_Inputbox;
import static com.common.locators.New_Record_Form_Locators.new_record_first_name;
import static com.common.locators.New_Record_Form_Locators.new_record_popup_buttons;
import static com.common.locators.People_Page_Locators.new_button;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.Init;
import com.common.Util;

public class New_Record_Form extends Annotation implements Init {

	// reviewed
	public static void Enter_Given_Value_In_Given_Inputbox(String inputbox_Name, String value) {
		try {
		if ((!value.isEmpty() )|| value == null) {
			Util.Report_Log(Status.INFO, "Enter given value :  " + value + " , in input box : " + inputbox_Name);
			By locator = By.xpath(new_record_Inputbox.replace("$Input_Box", inputbox_Name));
			sendkeys.Webdriver_Sendkeys(locator, value);
			Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(locator, "value"), value,
					"Unable to enter given value : " + value + " , in given field : " + inputbox_Name);
			Util.Report_Log(Status.PASS,
					"Successfully entered given value : " + value + " , in given field : " + inputbox_Name);
		}
		}
		catch (Exception e) {
			// TODO: handle exception
		}
	}

	// reviewed
	@Parameters({ "first_Name", "last_Name", "email", "job_title", "company" })
	@Test(groups = { "Fill_New_Record_Form" })
	public static void Fill_New_Record_Form(@Optional String first_Name, @Optional String last_Name,
			@Optional String email, @Optional String job_title, @Optional String company) {

		Enter_Given_Value_In_Given_Inputbox("firstname", first_Name);
		Enter_Given_Value_In_Given_Inputbox("lastname", last_Name);
		Enter_Given_Value_In_Given_Inputbox("email", email);
		Enter_Given_Value_In_Given_Inputbox("jobTitle", job_title);
		Enter_Given_Value_In_Given_Inputbox("company", company);

//		// Enter First Name
//
//		Util.Report_Log(Status.INFO, "Enter First Name :  " + first_Name);
//		sendkeys.Webdriver_Sendkeys(new_record_first_name, first_Name);
//		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(new_record_first_name, "value"), first_Name,
//				"Unable to enter First Name : " + first_Name);
//		Util.Report_Log(Status.PASS, "Successfully entered First Name :  " + first_Name);
//        
//		// Enter Last Name
//		Util.Report_Log(Status.INFO, "Enter Last Name :  " + last_Name);
//		sendkeys.Webdriver_Sendkeys(new_record_last_name, last_Name);
//		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(new_record_last_name, "value"), last_Name,
//				"Unable to enter Last Name : " + last_Name);
//		Util.Report_Log(Status.PASS, "Successfully entered Last Name :  " + last_Name);
//		
//		// Enter Email ID
//		Util.Report_Log(Status.INFO, "Enter Email :  " + email);
//		sendkeys.Webdriver_Sendkeys(new_record_email, email);
//		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(new_record_email, "value"), email,
//				"Unable to enter email : " + email);
//		Util.Report_Log(Status.PASS, "Successfully entered Email :  " + email);
//		
//
//		// Enter Job Title
//		Util.Report_Log(Status.INFO, "Enter Job Title :  " + job_title);
//		sendkeys.Webdriver_Sendkeys(new_record_job_title, job_title);
//		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(new_record_job_title, "value"), job_title,
//				"Unable to enter Job Title : " + job_title);
//		Util.Report_Log(Status.PASS, "Successfully entered Job Title :  " + job_title);
//
//		// Enter Company Name
//		Util.Report_Log(Status.INFO, "Enter Company Name :  " + company);
//		sendkeys.Webdriver_Sendkeys(new_record_company, company);
//		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(new_record_company, "value"), company,
//				"Unable to enter Company Name : " + company);
//		Util.Report_Log(Status.PASS, "Successfully entered Company Name :  " + company);

	}

// reviewed
	@Parameters({ "button_name", "enable_flag" })
	@Test(groups = { "Verify_Button_Visiblity" })
	public static void Verify_Button_Visiblity(String button_name, boolean enable_flag) {

		String button_Names[] = button_name.split(",");
		for (String name : button_Names) {
			boolean flag = false;
			WebElement e = webelement.Get_Webelement(By.xpath(new_record_popup_buttons.replace("$Button_Name", name)));
			Util.Report_Log(Status.INFO, "Check Given button " + name + " is clickable or not");
			flag = visible.Is_Enabled(e);
			Assert.assertEquals(flag, enable_flag, "Given button " + name + " is clickable : " + flag);
			Util.Report_Log(Status.PASS, "Given button " + name + " is clickable : " + flag);
		}
	}

//reviewed
	@Parameters({ "button_name" })
	@Test(groups = { "Click_On_Given_Button_On_New_Record_Pannel" })
	public static void Click_On_Given_Button_On_New_Record_Pannel(String button_name) throws InterruptedException {

		boolean flag = false;

		Util.Report_Log(Status.INFO, "Click on Button :  " + button_name);
		visible.Wait_Until_Element_Is_Clickable(Constants.expicit_Wait_Time, By.xpath(new_record_popup_buttons.replace("$Button_Name", button_name)));
		click.Webdriver_Click(By.xpath(new_record_popup_buttons.replace("$Button_Name", button_name)), true);
		if (button_name.equalsIgnoreCase("Save and create another")) {
			flag = visible.Is_Enabled(webelement.Get_Webelement(new_record_first_name));

		} else {
			try {
			flag = visible.Is_Enabled(webelement.Get_Webelement(new_button));
			}catch (Exception e) {
				flag = visible.Is_Enabled(webelement.Get_Webelement(help_Close_Button));
			}
		}
		Assert.assertTrue(flag, "Unable to click Given Button : " + button_name);
		Util.Report_Log(Status.PASS, "Given Button : " + button_name + " clicked succsessfully.");
		//if (!button_name.equalsIgnoreCase("Cancel"))
			Constants.totalRecordsAfter = People_Page.Records_Count();
		Util.Report_Log(Status.INFO, "Total Records after : " + Constants.totalRecordsAfter);

	}
	@Test(groups = { "Click_On_Close_Icon_On_New_Record_Pannel" })
	public static void Click_On_Close_Icon_On_New_Record_Pannel() throws InterruptedException {

		boolean flag = false;

		
		Util.Report_Log(Status.INFO, "Click on close icon");
		visible.Pause(2);
		visible.Wait_Until_Element_Is_Clickable(Constants.expicit_Wait_Time, close_Icon);
		click.Webdriver_Click_By_Action_WithMoveTo(close_Icon);
		
			flag = visible.Is_Enabled(webelement.Get_Webelement(new_button));
		
		Assert.assertTrue(flag, "Unable to click Given on close icon");
		Util.Report_Log(Status.PASS, "Closed icon clicked succsessfully.");
			Constants.totalRecordsAfter = People_Page.Records_Count();
		Util.Report_Log(Status.INFO, "Total Records after : " + Constants.totalRecordsAfter);

	}

}
