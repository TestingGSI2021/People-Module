package com.common;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.common.ui.Help_Page;
import com.webdriverutilities.Set_Webdriver;


public class Annotation {

	private final Logger log = Logger.getLogger(Annotation.class);
	static ExtentTest test;
	public static ExtentReports extent;
	static ITestContext testContext;
	static public SoftAssert soft_assert = new SoftAssert();
	
	public WebDriver driver = Set_Webdriver.DRIVER.Get_Driver();

	@BeforeSuite(alwaysRun = true)
	public void Before_Suites(ITestContext test_Context) {
		log.info(
				"\n\n========================================================================================================================================================\n<<<<< TEST SUITE Started : "
						+ Constants.suite_Name
						+ " >>>>>\n========================================================================================================================================================\n");
		Constants.random_Number = Util.Get_Random_String(5) ;
		Constants.random_Number = Util.Get_Random_String(5);
		Constants.random_Number_Integer = Util.Get_Random_Number(5);
		Constants.random_Number_Special_Character =Util.Get_Random_Special_Characters(5);
		if((new File(Constants.outputdata_Path)).exists())
		{
			try {
				log.info("Cleaning the OutputData folder.");
				FileUtils.deleteDirectory(new File(Constants.outputdata_Path));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		log.info("Creating Output Folder");
		File dir = new File(Constants.outputdata_Path);
		dir.mkdirs();	
		log.info("Output folder created");
	}
	@BeforeMethod(alwaysRun = true)
	public void Before_Method(Method m) {
		Report.Create_Group(m.getName());
      
		
	}
	

	@AfterTest(alwaysRun = true)
	public void After_Test(ITestContext test_Context) {
		 
		log.info(
				"\n\n-------------------------------------------------------------------------------------------------------------------------- \n TEST CASE ENDED : \""
						+ test_Context.getName()
						+ "\" \n-------------------------------------------------------------------------------------------------------------------------- \n");
	}



	@BeforeTest(alwaysRun = true)
	@Parameters({"username","password"})
	public void Before_Test_1(ITestContext test_Context,@Optional String username,@Optional String password) {
		log.info(
				"\n\n-------------------------------------------------------------------------------------------------------------------------- \n TEST CASE STARTED : \""
						+ test_Context.getName()
						+ "\" \n-------------------------------------------------------------------------------------------------------------------------- \n");
	
		Report.Create_Test(test_Context.getCurrentXmlTest().getName());
		
		if(username!=null && password!=null) {
			log.info("Updating  User Credentials");
			Constants.email=username;
			Constants.password=password;
			log.info("Username updated to : "+Constants.email);
			log.info("Password updated to : "+Constants.password);
			log.info(" Credentials Updated Successfully");
		}           
	}

	@AfterMethod(alwaysRun = true)
	public void After_Method(ITestResult test_Result, Method method) throws Exception {
		if (!test_Result.isSuccess()) {
			Report.Take_Screenshot(driver, Report.Get_Logger(), method.getName());
			Throwable th = test_Result.getThrowable();
			if (th != null) {
				Util.Report_Log(Status.FAIL,
						th.getMessage() + "\n" + printStacktrace(th.getStackTrace(), method.getName()));
				test_Result.setThrowable(null);
			}
		}
	}

	@AfterSuite(alwaysRun = true)
	public void After_Suites() {
		Report.Get_Instance().flush();
		log.info(
				"\n\n========================================================================================================================================================\n<<<<< TEST SUITE ENDED : "
						+ Constants.suite_Name
						+ " >>>>>\n========================================================================================================================================================\n");
		log.info("Saving data into Environment Propertie File");
		driver.quit();
		
	}

	private static String printStacktrace(StackTraceElement stack[], String methodName) {
		String stackLog = "";
		for (StackTraceElement stk : stack) {
			stackLog = stackLog + stk.toString() + "<br />";
		}
		return stackLog;
	}


}
