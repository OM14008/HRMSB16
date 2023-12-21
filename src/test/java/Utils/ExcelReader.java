package Utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class ExcelReader {
    public static List<Map<String, String>> read(String sheetName, String path) {
        FileInputStream fileInputStream = null;
        List<Map<String,String>> excelData = new ArrayList<>();
        try {
            fileInputStream = new FileInputStream(path);
            // that special call which knows how to read the data from excel files
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            Sheet sheet = xssfWorkbook.getSheet(sheetName);
            //this row is just for keys
            Row headerRow = sheet.getRow(0);
            //for row, we take 1 index because 0 is already used for headers
            for (int rows = 1; rows < sheet.getPhysicalNumberOfRows(); rows++) {
                //this row is for values
                Row row = sheet.getRow(rows);
                Map<String, String> rowMap = new LinkedHashMap<>();
                //here, we are taking all the columns
                for (int col = 0; col < row.getPhysicalNumberOfCells(); col++) {
                    String key = headerRow.getCell(col).toString();
                    String value = row.getCell(col).toString();
                    rowMap.put(key, value);
                }
                excelData.add(rowMap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return excelData;
    }
    //excel reader method but with different return type
    public static List<Map<Integer, Integer>> readExcel(String filePath) throws IOException {
        List<Map<Integer, Integer>> resultList = new ArrayList<>();

        FileInputStream fileInputStream = new FileInputStream(filePath);
        Workbook workbook = new XSSFWorkbook(fileInputStream);

        Sheet sheet = workbook.getSheetAt(0); // Assuming data is present in the first sheet

        for (Row row : sheet) {
            Map<Integer, Integer> map = new HashMap<>();

            for (int i = 0; i < 7; i++) {
                Cell cell = row.getCell(i);

                if (cell != null && cell.getCellType() == CellType.NUMERIC) {
                    int value = (int) cell.getNumericCellValue();
                    map.put(i, value);
                }
            }

            if (!map.isEmpty()) {
                resultList.add(map);
            }
        }

        workbook.close();
        fileInputStream.close();

        return resultList;
    }
}

