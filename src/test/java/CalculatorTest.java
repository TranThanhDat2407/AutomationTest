import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.example.Calculator;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Set;

public class CalculatorTest {

    HSSFWorkbook workbook;
    HSSFSheet sheet;
    private LinkedHashMap<String, Object[]> TestNGResults;
    @BeforeClass
    public void setUp() {
        workbook = new HSSFWorkbook();
        sheet = workbook.createSheet("Test Result Summary");
        TestNGResults = new LinkedHashMap<String, Object[]>();
        TestNGResults.put("1", new Object[]{
                "Test Step No.",
                "Action",
                "Expected Output",
                "Actual Output",
        });
    }


    @AfterClass
    public void teardown() {
        // Tạo Workbook và Sheet nếu chưa tạo
        if (workbook == null) {
            workbook = new HSSFWorkbook();
            sheet = workbook.createSheet("TestNG Results");
        }

        Set<String> keyset = TestNGResults.keySet();
        int rownum = 0;
        for (String key : keyset) {
            Row row = sheet.createRow(rownum++);
            Object[] objArr = TestNGResults.get(key);
            int cellnum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellnum++);
                if (obj instanceof String) {
                    cell.setCellValue((String) obj);
                } else if (obj instanceof Date) {
                    cell.setCellValue((Date) obj);
                } else if (obj instanceof Double) {
                    cell.setCellValue((Double) obj);
                } else if (obj instanceof Boolean) {
                    cell.setCellValue((Boolean) obj);
                }
            }
        }

        try (FileOutputStream out = new FileOutputStream(new File("TestNGResults.xls"))) {
            workbook.write(out); // ghi workbook vào output stream
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (workbook != null) {
                    workbook.close(); // Đảm bảo rằng workbook được đóng
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testAdd() {
        try{
            Calculator calculator = new Calculator();
            Assert.assertEquals(2, calculator.add(1, 1)); // Kiểm tra 1 + 1 = 2

            TestNGResults.put("2",new Object[]{
                    1d, "Test Add 1 + 1", "2","pass"
            });

        } catch (Exception e){
            TestNGResults.put("2",new Object[]{
                    1d, "Test Add 1 + 1", "2","fail"
            });
        }
    }

    @Test
    public void testSubtract() {
        try{
            Calculator calculator = new Calculator();
            Assert.assertEquals(2, calculator.subtract(1, 1)); // Kiểm tra 1 + 1 = 2

            TestNGResults.put("3",new Object[]{
                    2d, "Test Substract 1 - 1", "0","pass"
            });

        } catch (Exception e){
            TestNGResults.put("3",new Object[]{
                    2d, "Test Subtract 1 - 1", "0","fail"
            });
        }
    }
}
