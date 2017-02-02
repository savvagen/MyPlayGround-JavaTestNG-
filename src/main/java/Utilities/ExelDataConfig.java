package Utilities;


import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExelDataConfig {


    public static XSSFWorkbook wb;
    public static XSSFSheet sheet;

    public ExelDataConfig(String exelPath) throws IOException {
        try {
            File scr = new File(exelPath);
            FileInputStream fis = new FileInputStream(scr);
            wb = new XSSFWorkbook(fis);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getData(int sheetNumber, int row, int column){
        sheet=wb.getSheetAt(sheetNumber);
        String data = sheet.getRow(row).getCell(column).getStringCellValue();
        return data;
    }

    public int getRowCount(int sheetIndex){
        int row = wb.getSheetAt(sheetIndex).getLastRowNum();
        row = row+1;
        return row;
    }

}
