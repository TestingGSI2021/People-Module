package com.common.ui;

import static com.common.locators.Help_Page_Locators.*;
import static com.common.locators.Import_People_Page_Locators.import_Cancel_Button;
import static com.common.locators.Import_People_Page_Locators.upload_File_Button;
import static com.common.locators.New_Record_Form_Locators.new_record_cancel_button;
import static com.common.locators.People_Page_Locators.new_button;
import static com.common.locators.ProjetctDetail_Page_Locators.people_main;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Init;
import com.common.Util;


public class Help_Page extends Annotation implements Init {
	// reviewed
	//close help section
	@Test(groups = { "Close_Help_Section" })
	public static void Close_Help_Section() {

		if (visible.Is_Displayed(help_Close_Button)) {
			Util.Report_Log(Status.INFO, "Help Section is opened ");

			click.Webdriver_Click(help_Close_Button, true);
			Util.Report_Log(Status.PASS, "Help Section Closed Sucessufully");
		} else
			Util.Report_Log(Status.INFO, "Help Section is already closed ");
	}
   
	// reviewed
	@Test(groups = { "Click_Import_Button_On_Help_Section" })
	public static void Click_Import_Button_On_Help_Section() {

	       //Checking Import Button
			Util.Report_Log(Status.INFO, "Click on Import Button From Help Section ");
			click.Webdriver_Click(help_Import_Button, true);
			Assert.assertTrue(visible.Is_Displayed(import_Cancel_Button), "Some issue occured, unable to click on Import button.");
            Util.Report_Log(Status.PASS, "Successfully clicked on Import button, Upload file page open. ");
//			Util.Report_Log(Status.INFO, "Click on 'Cancel' button on 'Import People Page' ");
//			click.Webdriver_Click(import_Cancel_Button, true);
//			visible.Pause(2);
//			Assert.assertTrue(visible.Is_Displayed(help_Close_Button), "Some issue occured, unable to click on 'Cancel' button on Import People Page.");
//			Util.Report_Log(Status.INFO, "Successfully Clicked on 'Cancel' button on 'Import People Page'. Now user is on 'Help' section. ");
	}
	// reviewed
	@Test(groups = { "Click_Create_Button_On_Help_Section" })
	public static void Click_Create_Button_On_Help_Section() {
	     // Checking (Create Records Manually) 'Create' button 
			Util.Report_Log(Status.INFO, "Click on Create Button From Help Section ");
			click.Webdriver_Click(help_New_Record_Form_Button, true);
			visible.Pause(0);
			Assert.assertTrue(visible.Is_Displayed(new_record_cancel_button), "Some issue ocurred, unable to click on Create button.");
            Util.Report_Log(Status.PASS, "Successfully clicked on Create button, 'New Record Form' Opened. ");
			
	}
	
}
