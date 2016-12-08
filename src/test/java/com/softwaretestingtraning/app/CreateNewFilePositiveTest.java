package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

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
}

