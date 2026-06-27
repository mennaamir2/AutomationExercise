package com.automationexercise.utils.dataReader;
import com.automationexercise.utils.logs.LogsManager;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    private static final String TEST_DATA_PATH = "src/test/resources/test-data/";
    public static String getExcelData(String excelFilename, String sheetName, int rowNum, int colNum) {
        XSSFWorkbook workBook;
        XSSFSheet sheet;

        String cellData;
        try {
            workBook = new XSSFWorkbook(TEST_DATA_PATH + excelFilename);
            sheet = workBook.getSheet(sheetName);
            cellData = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
            return cellData;
        } catch (Exception e) {
            LogsManager.error("Error reading excel file:", excelFilename, e.getMessage());
            return "";
        }

    }
}
