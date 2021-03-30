package com.common.locators;

import org.openqa.selenium.By;

public class New_Record_Form_Locators {
	public static final By new_record_first_name= By.xpath("//input[@name='data[firstname]']"); // reviewed
//	public static final By new_record_last_name= By.xpath("//input[@name='data[lastname]']");
//	public static final By new_record_email= By.xpath("//input[@name='data[email]']");
//	public static final By new_record_job_title= By.xpath("//input[@name='data[jobTitle]']");
//	public static final By new_record_company= By.xpath("//input[@name='data[company]']");
	
	public static final String new_record_Inputbox = "//input[@name='data[$Input_Box]']"; // reviewed
//	public static final By new_record_save= By.xpath("//button[@name='data[submit]']"); 
//	public static final By new_record_save_and_create_another= By.xpath("//button[text()='Save and create another']");
	public static final By new_record_cancel_button= By.xpath("//button[text()='Cancel']"); // reviewed

	public static final String new_record_popup_buttons= "//button[text()[normalize-space() = '$Button_Name']]"; //reviewed
	public static final By close_Icon = By.xpath("//div[@class='closeButton']");



}
