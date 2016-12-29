package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

@Test
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    @Test(groups = {"positive"})
    public void testFileCreationParameters() throws IOException {
        System.out.println("Running first positive test");
        File file = new File(fileName);
        Assert.assertTrue(file.createNewFile(),
                "File already exists. Something went wrong during the preparation.");
        Assert.assertTrue(file.exists(), "File creation failed.");
        Assert.assertEquals(file.length(), 0, "Created file is not empty.");
    }


    @Test(groups = {"positive"}, dataProvider = "excelDataProvider", dataProviderClass = ExcelDataProviders.class)
    @ExcelDataSource("UnusualFilenames.xls")
    public void testCreateFilesWithUnusualValidFilenames(String currentFileName) throws IOException {
        String currentFullFileName = tempDirectory.toString() + "/" + currentFileName;
        File file = new File(currentFullFileName);
        Assert.assertTrue(file.createNewFile(), "Unable to create file named " + currentFileName);
    }

}

