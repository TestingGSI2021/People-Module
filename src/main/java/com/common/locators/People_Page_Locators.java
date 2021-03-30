package com.common.locators;

import org.openqa.selenium.By;

public class People_Page_Locators {
	public static final By new_button= By.xpath("//div[contains (@id, 'people')]//span/p[text() = 'New']"); //reviewed
	public static final By import_button= By.xpath("//div[contains (@id, 'people')]//span/p[text() = 'Import']"); //reviewed
	public static final By export_button= By.xpath("//div[contains (@id, 'people')]//span/p[text() = 'Export']"); //reviewed
	
	public static final By records_Count= By.xpath("//p[@class='totalrec']"); //reviewed


	public static final By successfully_Added=By.xpath("[text()='You have successfully created record.']");
	public static final By select_All_Data=By.xpath("//span[text()='Select all data']"); //reviewed
	public static final By clear_All_Data=By.xpath("//span[text()='Clear all data']");
	public static final String select_Given_Row = "//td[contains(@class , '$columnName') and text() = '$Text']//preceding-sibling::td//span[@class='counter ']";

	public static final By no_Data_Found_Text=By.xpath("//h3[text()='No data found.']");
	public static final By delete_All_Records=By.xpath("//p[text()='Delete']");
	public static final By hover_On_DropDown=By.xpath("//span[contains(@class, 'anticon anticon-down ant-dropdown-trigger')]"); //reviewed
	public static final By proceed_To_Delete=By.xpath("//button[text()='Proceed to Delete']");
	
	public static final By add_Filters=By.xpath("//button[contains(@class,'filter')]");
	public static final By clear_All_Filters=By.xpath("//div[contains(@class,'clear-btn')]");
	public static String clear_Filter_Condition_Button = "(//span[@class='close-btn'])[$Filter_Condition_Number]";
	
	public static final By add_Another_Filter_Button = By.xpath("//button[contains(text(),'Add another filter')]");

	public static final By field_Dropdown_Button=By.xpath("(//button[contains (@class,'dropdown')])[1]");
	public static final By field_Dropdown_First_Name_Visibility=By.xpath("//a[text() [normalize-space()='First Name']]");
	public static String field_Options="//a[text() [normalize-space()='$Field_Name']]";
	public static String field_Dropdown_Button_text="(//button[contains (@class,'dropdown')])[1]/span[contains(text() , '$Field_Name')]";

	
	public static final By condition_Dropdown_Button=By.xpath("(//button[contains (@class,'dropdown')])[2]");
	public static final By condition_Dropdown_contains_Visibility=By.xpath("//a[text() [normalize-space()='contains']]");
	public static String condition_Options="//a[text() [normalize-space()='$Condition']]";
	public static String condition_Dropdown_Button_text="(//button[contains (@class,'dropdown')])[2]/span[contains(text() , '$Condition')]";

	public static final By filter_Input=By.xpath("//input[contains(@class, 'filter-input')]");
	
	
	public static String table_Column_Cell_Value="//td[contains(@class, '$Column_Name')]";
	

	public static final By help_Button=By.xpath("//button[contains (@class, 'help')]");
	public static final By arrange_Icon_Button=By.xpath("//img[contains (@src,'icon_arrange')]");

	public static final By sortable_Container=By.xpath("//ul[contains (@id,'SortableContainer')]");
	public static String arrange_Icon_Checkbox="//li[@class = 'sortableHelper' and contains(text() , '$Column_Name')]//input";

	public static String column_Header_Title="//tr//th//span[contains(text() , '$Column_Name')]";

	public static String table_Header_Titles= "//tr//th//span[contains(text() , 'First Name')]//following-sibling::span/span[contains(@class,'dropdown')]";
	public static final By filter_Button_At_Header=By.xpath("//li//img[contains(@src, 'icon_filter')]");
	public static final By sort_AZ_Button_At_Header=By.xpath("//li//img[contains(@src, 'icon_sort_az')]");
	public static final By sort_ZA_Button_At_Header=By.xpath("//li//img[contains(@src, 'icon_sort_za')]");
	public static final By hide_Column_Button_At_Header=By.xpath("//li//img[contains(@src, 'icon_hide')]");
	public static String search_input_Header_Titles= "//input[contains (@placeholder ,'$Column_Name')]";
	public static final By search_Button_At_Header=By.xpath("//button[contains (@class ,'search')]");
	public static final By close_search_Button_At_Header=By.cssSelector("li:nth-child(5) > span > span > span.ant-input-affix-wrapper > span > span > svg");

	public static final String page_Handling_Buttons = "//li[@title ='$Button_Name']";

	
	


}
