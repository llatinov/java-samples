package com.automationrhapsody.apachepoi;

import java.io.File;

import org.apache.poi.ss.usermodel.IndexedColors;

public class SampleExcelApp {

    public static void main(String[] args) {
        String sheetName = "SheetName";

        ExcelWriter excelWriter = new ExcelWriter();
        excelWriter.switchToSheet(sheetName);
        excelWriter.writeRow("A1-blue", "B1", "C1");
        excelWriter.writeRow("A2", "B2", "C2");
        excelWriter.setCellColour(1, 1, IndexedColors.BLUE);

        excelWriter.switchToSheet("NewSheetName");
        excelWriter.writeRow("A1", "B1");
        excelWriter.writeRow("A2", "B2-red");
        excelWriter.setCellColour(2, 2, IndexedColors.RED);

        excelWriter.switchToSheet(sheetName);
        excelWriter.writeRow("A3", "B3", "C3");
        excelWriter.writeRow("A4", "B4", "C4");

        File excelFile = new File("testReport.xlsx");
        excelWriter.writeAndClose(excelFile);
    }
}
