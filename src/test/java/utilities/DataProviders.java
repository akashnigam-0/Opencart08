package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "loginData")
    public Object[][] getData() throws IOException {
        String path = ".\\testData\\OpenCart_loginData.xlsx";
        
        // Create an instance of ExcelUtilities and select the sheet
        ExcelUtilities xl = new ExcelUtilities(path);
        xl.selectSheet("Sheet1");

        int totalRows = xl.getRowCount();
        int totalCols = xl.getColumnCount(0);

        // Create a 2D array to hold the data
        Object[][] loginData = new Object[totalRows - 1][totalCols];

        // Loop through the rows and columns to fetch data
        for (int i = 1; i < totalRows; i++) {
            for (int j = 0; j < totalCols; j++) {
                loginData[i - 1][j] = xl.getCellData(i, j);
            }
        }

        xl.close(); // Close the Excel file
        return loginData;
    }
}
