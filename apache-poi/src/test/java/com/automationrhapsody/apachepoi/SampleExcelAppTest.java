package com.automationrhapsody.apachepoi;

import java.io.File;

import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SampleExcelAppTest {

    private static final File FILE = new File("testReport.xlsx");

    private static XSSFWorkbook workbookUnderTest;

    @BeforeClass
    public static void beforeClass() throws Exception {
        if (FILE.exists()) {
            FILE.delete();
        }

        SampleExcelApp.main(null);

        workbookUnderTest = new XSSFWorkbook(FILE);
    }

    @Test
    public void testNumberOfSheets() {
        assertThat(workbookUnderTest.getNumberOfSheets(), is(2));
    }

    @Test
    public void testSheetName() {
        assertThat(workbookUnderTest.getSheetName(0), is("SheetName"));
        assertThat(workbookUnderTest.getSheetName(1), is("NewSheetName"));
    }

    @Test
    public void testFirstSheetContent() {
        XSSFSheet sheet = workbookUnderTest.getSheetAt(0);
        assertThat(sheet.getLastRowNum(), is(3));
        assertThat(sheet.getRow(3).getLastCellNum(), is((short) 3));
        assertThat(sheet.getRow(3).getCell(2).getStringCellValue(), is("C4"));

        assertThat(sheet.getRow(0).getCell(0).getCellStyle().getFillForegroundColor(),
            is(IndexedColors.BLUE.getIndex()));
        assertThat(sheet.getRow(1).getCell(1).getCellStyle().getFillForegroundColor(),
            is(IndexedColors.AUTOMATIC.getIndex()));
    }

    @Test
    public void testSecondSheetContent() {
        XSSFSheet sheet = workbookUnderTest.getSheetAt(1);
        assertThat(sheet.getLastRowNum(), is(1));
        assertThat(sheet.getRow(1).getLastCellNum(), is((short) 2));
        assertThat(sheet.getRow(1).getCell(1).getStringCellValue(), is("B2-red"));

        assertThat(sheet.getRow(1).getCell(1).getCellStyle().getFillForegroundColor(),
            is(IndexedColors.RED.getIndex()));
        assertThat(sheet.getRow(0).getCell(0).getCellStyle().getFillForegroundColor(),
            is(IndexedColors.AUTOMATIC.getIndex()));
    }
}
