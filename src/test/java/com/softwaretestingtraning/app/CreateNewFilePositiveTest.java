package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Test
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    @Test(groups = {"positive"})
    public void testFileExists() {
        System.out.println("Running first positive test");
        File file = new File(fileName);
        Assert.assertTrue(file.exists(), "File creation failed.");
    }

    @Test(groups = {"positive"})
    public void testFileIsEmpty() {
        File file = new File(fileName);
        Assert.assertEquals(file.length(), 0, "Created file is not empty.");
    }

    @Test(groups = {"positive"})
    public void testFileCannotBeCreatedIfAlreadyExists() {
        try {
            File file = new File(fileName);
            Assert.assertFalse(file.createNewFile(), "File does not exist. Your set up method is probably broken.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"positive", "broken"})
    public void testFileIsEmptyIncorrect() {
        File file = new File(fileName);
        Assert.assertEquals(file.length(), 1, "Created file is not empty.");
    }

    @Test(groups = {"positive"}, dataProviderClass = DataProviders.class, dataProvider = "loadFilenameFromFile")
    public void testCreateFilesWithUnusualValidFilenames(String currentFileName) {
        String currentFullFileName = tempDirectory.toString() + "/" + currentFileName;
        File file = new File(currentFullFileName);
        try {
            Assert.assertTrue(file.createNewFile(), "Unable to create file named " + currentFileName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}

