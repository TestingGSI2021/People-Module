package com.common.ui;

import static com.common.locators.ProjectList_Page_Locators.project_name;
import static com.common.locators.ProjetctDetail_Page_Locators.people_main;

import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Init;
import com.common.Util;

public class ProjectDetail_Page extends Annotation implements Init {
	//reviewed
	@Test(groups = { "Click_On_People_Link" })
	public static void Click_On_People_Link() {
		// Open People Module by clicking on people link
		Util.Report_Log(Status.INFO, "Click on People link to open People Module. ");
		click.Webdriver_Click(people_main, true);

		visible.Wait_For_Page_To_Load();
		navigate.Webdriver_Refresh();
		navigate.Webdriver_Refresh();
		visible.Wait_For_Page_To_Load();

		Util.Report_Log(Status.PASS, "People module/page opened. ");

	}

}
