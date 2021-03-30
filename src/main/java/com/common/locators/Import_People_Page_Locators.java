package com.common.locators;

import org.openqa.selenium.By;

public class Import_People_Page_Locators {
	public static final By upload_File_Button= By.xpath("//input[@name='filepond']"); // reviewed
	public static final By import_Next_Button= By.xpath("//button[text()='Next']");	// reviewed
	public static final By import_Cancel_Button= By.xpath("//button[text()='Cancel']");	 // reviewed
	public static final String import_Page_Button= "//button[text()='$Button_Name']";	 // reviewed
	public static final By upload_Complete=By.xpath("//span[text()='Upload complete']"); // reviewed
	public static final By download_Sample_File = By.xpath("//a[text() = ' Download a sample file ']"); //reviewed

	public static final By upload_File_text= By.xpath("//h1[text()='Upload Your File']"); // reviewed
	
	public static final By map_Your_Record_Page_Header = By.xpath("//div[@class='map-fields__header']/h1");


}
