package com.common.ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.File_Utils;
import com.common.Init;
import com.common.Util;

import static com.common.locators.Help_Page_Locators.help_Close_Button;
import static com.common.locators.Import_People_Page_Locators.download_Sample_File;
import static com.common.locators.Import_People_Page_Locators.import_Next_Button;
import static com.common.locators.Import_People_Page_Locators.import_Page_Button;
import static com.common.locators.Import_People_Page_Locators.map_Your_Record_Page_Header;
import static com.common.locators.Import_People_Page_Locators.upload_Complete;
import static com.common.locators.Import_People_Page_Locators.upload_File_Button;
import static com.common.locators.Login_Page_Locators.otp_Input_Box;
import static com.common.locators.New_Record_Form_Locators.new_record_popup_buttons;
import static com.common.locators.People_Page_Locators.new_button;

import java.beans.Visibility;

public class Import_People_Page extends Annotation implements Init {
	// reviewed
//InputData/Import_File/CSV_File/Test.csv
	@Parameters({ "file_Path" })
	@Test(groups = { "Upload_Given_File_On_Import_Page" })
	public static void Upload_Given_File_On_Import_Page(String file_Path) {
	//	typeText();
		//Uploading file on import people page
	    Util.Report_Log(Status.INFO, "Uploading Given File : " + file_Path);
		sendkeys.Webdriver_Sendkeys_Checking_Presence(upload_File_Button,
				Constants.user_Dir_Path + file_Path);
		visible.Wait_Until_Visible(Constants.expicit_Wait_Time, upload_Complete);
		Assert.assertTrue(visible.Is_Displayed(upload_Complete), "Some issue occured not able to upload file.");
		Util.Report_Log(Status.PASS, file_Path+" given File has been uploaded sucessufully");
	}
	@Parameters({ "button_name", "enable_flag" })
	@Test(groups = { "Verify_Button_Visiblity_On_Import_Page" })
	public static void Verify_Button_Visiblity_On_Import_Page(String button_name, boolean enable_flag) {

			boolean flag = false;
			WebElement e = webelement.Get_Webelement(By.xpath(import_Page_Button.replace("$Button_Name", button_name)));
			Util.Report_Log(Status.INFO, "Check Given button " + button_name + " is clickable or not");
			flag = visible.Is_Enabled(e);
			Assert.assertEquals(flag, enable_flag, "Given button " + button_name + " is clickable : " + flag);
			Util.Report_Log(Status.PASS, "Given button " + button_name + " is clickable : " + flag);
		
	}
	
	@Test(groups = { "Download_Sample_File" })
	public static void Download_Sample_File() {
	
	    Util.Report_Log(Status.INFO, "Downloading sample File");
	    click.Webdriver_Click(download_Sample_File, true);
		Util.Report_Log(Status.INFO, "Clicked on download sample file");
		try {
	    File_Utils.Wait_For_File_To_Download(Constants.browser_Download_Folder, ".csv", "import-people-csv-example");
	    Util.Report_Log(Status.PASS, "Sample File downloaded successfully");
		}
		catch (Exception e) {
			 Util.Report_Log(Status.FAIL, "Unable to download Sample File");
		}
	}
//reviewed
	@Parameters({ "button_name" })
	@Test(groups = { "Click_On_Given_Button_On_Import_Page" })
	public static void Click_On_Given_Button_On_Import_Page(String button_name) throws InterruptedException {

		boolean flag = false;

		Util.Report_Log(Status.INFO, "Click on Button :  " + button_name);
		visible.Wait_Until_Element_Is_Clickable(Constants.expicit_Wait_Time, By.xpath(import_Page_Button.replace("$Button_Name", button_name)));
		click.Webdriver_Click(By.xpath(import_Page_Button.replace("$Button_Name", button_name)), true);
		if (button_name.equalsIgnoreCase("Next")) {
			flag = visible.Is_Enabled(webelement.Get_Webelement(map_Your_Record_Page_Header));

		} else {
			try {
			flag = visible.Is_Enabled(webelement.Get_Webelement(new_button));
			}
			catch (Exception e) {
				flag = visible.Is_Enabled(webelement.Get_Webelement(help_Close_Button));
			}
		}
		Assert.assertTrue(flag, "Unable to click Given Button : " + button_name);
		Util.Report_Log(Status.PASS, "Given Button : " + button_name + " clicked succsessfully.");
	

	}
	// reviewed
	
	@Test(groups = { "By_Default_Map_Records_And_Override_Duplicates" })
	public static void By_Default_Map_Records_And_Override_Duplicates() {
     boolean flag = false;

		
		//Map your fields Next Button
		WebElement e1 = webelement.Get_Webelement(import_Next_Button);
		
		flag = visible.Is_Enabled(e1);
		Assert.assertEquals(flag, true);
		Util.Report_Log(Status.INFO, "Next button is clickable on 'map your fields page': " + flag);
		click.Webdriver_Click(import_Next_Button, true);
		Util.Report_Log(Status.PASS, "Clicked on 'Next' button & user Navigated to 'choose an action page'");
		
		//Choose an action Page Next Button
		WebElement e2 = webelement.Get_Webelement(import_Next_Button);
		
		flag = visible.Is_Enabled(e2);
		Assert.assertEquals(flag, true);
		Util.Report_Log(Status.INFO, "Next button is clickable on 'choose an action' page : " + flag);
		click.Webdriver_Click(import_Next_Button, true);
		Util.Report_Log(Status.PASS, "Clicked on Next Button.");
		visible.Wait_For_Page_To_Load();
		Constants.totalRecordsAfter = People_Page.Records_Count();
		Util.Report_Log(Status.INFO, "Total Records after : " + Constants.totalRecordsAfter);

		
	}



}
