package utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtilities {

    private String filePath;
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to initialize with file path
    public ExcelUtilities(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(fileInputStream); // Open the Excel workbook
        fileInputStream.close();
    }

    // Method to select the sheet by name
    public void selectSheet(String sheetName) {
        sheet = workbook.getSheet(sheetName);
    }

    // Method to select the sheet by index
    public void selectSheet(int sheetIndex) {
        sheet = workbook.getSheetAt(sheetIndex);
    }

    // Method to get data from a specific cell (0-based index)
    public String getCellData(int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            return "";
        }
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            return "";
        }
        return getCellValueAsString(cell);
    }

    // Method to write data into a specific cell (0-based index)
    public void setCellData(int rowIndex, int colIndex, String data) throws IOException {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        cell.setCellValue(data);
        writeFile();
    }

    // Method to get the total number of rows in the selected sheet
    public int getRowCount() {
        return sheet.getLastRowNum() + 1;
    }

    // Method to get the total number of columns in a specific row
    public int getColumnCount(int rowIndex) {
        Row row = sheet.getRow(rowIndex);
        if (row != null) {
            return row.getLastCellNum();
        }
        return 0;
    }

    // Method to close the workbook (important to release resources)
    public void close() throws IOException {
        if (workbook != null) {
            workbook.close();
        }
    }

    // Helper method to convert a cell's value to a String
    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }

        CellType cellType = cell.getCellType();
        switch (cellType) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    // Helper method to write changes back to the Excel file
    private void writeFile() throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            workbook.write(fileOut);
        }
    }
}
