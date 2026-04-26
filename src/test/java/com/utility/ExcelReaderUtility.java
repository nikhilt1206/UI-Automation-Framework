package com.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.ui.pojo.User;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {
    public static Iterator<User> readExcelFile(String filename){

        File xlsxFile = new File(System.getProperty("user.dir")+"/testData/"+filename);
        List<User> userList = new ArrayList<>();
        DataFormatter formatter = new DataFormatter();
        try (XSSFWorkbook workbook = new XSSFWorkbook(xlsxFile)){
            XSSFSheet sheet= workbook.getSheet("LoginTestData");
            if (sheet == null) {
                throw new RuntimeException("Sheet 'LoginTestData' not found");
            }
            Iterator<Row> rowIterator = sheet.iterator();
            if(rowIterator.hasNext()){
                rowIterator.next();
            }
            while(rowIterator.hasNext()){
                Row row = rowIterator.next();
                if(row==null || row.getCell(0)==null) continue;
                String emailAddress= formatter.formatCellValue(row.getCell(0));
                String password = formatter.formatCellValue(row.getCell(1));
                if (emailAddress.equalsIgnoreCase("emailAddress")) continue;
                if (emailAddress.isBlank() || password.isBlank()) continue;
                userList.add(new User(emailAddress, password));
            }
        } catch (IOException | InvalidFormatException e) {
            throw new RuntimeException("Error reading Excel file",e);
        }
        return userList.iterator();
    }
}
