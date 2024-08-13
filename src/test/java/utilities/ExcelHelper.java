package utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelHelper {

    public  FileInputStream fileInputStream;
    public  FileOutputStream fileOutputStream;
    public  XSSFWorkbook workbook;
    public  XSSFSheet sheet;
    public  XSSFRow row;
    public  XSSFCell cell;
    public  String path;

    public ExcelHelper(String path){
        this.path= path;
    }

    public int getRowCount(String excelSheet){
        try {
            fileInputStream= new FileInputStream(path);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            int rowCount= sheet.getLastRowNum();
            workbook.close();
            fileInputStream.close();
            return rowCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCellCount(String excelSheet, int rowNum){
        try {
            fileInputStream= new FileInputStream(path);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            int cellCount= row.getLastCellNum();
            workbook.close();
            fileInputStream.close();
            return cellCount;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCellData(String excelSheet, int rowNum, int colNum){
        try {
            fileInputStream= new FileInputStream(path);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            cell= row.getCell(colNum);
            String data;
            try {
                DataFormatter formatter= new DataFormatter();
                String cellData= formatter.formatCellValue(cell);
                return cellData;
            }catch (Exception e){
                data= "";
            }
            workbook.close();
            fileInputStream.close();
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setCellData(String excelSheet, int rowNum, int colNum, String data){
        try {
            fileInputStream= new FileInputStream(path);
            workbook= new XSSFWorkbook(fileInputStream);
            sheet= workbook.getSheet(excelSheet);
            row= sheet.getRow(rowNum);
            cell= row.createCell(colNum);
            cell.setCellValue(data);
            fileOutputStream= new FileOutputStream(path);
            workbook.close();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
