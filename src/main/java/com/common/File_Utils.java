package com.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class File_Utils implements Init {

	public static boolean isValidFile(File file) {
		return (file != null && file.exists() && file.isFile());
	}

	public static String Folder_Creation(String Path) {
		File dir = new File(Path);
		if (!dir.exists()) {
			if (dir.mkdirs()) {
				Util.Report_Log(Status.INFO, "Directory is created!  :" + Path);
			} else {
				Util.Report_Log(Status.INFO, "Failed to create directory!");
			}
		} else {
			Util.Report_Log(Status.INFO, "Directory Exists!");
		}
		return dir.getAbsolutePath();
	}

	public static String Search_Filename(String folder_Path, String filepattern, String extentsion) {
		String fileName = null;
		String file_Name = null;
		File dir = new File(folder_Path);
		File[] files = dir.listFiles();
		for (File f : files) {
			fileName = f.getName();
			if ((fileName.toLowerCase().contains(filepattern.toLowerCase()))
					&& (fileName.toLowerCase().contains(extentsion.toLowerCase()))) {
				file_Name = f.getName();
				break;
			}
		}
		Assert.assertTrue(file_Name != null, "Unable to find file with pattern - " + filepattern);
		return file_Name;
	}

	public static void Delete_file(String path) {
		try {

			File file = new File(path);

			if (file.delete()) {
				Util.Report_Log(Status.INFO, file.getName() + " is deleted!");
			} else {
				Util.Report_Log(Status.INFO, "Delete operation is failed.");
			}

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	public static String Get_File_Type_Extension(String file_Name) {

		if (file_Name.isEmpty()) {
			Util.Report_Log(Status.INFO, "File_Name is Null");
			return null;
		} else {
			return file_Name.substring(file_Name.lastIndexOf("."));
		}
	}

	public static String Wait_For_File_To_Download(String downloadDirPath, final String ext, String name)
			throws Exception {
		Util.Report_Log(Status.INFO, "downloading file in dir : " + downloadDirPath);
		boolean flag = false;
		File dir = new File(downloadDirPath);
		File[] files = null;
		String nameFromFile = null;
		int count = 0;
		while (true) {
			if (count <= 30) {
				Thread.sleep(5000);
				count++;
			} else {
				throw new Exception("timeout for file to download");
			}
			files = dir.listFiles(new FilenameFilter() {
				// @Override
				@Override
				public boolean accept(File dir, String name) {
					if (name.lastIndexOf('.') > 0) {
						// get last index for '.' char
						int lastIndex = name.lastIndexOf('.');
						// get extension
						String str = name.substring(lastIndex);
						// match path name extension
						if (str.equals(ext)) {
							return true;
						}
					}
					return false;
				}
			});
			if ((files != null) && (files.length > 0)) {
				for (int i = 0; i < files.length; i++) {
					String strWithPath = files[i].toString();

					Util.Report_Log(Status.INFO, "Full Name with path is:" + strWithPath);
					nameFromFile = files[i].getName();
					// int lastIndex = strWithPath.lastIndexOf('/');
					// nameFromFile =
					// strWithPath.substring(lastIndex).substring(1).replaceAll("\\s+", "");

					Util.Report_Log(Status.INFO, "Name is from File:" + nameFromFile);
					Util.Report_Log(Status.INFO, "Expected Name is:" + name);
					// String NAme = "\\" + name;

					if (name.substring(0, 9).equalsIgnoreCase("Annotated")) {
						if (name.equalsIgnoreCase(nameFromFile) || name.contains(nameFromFile)
								|| name.contains(nameFromFile)
								|| name.substring(9, name.length()).trim().equalsIgnoreCase(nameFromFile)) {
							Util.Report_Log(Status.INFO, "Found Downloaded File");
							flag = true;
							break;
						}
					} else {
						if (nameFromFile.contains(name)) {
							Util.Report_Log(Status.INFO, "Found Downloaded File");
							flag = true;
							break;
						} else {
							continue;
						}
					}

				}

			}
			if (flag)
				break;
		}
		return nameFromFile;
	}

	public static void FileWriter(String filepath, String response, String filename) throws Exception {
		File_Utils.Folder_Creation(filepath);
		FileWriter fw = new FileWriter(new File(filepath + "/" + filename));
		fw.write(response);
		fw.close();
	}

	public static void Validate_Content_In_File(String[] text_To_Search, String file_Path) throws IOException {
		File file = new File(file_Path);
		Util.Report_Log(Status.INFO, "Validating text precence in file - " + file_Path);
		boolean flag = false;
		String text_Not_Found = "";
		for (String str : text_To_Search) {
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.contains(str)) {
					flag = true;
					break;
				}
			}
			br.close();
			if (!flag)
				text_Not_Found += str;
		}
		Assert.assertTrue(text_Not_Found.isEmpty(), "The following text not found - " + text_Not_Found);
		Util.Report_Log(Status.PASS, "Text List is present in file ");

	}

	public static ArrayList<String> Column_list_From_Csv(String filepath) throws Exception {
		String line = "";
		ArrayList<String> col_list = new ArrayList<>();
		BufferedReader br = new BufferedReader(new FileReader(filepath));
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			for (int i = 0; i < line.split(",").length; i++) {
				col_list.add(line.split(",")[i]);
			}
			break;
		}
		return col_list;
	}

	public static ArrayList<String> Record_list_From_Csv(String filepath) throws Exception {
		ArrayList<String> csv_Record = new ArrayList<String>();
		Path pathToFile = Paths.get(filepath);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line = br.readLine();
			while (line != null) {
				line = br.readLine();
				csv_Record.add(line);
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return csv_Record;
	}

	public static String Read_File_As_String(String filePath) throws IOException {
		String file_data = new String(Files.readAllBytes(Paths.get(filePath)));
		return file_data;
	}

}
