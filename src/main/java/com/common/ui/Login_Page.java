package com.common.ui;

import static com.common.locators.Login_Page_Locators.email_Input_Box;
import static com.common.locators.Login_Page_Locators.otp_Input_Box;
import static com.common.locators.Login_Page_Locators.submit_Button;
import static com.common.locators.ProjectList_Page_Locators.create_project;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.Init;
import com.common.Mail_Read;
import com.common.Util;
import com.webdriverutilities.Navigate;
import com.webdriverutilities.Send_Keys;

public class Login_Page extends Annotation implements Init {

	// reviewed
	@Test(groups = { "Open Xpmgr", "Login" })
	public static void Open_Xpmgr() {
		//Open XPmgr
		Util.Report_Log(Status.INFO, "Navigating to URL: " + Constants.url);
		navigate.Webdriver_Get(Constants.url);
		Assert.assertEquals(Constants.url, navigate.Webdriver_Get_Current_Url());
		Util.Report_Log(Status.PASS, "Successfully Navigated to URL: " + Constants.url);

	}
// reviewed
	@Test(dependsOnMethods = { "Open_Xpmgr" }, groups = { "Login" })
	public static void Login() {

		// Enter Email ID
		Util.Report_Log(Status.INFO, "Enter given email :  " + Constants.email);
		sendkeys.Webdriver_Sendkeys(email_Input_Box, Constants.email);
		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(email_Input_Box, "value"), Constants.email,
				"Unable to enter given email : " + Constants.email);
		Util.Report_Log(Status.PASS, "Successfully entered given email :  " + Constants.email);

		// Clicking on Submit Button
		Util.Report_Log(Status.INFO, "Click on submit button.");
		click.Webdriver_Click(submit_Button, true);
		Assert.assertTrue(visible.Is_Displayed(otp_Input_Box), "Some issue unable to click on submit button.");
		Util.Report_Log(Status.PASS, "Successfully Clicked on submit button.");

		// Enter OTP
		String otp = Mail_Read.Fetch_Content_From_Mail(Constants.mailFolderName,
				Constants.emailSubjectContent, Constants.emailContent, Constants.lengthOfOTP);
		Util.Report_Log(Status.INFO, "Enter given OTP : " + otp);
		sendkeys.Webdriver_Sendkeys(otp_Input_Box, otp );
		Assert.assertTrue(visible.Is_Displayed(create_project), "Unable to login/ Incorrect OTP.");
		Util.Report_Log(Status.PASS, "Correct OTP entered. Successfully logged in XPmgr.");

	}

}
