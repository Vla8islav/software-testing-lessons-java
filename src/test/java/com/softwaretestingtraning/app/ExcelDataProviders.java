package com.softwaretestingtraning.app;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.testng.annotations.DataProvider;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ExcelDataProviders {

    @DataProvider
    public static Object[][] excelDataProvider(Method m) throws IOException {
        if (m.isAnnotationPresent(ExcelDataSource.class)) {
            int length = m.getParameterTypes().length;
            ExcelDataSource dataSource = m.getAnnotation(ExcelDataSource.class);
            List<Object[]> result = new ArrayList<>();
            HSSFWorkbook workbook = new HSSFWorkbook(ExcelDataProviders.class.getResourceAsStream("/" + dataSource.value()));
            HSSFSheet sheet = workbook.getSheetAt(0);

            for (Row row : sheet) {
                Object[] parameters = new Object[length];
                for ( int i = 0; i < length; i++) {
                    String currentCellValue = row.getCell(i).getStringCellValue();
                    parameters[i] = currentCellValue;
                }
                result.add(parameters);
            }
            return result.toArray(new Object[result.size()][]);
        } else {
            throw new Error("There is no @ExcelDataSource annotation on method " + m);
        }
    }

}
