package com.automationrhapsody.apachepoi;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriter {

    private final XSSFWorkbook workBook;

    private Map<String, Integer> nextRows = new HashMap<>();
    private String currentSheet;
    private boolean isSaved;

    public ExcelWriter() {
        workBook = new XSSFWorkbook();
    }

    public void writeAndClose(File excelFile) {
        if (isSaved) {
            throw new IllegalArgumentException("Workbook already saved. Make new instance to start over.");
        }
        try {
            workBook.write(new FileOutputStream(excelFile));
            workBook.close();
            isSaved = true;
        } catch (IOException ioe) {
            // TODO log
        }
    }

    public void switchToSheet(String sheetName) {
        currentSheet = sheetName;
        if (workBook.getSheet(sheetName) == null) {
            workBook.createSheet(currentSheet);
            nextRows.put(currentSheet, 0);
        }
    }

    public void writeRow(String... values) {
        XSSFSheet sheet = workBook.getSheet(currentSheet);
        int nextRow = nextRows.get(currentSheet);
        XSSFRow row = sheet.createRow(nextRow);

        for (int i = 0; i < values.length; i++) {
            XSSFCell cell = row.createCell(i);
            cell.setCellType(CellType.STRING);
            cell.setCellValue(values[i]);
        }

        nextRows.put(currentSheet, nextRow + 1);
    }

    public void setCellColour(int rowNumber, int cellNumber, IndexedColors colour) {
        XSSFCellStyle style = workBook.createCellStyle();
        style.setFillForegroundColor(colour.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        int nextRow = nextRows.get(currentSheet);
        if (rowNumber > nextRow) {
            // TODO log or exception?
            System.out.println("Requested row (" + rowNumber + ") is greater than all rows (" + nextRow + ") in "
                + currentSheet + " sheet. Writing to last row!");
            rowNumber = nextRow;
        }
        XSSFSheet sheet = workBook.getSheet(currentSheet);
        int lastCell = sheet.getRow(rowNumber - 1).getLastCellNum();
        if (cellNumber > lastCell) {
            // TODO log or exception?
            System.out.println("Requested cell (" + cellNumber + ") is greater than all rows (" + lastCell + ") in "
                + currentSheet + " sheet. Writing to last cell!");
            cellNumber = lastCell;
        }

        sheet.getRow(rowNumber - 1).getCell(cellNumber - 1).setCellStyle(style);
    }
}
