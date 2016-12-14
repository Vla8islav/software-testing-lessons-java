package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

@Test
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    @Test(groups = {"positive"})
    public void testFileCreationParameters() {
        System.out.println("Running first positive test");
        try {
            File file = new File(fileName);
            Assert.assertTrue(file.createNewFile(), "File already exists. Your cleanup method is probably broken.");
            Assert.assertTrue(file.exists(), "File creation failed.");
            Assert.assertEquals(file.length(), 0, "Created file is not empty.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"positive"})
    public void testFileCannotBeCreatedIfAlreadyExists() {
        try {
            File file = new File(fileName);
            if(!file.exists()){
                file.createNewFile();
            }
            Assert.assertFalse(file.createNewFile(), "Successfully created already existing file.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"positive", "broken"})
    public void testFileIsEmptyIncorrect() {
        File file = new File(fileName);
        Assert.assertEquals(file.length(), 1, "Created file is not empty.");
    }
}

