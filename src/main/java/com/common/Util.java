package com.common;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.webdriverutilities.Set_Webdriver;



public class Util extends Annotation implements Init {

	public static Logger log = Logger.getLogger(Util.class);
	static WebDriver driver = Set_Webdriver.DRIVER.Get_Driver();
	private static SecureRandom random = new SecureRandom();

	public static void Take_Screenshot(String file_Name) throws IOException {
		File scr_File = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scr_File, new File(Constants.zip_Folder + File.separator + file_Name + ".png"));
		File a = new File(Constants.zip_Folder + File.separator + file_Name);
		log.info("ScreenShot Path : " + a.getAbsolutePath());
		File b = new File(file_Name);
		FileUtils.copyFile(scr_File, b);
		log.info("ScreenShot Path attached in report: " + b.getAbsolutePath());

	}

	public static String Get_Random_String(int length) {

		String result = new BigInteger(Long.SIZE * length, random).toString(32);
		return result.substring(0, length);
	}
	public static String Get_Random_Number(int length)
	{
		String result = String.valueOf(new BigInteger(Long.SIZE * length, random));
		return result.substring(0, length);
	}
	public static String Get_Random_Special_Characters(int length)
	{
		final String alphabet = "<([{^-=$!|]})?*+>&%#@~`,_:;'";
		final int N = alphabet.length();
		Random r = new Random();
		StringBuilder s = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			s.append(alphabet.charAt(r.nextInt(N)));
		}
		return String.valueOf(s);
	}


	public static String Get_Current_Frame() {
		JavascriptExecutor js_Executor = (JavascriptExecutor) driver;
		String currentFrame = (String) js_Executor.executeScript("return self.name");
		return currentFrame;
	}

	public static void Report_Log(Status status, String message) {
		String str = Thread.currentThread().getStackTrace()[2].getClassName();
		str = str.substring(str.lastIndexOf(".") + 1);
		String class_Info = "(" + str + ".Java:" + Thread.currentThread().getStackTrace()[2].getLineNumber() + ") ";
		message = class_Info + message;
		Report.Get_Logger().log(status, message);
		log.info(message);
	}

	public static int get_Number_Of_Tabs() {
		ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
		return tabs.size();
	}

	
	public static String Get_Current_Date_In_String_Format() {
		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat("MM/dd/yyyy");
		DateFor = new SimpleDateFormat("MMM dd,yyyy h:mm");
		String currentDate = DateFor.format(date);
		System.out.println("Formatted Date: " + currentDate);
		return currentDate;
	}

	
	public static long Get_Current_Date_In_Milli_Seconds() {
		Date date = new Date();
		long timeMilli = date.getTime();
		System.out.println("Time in milliseconds : " + timeMilli);
		return timeMilli;

	}

	public static String Get_Time_In_CST(String format, int min) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date date = new Date(System.currentTimeMillis() + min * 60 * 1000);
		sdf.setTimeZone(TimeZone.getTimeZone("CST"));
		return sdf.format(date);
	}

	public static String Convert_String_Date_Format(String date_String, String string_Old_Format,
													String string_New_Format) throws ParseException {
		final String OLD_FORMAT = string_Old_Format;
		final String NEW_FORMAT = string_New_Format;
		String oldDateString = date_String;
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
		Date d = sdf.parse(oldDateString);
		sdf.applyPattern(NEW_FORMAT);
		newDateString = sdf.format(d);
		return newDateString;
	}

	public static String Convert_Given_Time_In_Given_Format(String tag_Value,String format) {
		LocalTime date = LocalTime.parse(tag_Value);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
		String text = date.format(formatter);
		System.out.println(text);
		return text;
	}
}
