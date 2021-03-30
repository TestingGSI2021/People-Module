package com.common;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class Constants {

	public static final String local_Browser_Name = "chrome";
	public static final String local_DB_Storage = "false";
	public static final String local_Screen_Resolution = "maximize";
	public static String random_Number = "d";
	public static String random_Number_Integer = "";
	public static String random_Number_Special_Character = "";
	public static String suite_Name = "Suite_Name";
	public static String zip_Folder;
	public static String url="https://xpmgr.gevme.com/login";

	public static final String browser_Download_Folder = "OutputData/downloadFiles";

	public static final String browser_Name = (System.getProperty("BROWSER_TO_USE") != null)
			? System.getProperty("BROWSER_TO_USE").toString().toLowerCase().trim()
			: local_Browser_Name;
	public static final int expicit_Wait_Time = 60;
	public static final int explicit_Wait_For_Short_Time = 10;
	public static String screen_Resolution = (System.getProperty("SCREEN_RESOLUTION") != null)
			? System.getProperty("SCREEN_RESOLUTION").toString().toLowerCase().trim()
			: local_Screen_Resolution;

	public static final String outputdata_Path = "OutputData" + File.separator;
	public static final String extent_Report_Path = outputdata_Path + "ExtentReport.html";
	public static final String extent_Report_Screenshot_Path = outputdata_Path + "Screenshots";
//	public final static String user_Dir_Path = (System.getenv("BUILD_URL") != null)
//			? System.getenv("BUILD_URL") + "artifact" + File.separator + "Automation_Project" + File.separator
//			: System.getProperty("user.dir") + File.separator;
	public final static String user_Dir_Path =  System.getProperty("user.dir") + File.separator;
	public static HashMap<String, String> header_Map = new HashMap<String, String>();
	public static HashMap<String, String> form_Map = new HashMap<String, String>();
	public static Map<String, Object> api_Response;
	public static String email = "testinggsi1@gmail.com";
	public static String password = "GSI@2021";
	public static String mailFolderName = "INBOX";
	public static String emailSubjectContent = "OTP for Your Leads Generation Apps";
	public static String emailContent = "Your One-Time Password (OTP) is:</p><p style=\"font-weight: bold; font-size: 40px; margin-top: 24px; padding: 16px 0px 16px 24px; letter-spacing: 24px; display: inline-block; background: #e8e8e8; color: #444444; border-radius: 4px;\">";
	public static int lengthOfOTP = 4;
	
	public static int totalRecordsBefore=0;
	public static int totalRecordsAfter=0;
	public static String downloadFileName = "601b7be559e138003d9a0e96-GEVEME-Live.csv";

}

