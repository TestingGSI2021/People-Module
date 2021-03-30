package com.common.ui;

import static com.common.locators.Help_Page_Locators.help_Close_Button;
import static com.common.locators.Import_People_Page_Locators.upload_File_text;
import static com.common.locators.People_Page_Locators.add_Another_Filter_Button;
import static com.common.locators.People_Page_Locators.add_Filters;
import static com.common.locators.People_Page_Locators.arrange_Icon_Button;
import static com.common.locators.People_Page_Locators.arrange_Icon_Checkbox;
import static com.common.locators.People_Page_Locators.clear_All_Data;
import static com.common.locators.People_Page_Locators.clear_All_Filters;
import static com.common.locators.People_Page_Locators.clear_Filter_Condition_Button;
import static com.common.locators.People_Page_Locators.close_search_Button_At_Header;
import static com.common.locators.People_Page_Locators.column_Header_Title;
import static com.common.locators.People_Page_Locators.condition_Dropdown_Button;
import static com.common.locators.People_Page_Locators.condition_Dropdown_Button_text;
import static com.common.locators.People_Page_Locators.condition_Dropdown_contains_Visibility;
import static com.common.locators.People_Page_Locators.condition_Options;
import static com.common.locators.People_Page_Locators.delete_All_Records;
import static com.common.locators.People_Page_Locators.export_button;
import static com.common.locators.People_Page_Locators.field_Dropdown_Button;
import static com.common.locators.People_Page_Locators.field_Dropdown_Button_text;
import static com.common.locators.People_Page_Locators.field_Dropdown_First_Name_Visibility;
import static com.common.locators.People_Page_Locators.field_Options;
import static com.common.locators.People_Page_Locators.filter_Button_At_Header;
import static com.common.locators.People_Page_Locators.filter_Input;
import static com.common.locators.People_Page_Locators.help_Button;
import static com.common.locators.People_Page_Locators.hide_Column_Button_At_Header;
import static com.common.locators.People_Page_Locators.hover_On_DropDown;
import static com.common.locators.People_Page_Locators.import_button;
import static com.common.locators.People_Page_Locators.new_button;
import static com.common.locators.People_Page_Locators.no_Data_Found_Text;
import static com.common.locators.People_Page_Locators.page_Handling_Buttons;
import static com.common.locators.People_Page_Locators.proceed_To_Delete;
import static com.common.locators.People_Page_Locators.records_Count;
import static com.common.locators.People_Page_Locators.search_Button_At_Header;
import static com.common.locators.People_Page_Locators.search_input_Header_Titles;
import static com.common.locators.People_Page_Locators.select_All_Data;
import static com.common.locators.People_Page_Locators.select_Given_Row;
import static com.common.locators.People_Page_Locators.sort_AZ_Button_At_Header;
import static com.common.locators.People_Page_Locators.sort_ZA_Button_At_Header;
import static com.common.locators.People_Page_Locators.sortable_Container;
import static com.common.locators.People_Page_Locators.table_Column_Cell_Value;
import static com.common.locators.People_Page_Locators.table_Header_Titles;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.common.Annotation;
import com.common.Constants;
import com.common.File_Utils;
import com.common.Init;
import com.common.Util;

public class People_Page extends Annotation implements Init {

	@Test(groups = { "Open_Help_Section" })
	public static void Open_Help_Section() {
		// Click on Help button
		if (visible.Is_Displayed(help_Close_Button)) {
			Util.Report_Log(Status.INFO, "Help Scetion is already opened");
		} else {
			Util.Report_Log(Status.INFO, "Click on 'Help' button & open 'Help' section.");
			click.Webdriver_Click(help_Button, true);
			Assert.assertTrue(visible.Is_Displayed(help_Close_Button),
					"Some issue not able to click on 'Help' Button.");
			Util.Report_Log(Status.PASS, "Successfully clicked on Help button. Help Section have been opened.");
		}
	}

	public static int Records_Count() {
		int count = 0;
		try {
			visible.Wait_Until_Visible(Constants.expicit_Wait_Time, records_Count);

			String total_Text = text.Webdriver_Get_Text(records_Count);
			String[] total_Text_Split = total_Text.split("of");
			String total = total_Text_Split[1].trim().replaceAll("[^0-9 ]", "").trim();
			count = Integer.parseInt(total);

		} catch (Exception e) {
			Util.Report_Log(Status.INFO, "No  record found");

		}
		return count;
	}

	@Test(groups = { "Click_On_Add_Filters" })
	public static void Click_On_Add_Filters() {
		// Click on 'Add Filter' button
		Util.Report_Log(Status.INFO, "Click on Add Filters");
		click.Webdriver_Click(add_Filters, true);
		Assert.assertTrue(visible.Is_Displayed(clear_All_Filters), "Add Filters button is not clickable.");
		Util.Report_Log(Status.PASS, "Successfully clicked on Add Filters button.");
	}

	@Test(groups = { "Clear_All_Filters" })
	public static void Clear_All_Filters() {
		// Clear All Filters
		Util.Report_Log(Status.INFO, "Click on 'Clear All Filter' button.");
		click.Webdriver_Click(clear_All_Filters, true);
		visible.Pause(5);
		Assert.assertTrue(visible.Is_Displayed(export_button), "Unable to clear the filters.");
		Util.Report_Log(Status.PASS, "All the filters have been removed.");

	}

	@Parameters({ "Field_Name", "Condition", "filter_Input_Value" })
	@Test(groups = { "Add_Filters" })
	public static void Add_Filters(String Field_Name, String Condition, String filter_Input_Value) {

		// Click on 'Field Drop-down Button'
		Util.Report_Log(Status.INFO, "Click on field dropdown button");
		click.Webdriver_Click(field_Dropdown_Button, true);
		Assert.assertTrue(visible.Is_Displayed(field_Dropdown_First_Name_Visibility),
				"Unable to click on field dropdown button");
		Util.Report_Log(Status.PASS, "Successfully clicked on field dropdown button");

		// Select Field where filter needs to be apply
		By Field_Name_Ele = By.xpath(field_Options.replace("$Field_Name", Field_Name));
		Util.Report_Log(Status.INFO, "Select the field name :  " + Field_Name);
		click.Webdriver_Click(Field_Name_Ele, true);
		By field_Dropdown_Button_text_Ele = By.xpath(field_Dropdown_Button_text.replace("$Field_Name", Field_Name));
		Assert.assertTrue(visible.Is_Displayed(field_Dropdown_Button_text_Ele), "Unable to select the field");
		Util.Report_Log(Status.PASS, "Successfully selected the field : " + Field_Name);

		// Click on 'condition drop down Button' like contains/doesn't contains
		Util.Report_Log(Status.INFO, "Click on condition dropdown button(contains/doesn't contains)");
		click.Webdriver_Click(condition_Dropdown_Button, true);
		Assert.assertTrue(visible.Is_Displayed(condition_Dropdown_contains_Visibility),
				"Unable to click on condition (contains/doesn't contains) dropdown button");
		Util.Report_Log(Status.PASS, "Successfully clicked on condition (contains/doesn't contains) dropdown button");

		// Select condition like contains/ doesn't contains
		By Condition_Ele = By.xpath(condition_Options.replace("$Condition", Condition));
		Util.Report_Log(Status.INFO, "Select the condition (contains/doesn't contains) :  " + Condition);
		click.Webdriver_Click(Condition_Ele, true);
		if (!Condition.equalsIgnoreCase("contains"))
			Condition = "DNContain";
		By condition_Dropdown_Button_text_Ele = By
				.xpath(condition_Dropdown_Button_text.replace("$Condition", Condition));
		Assert.assertTrue(visible.Is_Displayed(condition_Dropdown_Button_text_Ele), "Unable to select condition");
		Util.Report_Log(Status.PASS, "Successfully selected the condition : " + Condition);

		// Fill the text in input field of filter
		Util.Report_Log(Status.INFO, "Fill the text in input field of filter ");
		sendkeys.Webdriver_Sendkeys(filter_Input, filter_Input_Value);
		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(filter_Input, "value"), filter_Input_Value,
				"Unable to enter input value  : " + filter_Input_Value);
		Util.Report_Log(Status.PASS, "Successfully entered value in input : " + filter_Input_Value);
		visible.Pause(2);

	}

	@Test(groups = { "Click_On_Clear_Filter_Condition_Button" })
	public static void Click_On_Clear_Filter_Condition_Button(String filter_Condition_Number) {
		Util.Report_Log(Status.INFO, "Click on given 'Clear Filter condition' button : " + filter_Condition_Number);
		click.Webdriver_Click(
				By.xpath(clear_Filter_Condition_Button.replace("$Filter_Condition_Number", filter_Condition_Number)),
				true);
		visible.Pause(5);
		Util.Report_Log(Status.PASS, "close filter condition button clicked successfully");

	}

	@Test(groups = { "Click_On_Add_Another_Filter_Button" })
	public static void Click_On_Add_Another_Filter_Button() {
		Util.Report_Log(Status.INFO, "Click on Add Another Filter Button");
		click.Webdriver_Click(add_Another_Filter_Button, true);
		Util.Report_Log(Status.PASS, "Add Another Filter Button clicked successfully");

	}

	// reviewed
	@Test(groups = { "Click_On_New_Button" })
	public static void Click_On_New_Button() {
		Constants.totalRecordsBefore = People_Page.Records_Count();
		Util.Report_Log(Status.INFO, "Total Records before : " + Constants.totalRecordsBefore);

		click.Webdriver_Click(new_button, true);
		Util.Report_Log(Status.PASS, "New Record Form Open ");
	}

	// reviewed
	@Test(groups = { "Click_On_Export_Button" })
	public static void Click_On_Export_Button() throws Exception {
		Util.Report_Log(Status.INFO, "Click on export button");
		click.Webdriver_Click(export_button, true);

	}

	// reviewed
	@Test(groups = { "Click_On_Import_Button" })
	public static void Click_On_Import_Button() {
		Constants.totalRecordsBefore = People_Page.Records_Count();
		Util.Report_Log(Status.INFO, "Total Records before importing: " + Constants.totalRecordsBefore);

		Util.Report_Log(Status.INFO, "Click on import button");
		click.Webdriver_Click(import_button, true);
		Assert.assertTrue(visible.Is_Displayed(upload_File_text), "Some issue not able to click on import button.");
		Util.Report_Log(Status.PASS, "Import People Page have been opened.");
	}

	@Test(groups = { "Open_Arrange_Icon_Container" })
	public static void Open_Arrange_Icon_Container() {
		// Click on Arrange Icon
		Util.Report_Log(Status.INFO, "Click on 'Arrange Icon' button & open 'Arrange_Icon_Container' section.");
		click.Webdriver_Click(arrange_Icon_Button, true);
		Assert.assertTrue(visible.Is_Displayed(sortable_Container),
				"Some issue not able to click on 'Arrange Icon' Button.");
		Util.Report_Log(Status.PASS,
				"Successfully clicked on 'Arrange Icon' button. 'Arrange_Icon_Container' section have been opened.");

	}

	@Parameters({ "Column_Name" })
	@Test(groups = { "Hide_Unhide_Table_Column" })
	public static void Hide_Unhide_Table_Column(String Column_Name) {
		// Click on Checkbox to Hide the column
		Util.Report_Log(Status.INFO, "Click on 'checkbox' to hide the column : " + Column_Name);
		By arrange_Icon_Checkbox_Ele = By.xpath(arrange_Icon_Checkbox.replace("$Column_Name", Column_Name));
		click.Webdriver_Click(arrange_Icon_Checkbox_Ele, true);

		By column_Header_Title_Ele = By.xpath(column_Header_Title.replace("$Column_Name", Column_Name));
		Assert.assertFalse(visible.Is_Displayed(column_Header_Title_Ele),
				"Some issue not able to click on 'checkbox' to hide the column : " + Column_Name);
		Util.Report_Log(Status.PASS,
				"Successfully clicked on 'Checkbox & : " + Column_Name + " column have been hide from the table.");

		// Click on Checkbox to Unhide the Column
		Util.Report_Log(Status.INFO, "Click on 'checkbox' to unhide the column :" + Column_Name);
		click.Webdriver_Click(arrange_Icon_Checkbox_Ele, true);

		Assert.assertTrue(visible.Is_Displayed(column_Header_Title_Ele),
				"Some issue not able to click on 'checkbox' to unhide the column : " + Column_Name);
		Util.Report_Log(Status.PASS,
				"Successfully clicked on 'Checkbox & : " + Column_Name + " column have been unhide from the table.");

	}

	// reviewed
	public static void Select_All_Records() {
		navigate.Webdriver_Refresh();

		Util.Report_Log(Status.INFO, "Select all records.");
		visible.Pause(2);
		click.Webdriver_Hover(hover_On_DropDown);
		visible.Wait_Until_Visible(Constants.expicit_Wait_Time, select_All_Data);
		;
		click.Webdriver_Click(select_All_Data, true);
		Assert.assertTrue(visible.Is_Displayed(delete_All_Records), "All records have not been selected.");
		Util.Report_Log(Status.PASS, "All records have been selected.");
	}

	// reviewed
	public static void Clear_All_Records() {
		navigate.Webdriver_Refresh();

		Util.Report_Log(Status.INFO, "Clear all records.");
		visible.Pause(2);
		click.Webdriver_Hover(hover_On_DropDown);
		visible.Wait_Until_Visible(Constants.expicit_Wait_Time, select_All_Data);
		;
		click.Webdriver_Click(clear_All_Data, true);
		Assert.assertTrue(visible.Is_Displayed(delete_All_Records), "All records have not been cleared.");
		Util.Report_Log(Status.PASS, "All records have been cleared.");
	}

	// reviewed
	@Parameters({ "file_Name", "extension" })
	@Test(groups = { "Verify_File_Downloaded" })
	public static void Verify_File_Downloaded(String file_Name, String extension) throws Exception {
		String downloaded_File_Name = File_Utils.Wait_For_File_To_Download(
				Constants.user_Dir_Path + Constants.browser_Download_Folder, extension, file_Name);
		Assert.assertEquals(downloaded_File_Name, Constants.downloadFileName,
				"Some issue ocuured, File is not Downloaded");
		Util.Report_Log(Status.PASS, "Records have been downloaded successfully.");
	}

	// reviewed

	// reviewed
	@Parameters({ "expected_Difference" })
	@Test(groups = { "Verify_Record_Added_Or_Not" })
	public static void Verify_Record_Added_Or_Not(int expected_Difference) {

		int actual_Difference = Constants.totalRecordsAfter - Constants.totalRecordsBefore;

		Assert.assertEquals(expected_Difference, actual_Difference);
		Util.Report_Log(Status.PASS, "No of records are same as expected");
	}

	// reviewed
	public static void Select_Records_By_Using_Given_Data_Of_Given_Column(String column_Name, String data) {
		Util.Report_Log(Status.INFO, "Select given records.");
		String xpath = (select_Given_Row.replace("$columnName", column_Name)).replace("$Text", data);
		click.Webdriver_Click(By.xpath(xpath), true);
		Assert.assertTrue(visible.Is_Displayed(delete_All_Records), "Given record does not selected successfully.");
		Util.Report_Log(Status.PASS, "Given record have selected successfully.");
	}

	// reviewed
	@Test(groups = { "Delete_Records" })
	public static void Delete_Records() {
		while (true) {
			Util.Report_Log(Status.INFO, "Deleting Records from table.");
			if (visible.Is_Displayed(no_Data_Found_Text)) {
				Util.Report_Log(Status.INFO, "No more records to delete.");
				break;
			} else {

				Select_All_Records();

				Util.Report_Log(Status.INFO, "Clicking on Delete Button");
				click.Webdriver_Click(delete_All_Records, true);
				Assert.assertTrue(visible.Is_Displayed(proceed_To_Delete),
						"Somme issue occurs 'delete' button is not clicked.");
				Util.Report_Log(Status.PASS, "Clicked on Delete Button");

				Util.Report_Log(Status.INFO, "Clicking on 'Proceed to Delete' Button");
				click.Webdriver_Click(proceed_To_Delete, true);
				Assert.assertTrue(visible.Is_Displayed(help_Close_Button),
						"Somme issue occurs 'Proceed to Delete' button is not clicked.");
				Util.Report_Log(Status.PASS, "Clicked on 'Proceed to Delete' Button");

				Util.Report_Log(Status.INFO, "Deleting All Records");

			}
		}
		Assert.assertTrue(visible.Is_Displayed(help_Close_Button),
				"Somme issue occurs not able to delete the records.");
		Util.Report_Log(Status.PASS, "Successfully deleted all records.");
	}

	@Test(groups = { "Verify_Warning_Message_For_Delete" })
	public static void Verify_Warning_Message_For_Delete() {

		Util.Report_Log(Status.INFO, "Clicking on 'Proceed to Delete' Button");
		click.Webdriver_Click(proceed_To_Delete, true);
		Assert.assertTrue(visible.Is_Displayed(help_Close_Button),
				"Somme issue occurs 'Proceed to Delete' button is not clicked.");
		Util.Report_Log(Status.PASS, "Clicked on 'Proceed to Delete' Button");

		Util.Report_Log(Status.INFO, "Deleting All Records");
		Assert.assertTrue(visible.Is_Displayed(help_Close_Button),
				"Somme issue occurs not able to delete the records.");
		Util.Report_Log(Status.PASS, "Successfully deleted all records.");

	}

	@Parameters({ "Column_Name" })
	@Test(groups = { "Click_On_Header_Title_DropDown" })
	public static void Click_On_Header_Title_DropDown(String Column_Name) {
		// Click on Header Title DropDown
		Util.Report_Log(Status.INFO, "Click on Header Title DropDown : " + Column_Name);
		By table_Header_Titles_Name = By.xpath(table_Header_Titles.replace("$Column_Name", Column_Name));
		click.Webdriver_Click(table_Header_Titles_Name, true);
		visible.Pause(1);
		visible.Wait_Until_Visible(Constants.expicit_Wait_Time, filter_Button_At_Header);
		Assert.assertTrue(visible.Is_Displayed(filter_Button_At_Header),
				"Some issue not able to click on Header Title DropDown : " + Column_Name);
		Util.Report_Log(Status.PASS, "Successfully clicked on Header Title DropDown. ");

	}

	@Parameters({ "Column_Name", "Condition", "filter_Input_Value" })
	@Test(groups = { "Verify_Filter_Data_In_Given_Table_Column" })
	public static void Verify_Filter_Data_In_Given_Table_Column(String Column_Name, String Condition,
			String filter_Input_Value) {
		visible.Pause(2);


		By column_Name = By.xpath(table_Column_Cell_Value.replace("$Column_Name", Column_Name));

		List<String> column_Values = text.Get_Elements_Text(column_Name);
		Util.Report_Log(Status.INFO, column_Values.toString());

		if (Condition.equalsIgnoreCase("contains")) {

			for (String value : column_Values) {
				Util.Report_Log(Status.INFO, "Check " + Column_Name + " column contains value : " + filter_Input_Value);
				if (value.toLowerCase().contains(filter_Input_Value.toLowerCase())) {
					Util.Report_Log(Status.PASS, Column_Name + " column contains value : " + filter_Input_Value);

				} else {
					Util.Report_Log(Status.FAIL,
							Column_Name + " column does not contains value : " + filter_Input_Value);
					break;
				}
			}

		} else {
			for (String value : column_Values) {
				Util.Report_Log(Status.INFO,
						"Check " + Column_Name + " column doesn't contains value : " + filter_Input_Value);
				if (!value.toLowerCase().contains(filter_Input_Value.toLowerCase())) {
					Util.Report_Log(Status.PASS,
							Column_Name + " column does not contains value : " + filter_Input_Value);

				} else {
					Util.Report_Log(Status.FAIL, Column_Name + " column contains value : " + filter_Input_Value);
					break;
				}
			}
		}
	}

	@Parameters({ "Column_Name" })
	@Test(groups = { "Click_Filters_From_Table_Header" })
	public static void Click_Filters_From_Table_Header(String Column_Name) {

		Util.Report_Log(Status.INFO, "Click on filter button from table Header : " + Column_Name);

		click.Webdriver_Click(filter_Button_At_Header, true);
		Assert.assertTrue(visible.Is_Displayed(clear_All_Filters),
				"Unable to click on filter button from table Header : " + Column_Name);
		Util.Report_Log(Status.PASS, "Successfully clicked on filter button from table Header : " + Column_Name);

	}

	@Parameters({ "Column_Name", "Column_Data_Name" })
	@Test(groups = { "Validate_Sorting_A_To_Z" })
	public static void Validate_Sorting_A_To_Z(String Column_Name, String Column_Data_Name) {
//		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
//			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
//		}

		List<String> values_Before_Sort = Read_All_Data_From_People_Table(Column_Data_Name, "Next Page");

		List<String> sorted_values = values_Before_Sort.stream().sorted().collect(Collectors.toList());
		Util.Report_Log(Status.INFO, "Sorted : " + sorted_values);

		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
			Click_On_Header_Title_DropDown(Column_Name);

		}


		// Click on Sort (A-Z) & Verify same in table.

		Util.Report_Log(Status.INFO, "Click on 'Sort (A-Z)' of Header : " + Column_Name);
		click.Webdriver_Click(sort_AZ_Button_At_Header, true);

		List<String> values_After_Sort = Read_All_Data_From_People_Table(Column_Data_Name, "Next Page");
		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
			Click_On_Header_Title_DropDown(Column_Name);

		}
		Assert.assertTrue(sorted_values.equals(values_After_Sort), "Unable to Sort A-Z");

		Util.Report_Log(Status.PASS, "Successfully clicked on 'Sort (A-Z)' of Header : " + Column_Name);

	}

	@Parameters({ "Column_Name", "Column_Data_Name" })
	@Test(groups = { "Validate_Sorting_Z_To_A" })
	public static void Validate_Sorting_Z_To_A(String Column_Name, String Column_Data_Name) {
//		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
//			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
//			Click_On_Header_Title_DropDown(Column_Name);
//
//		}
		List<String> values_Before_Sort = Read_All_Data_From_People_Table(Column_Data_Name, "Next Page");
		Collections.sort(values_Before_Sort, Collections.reverseOrder());

		List<String> sorted_values = values_Before_Sort;
		Util.Report_Log(Status.INFO, "reverse Sorted : " + sorted_values);

		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
			Click_On_Header_Title_DropDown(Column_Name);

		}

		// Click on Sort (A-Z) & Verify same in table.

		Util.Report_Log(Status.INFO, "Click on 'Sort (Z-A)' of Header : " + Column_Name);
		click.Webdriver_Click(sort_ZA_Button_At_Header, true);

		List<String> values_After_Sort = Read_All_Data_From_People_Table(Column_Data_Name, "Next Page");
		if (webelement.Is_Present(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), 30)) {
			click.Webdriver_Click(By.xpath(page_Handling_Buttons.replace("$Button_Name", "1")), true);
			Click_On_Header_Title_DropDown(Column_Name);

		}
		Assert.assertTrue(sorted_values.equals(values_After_Sort), "Unable to Sort Z-A");

		Util.Report_Log(Status.PASS, "Successfully clicked on 'Sort (Z-A)' of Header : " + Column_Name);

	}

	@Parameters({ "Search_String", "Search_Inputbox_Name" })
	@Test(groups = { "Validate_Search_Functionality" })

	public static void Validate_Search_Functionality(String Search_String, String Search_Inputbox_Name) {
		boolean flag = false;
		// Search Option in column from Header DropDown
		Util.Report_Log(Status.INFO, "Search for " + Search_String + " in " + Search_Inputbox_Name);
		By search_input_Header_Titles_Ele = By
				.xpath(search_input_Header_Titles.replace("$Column_Name", Search_Inputbox_Name));
		sendkeys.Webdriver_Sendkeys(search_input_Header_Titles_Ele, Search_String);
		Assert.assertEquals(text.Webdriver_Get_Text_Of_Attribute(search_input_Header_Titles_Ele, "value"),
				Search_String, "Unable to enter Last Name : " + Search_String);

		click.Webdriver_Click(search_Button_At_Header, true);

		By column_Name = By.xpath(table_Column_Cell_Value.replace("$Column_Name", Search_Inputbox_Name));

		List<String> column_Values = text.Get_Elements_Text(column_Name);

		if (column_Values.size() >= 1) {
			for (String cell_value : column_Values) {
				if (cell_value.toLowerCase().contains(Search_String.toLowerCase())) {
					flag = true;

				}

				else {
					flag = false;
					break;

				}

			}
			if (flag) {
				Util.Report_Log(Status.PASS,
						"Search Filter is working fine for search result: " + Search_Inputbox_Name);
			} else {
				Util.Report_Log(Status.FAIL,
						"Search Filter is not working fine for search result: " + Search_Inputbox_Name);
			}
		} else {
			Util.Report_Log(Status.INFO, "No data found for given Search");

		}

		click.Webdriver_Click(close_search_Button_At_Header, true);
		visible.Pause(2);
	}

	@Parameters({ "Column_Name", "Arrange_Icon_Column_Name" })
	@Test(groups = { "Validate_Column_Hide_Functinality_From_Column_Header" })

	public static void Validate_Column_Hide_Functinality_From_Column_Header(String Column_Name,
			String Arrange_Icon_Column_Name) {
		// Click on Hide option from Header DropDown
		Util.Report_Log(Status.INFO, "Click on 'Hide' option of Header : " + Column_Name);
		click.Webdriver_Click(hide_Column_Button_At_Header, true);

		By column_Header_Title_Ele = By.xpath(column_Header_Title.replace("$Column_Name", Column_Name));
		Assert.assertFalse(visible.Is_Displayed(column_Header_Title_Ele),
				"Some issue not able to click on 'Hide' option to hide the column : " + Column_Name);
		Util.Report_Log(Status.PASS,
				"Successfully clicked on 'Checkbox & : " + Column_Name + " column have been hide from the table.");

		Open_Arrange_Icon_Container();
		Util.Report_Log(Status.INFO, "Click on 'checkbox' to unhide the column :" + Arrange_Icon_Column_Name);
		By arrange_Icon_Checkbox_Ele = By
				.xpath(arrange_Icon_Checkbox.replace("$Column_Name", Arrange_Icon_Column_Name));

		click.Webdriver_Click(arrange_Icon_Checkbox_Ele, true);
		column_Header_Title_Ele = By.xpath(column_Header_Title.replace("$Column_Name", Column_Name));

		Assert.assertTrue(visible.Is_Displayed(column_Header_Title_Ele),
				"Some issue not able to click on 'checkbox' to unhide the column : " + Arrange_Icon_Column_Name);
		Util.Report_Log(Status.PASS, "Successfully clicked on 'Checkbox & : " + Arrange_Icon_Column_Name
				+ " column have been unhide from the table.");

	}

	public static List<String> Read_All_Data_From_People_Table(String Column_Name, String button_Name) {
		List<String> column_Values = null;
		By column_Name = By.xpath(table_Column_Cell_Value.replace("$Column_Name", Column_Name));

		column_Values = text.Get_Elements_Text(column_Name);
		String xpath = page_Handling_Buttons.replace("$Button_Name", button_Name);
		if (webelement.Is_Present(By.xpath(xpath), 30)) {
			while ((text.Webdriver_Get_Text_Of_Attribute(By.xpath(xpath), "aria-disabled")).equalsIgnoreCase("false")) {
				click.Webdriver_Javascipt_Click(By.xpath(xpath));
				column_Name = By.xpath(table_Column_Cell_Value.replace("$Column_Name", Column_Name));
				column_Values.addAll(text.Get_Elements_Text(column_Name));
			}
		}

		Util.Report_Log(Status.INFO, "All Data  " + column_Values);
		return column_Values;
	}

}
