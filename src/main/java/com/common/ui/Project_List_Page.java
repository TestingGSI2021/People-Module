package com.common.ui;

import static com.common.locators.ProjectList_Page_Locators.*;
import static com.common.locators.ProjetctDetail_Page_Locators.people_main;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.Init;
import com.common.Util;;


public class Project_List_Page extends Annotation implements Init {
	//reviewed
	@Parameters({ "project_Name" })
	@Test(groups = { "Open_Project" })
	public static void Open_Project(String project_Name) {
		// Click on Project
		By project_Name_Ele = By.xpath(project_name.replace("$project_Name", project_Name));
		Util.Report_Log(Status.INFO, "Click on the project :  " + project_Name);
		visible.Wait_Until_Element_Is_Clickable(Constants.expicit_Wait_Time, project_Name_Ele);
		click.Webdriver_Click(project_Name_Ele, true);
		Assert.assertTrue(visible.Is_Displayed(people_main), "Project doesn't Exist");
		Util.Report_Log(Status.PASS, "Successfully clicked on given project : " + project_Name);
	}

}
