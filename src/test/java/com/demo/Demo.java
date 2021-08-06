package com.demo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Demo {

	public static void main(String[] args) throws IOException {
//		Demo 1
/*		File source = new File("en.properties");
		File dest = new File("message.properties");
		copyFileUsingApacheCommonsIO(source, dest);

//		Demo 2
		ArrayList<String> users = TestData.getColumnData("E:\\STL\\STL\\ReadDataFromExcel\\MTN_All.xlsx", "Registration", "Username");
		System.out.println(users);

//		Demo 3
		System.out.println(arabicToDecimal("\u0660\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669"));
		System.out.println("\u0660\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669");
		System.out.println("\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9");

//		Demo 4
		// 1st example
        String str = "dcef";
        if (check(str.toCharArray()) == true)
            System.out.println("Yes");
        else
            System.out.println("No");
 
        // 2nd example
        String str1 = "xyza";
 
        if (check(str1.toCharArray()) == true)
            System.out.println("Yes");
        else
            System.out.println("No");

		System.out.println(StringUtils.containsIgnoreCase("headless_chrome", "edge"));

//		Demo 5
		excelToHashMap(".\\ReadDataFromExcel\\MTN.xlsx", "Plan");
*/
//		Demo6
/*		String[] col = {"PlanType", "PlanName", "MobilityType", "ICCID"};
		ArrayList<String> planDetails = TestData.getColumnData1(".\\ReadDataFromExcel\\MTN.xlsx", "Plan",col).get(0);
		System.out.println(planDetails.get(0));
		System.out.println(planDetails.get(1));
		System.out.println(planDetails.get(2));
		System.out.println(planDetails.get(3));
*/
//		Demo 7
/*		Map<String, String> data = getMapData("ReadDataFromExcel/MTN.xlsx", "Plan");
		for(Entry<String, String> map : data.entrySet()) {
			System.out.println(map.getKey()+" "+map.getValue());
		}
*/
//		Demo 8
//		System.out.println(TestData.getColumnData("ReadDataFromExcel/MTN - Copy.xlsx", "Plan", "TC_01"));
//		Demo 9
		deleteDirectory(new File(System.getProperty("user.dir") +"\\images"));
    }

	static void deleteDirectory(File directoryToBeDeleted) {
	    File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    directoryToBeDeleted.delete();
	}
	private static void copyFileUsingApacheCommonsIO(File source, File dest) throws IOException {
	    FileUtils.copyFile(source, dest);
	}

	@SuppressWarnings("unused")
	private static final String extendedArabic = "\u06f0\u06f1\u06f2\u06f3\u06f4\u06f5\u06f6\u06f7\u06f8\u06f9";

	//used in Arabic apps
	@SuppressWarnings("unused")
	private static final String arabic = "\u0660\u0661\u0662\u0663\u0664\u0665\u0666\u0667\u0668\u0669";

	private static String arabicToDecimal(String number) {
	    char[] chars = new char[number.length()];
	    for(int i=0;i<number.length();i++) {
	        char ch = number.charAt(i);
	        if (ch >= 0x0660 && ch <= 0x0669)
	           ch -= 0x0660 - '0';
	        else if (ch >= 0x06f0 && ch <= 0x06F9)
	           ch -= 0x06f0 - '0';
	        chars[i] = ch;
	    }
	    return new String(chars);
	}

	static boolean check(char s[])
    {
 
        // Get the length of the string
        int l = s.length;
 
        // sort the given string
        Arrays.sort(s);
 
        // Iterate for every index and
        // check for the condition
        for (int i = 1; i < l; i++) {
 
            // If are not consecutive
            if (s[i] - s[i - 1] != 1)
                return false;
        }
 
        return true;
    }

	public static boolean isValid() {
		//String password = "C!0ud"+Common.generateRandomNumber(7);
		String password = "C!0ud"+"1234567";

		// for checking if password length
		// is between 8 and 15
		if (!((password.length() >= 12) && (password.length() <= 15))) {
			return false;
		}

		// to check space
		if (password.contains(" ")) {
			return false;
		}
		if (true) {
			int count = 0;

			// check digits from 0 to 9
			for (int i = 0; i <= 9; i++) {

				// to convert int to string
				String str1 = Integer.toString(i);

				if (password.contains(str1)) {
					count = 1;
				}
			}
			if (count == 0) {
				return false;
			}
		}

		// for special characters
		if (!(password.contains("@") || password.contains("#")
				|| password.contains("!") || password.contains("~")
				|| password.contains("$") || password.contains("%")
				|| password.contains("^") || password.contains("&")
				|| password.contains("*") || password.contains("(")
				|| password.contains(")") || password.contains("-")
				|| password.contains("+") || password.contains("/")
				|| password.contains(":") || password.contains(".")
				|| password.contains(", ") || password.contains("<")
				|| password.contains(">") || password.contains("?")
				|| password.contains("|"))) {
			return false;
		}

		if (true) {
			int count = 0;

			// checking capital letters
			for (int i = 65; i <= 90; i++) {

				// type casting
				char c = (char)i;

				String str1 = Character.toString(c);
				if (password.contains(str1)) {
					count = 1;
				}
			}
			if (count == 0) {
				return false;
			}
		}

		if (true) {
			int count = 0;

			// checking small letters
			for (int i = 90; i <= 122; i++) {

				// type casting
				char c = (char)i;
				String str1 = Character.toString(c);

				if (password.contains(str1)) {
					count = 1;
				}
			}
			if (count == 0) {
				return false;
			}
		}

		// if all conditions fails
		return true;
	}

	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;

	public static void excelToHashMap(String fileName, String sheet) {
		try {
			FileInputStream ExcelFile = new FileInputStream(fileName);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheet);

			int rows = ExcelWSheet.getLastRowNum();

			HashMap<String, String> data = new HashMap<String, String>();

			for(int r=0; r<rows; r++) {
				String key = ExcelWSheet.getRow(r).getCell(0).getStringCellValue();
				String value = ExcelWSheet.getRow(r).getCell(1).getStringCellValue();
				data.put(key, value);
			}

			for(Map.Entry entry:data.entrySet()) {
				System.out.println(entry.getKey()+" "+entry.getValue());
			}
		} catch(Exception e) {
		}
	}

	
	public static Map<String, String> getMapData(String fileName, String sheet) throws IOException {
		Map<String, String> data = new HashMap<String, String>();
		try {
			FileInputStream fis = new FileInputStream(fileName);
			ExcelWBook = new XSSFWorkbook(fis);
			ExcelWSheet = ExcelWBook.getSheet(sheet);

			int rows = ExcelWSheet.getLastRowNum();
			for(int r=0; r<rows; r++) {
				Row row = ExcelWSheet.getRow(r);
				Cell keyCell = row.getCell(0);
				String key = keyCell.getStringCellValue().trim();

				Cell valueCell = row.getCell(1);
				String value = valueCell.getStringCellValue().trim();

				data.put(key, value);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return data;
	}
}