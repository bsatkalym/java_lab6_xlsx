package sbs13_lab6_xlsx;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SBS13_LAB6_XLSX {

    public static void main(String[] args) {
        try {
            String xlsxFileName = new File(".").getAbsoluteFile().getParentFile().getAbsolutePath()
                    + System.getProperty("file.separator") + "test.xlsx";
//            writeXLSXFile(xlsxFileName);
            modifyXLSXFile(xlsxFileName);
            readXLSXFile(xlsxFileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeXLSXFile(String xlsxFileName) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsxFileName));
        XSSFSheet sheet = wb.getSheetAt(0);
        for (int r = 0; r < 5; r++) {
            XSSFRow row = sheet.createRow(r);
            for (int c = 0; c < 5; c++) {
                row.createCell(c);
            }
        }

        FileOutputStream fos = new FileOutputStream(xlsxFileName);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    // Модификация данных в XLSX-файле
    public static void modifyXLSXFile(String xlsxFileName) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(xlsxFileName));
        XSSFSheet sheet = wb.getSheetAt(0);
        sheet.getRow(0).getCell(0).setCellValue("Пример");
        sheet.getRow(1).getCell(1).setCellValue("работы");
        sheet.getRow(2).getCell(2).setCellValue("с XLSX");
        sheet.getRow(3).getCell(3).setCellValue("через");
        sheet.getRow(4).getCell(4).setCellValue("Apache poi");

        FileOutputStream fos = new FileOutputStream(xlsxFileName);
        wb.write(fos);
        fos.flush();
        fos.close();
    }

    // Чтение данных из XLSX-файла
    public static void readXLSXFile(String xlsxFileName) throws IOException {
        InputStream ExcelFileToRead = new FileInputStream(xlsxFileName);
        XSSFSheet sheet = new XSSFWorkbook(ExcelFileToRead).getSheetAt(0);
        XSSFRow row;
        XSSFCell cell;
        // Считывание текстовых и цифровых данных из файла
        Iterator rows = sheet.rowIterator();
        while (rows.hasNext()) {
            row = (XSSFRow) rows.next();
            Iterator cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = (XSSFCell) cells.next();
                if (cell.getCellType() == CellType.STRING) {
                    System.out.print(cell.getStringCellValue() + " ");
                } else if (cell.getCellType() == CellType.NUMERIC) {
                    System.out.print(cell.getNumericCellValue() + " ");
                } else {
                }
            }System.out.println();
        }
        
    }
}
