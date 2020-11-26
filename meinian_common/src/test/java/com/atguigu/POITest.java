package com.atguigu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @Author: oono
 * @Date: 2020/11/25
 * @Description:
 */
public class POITest {

    @Test
    public void testWrite() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        XSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("姓名");
        row.createCell(1).setCellValue("性别");
        row.createCell(2).setCellValue("邮箱");
        row.createCell(3).setCellValue("地址");

        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("庄熙");
        row1.createCell(1).setCellValue("男");
        row1.createCell(2).setCellValue("112@163.com");
        row1.createCell(3).setCellValue("揭阳");

        row1.createCell(0).setCellValue("庄熙2");
        row1.createCell(1).setCellValue("男");
        row1.createCell(2).setCellValue("11211@163.com");
        row1.createCell(3).setCellValue("揭阳");

        FileOutputStream os = new FileOutputStream("C:\\Users\\oono\\Desktop\\test1.xlsx");
        workbook.write(os);
        os.close();
    }

    @Test
    public void testPOIRead1() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\oono\\Desktop\\test.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);
        for (Row cells : sheet) {
            for (Cell cell : cells) {
                System.out.println(cell.getStringCellValue());
            }
        }
    }

    @Test
    public void testPOIRead() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\oono\\Desktop\\test.xlsx");
        XSSFSheet sheet = workbook.getSheetAt(0);//index=0，表示sheet1
        //终止行号正常，所以用小于等于
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            XSSFRow row = sheet.getRow(i);
            //终止列号多一个，所以用小于
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                XSSFCell cell = row.getCell(j);
                //读取cell单元格中的内容
                String value = cell.getStringCellValue();
                System.out.print(value + "\t\t");
            }
            System.out.println();
        }
        workbook.close();
    }

}
