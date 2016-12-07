package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;

@Test
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    public void testFileExists() {
            File file = new File(fileName);
            Assert.assertTrue(file.exists(), "File creation failed.");
    }

    public void testFileIsEmpty() {
        File file = new File(fileName);
        Assert.assertEquals(file.length(), 0, "Created file is not empty.");
    }

    public void testFileCannotBeCreatedIfAlreadyExists() {
        try {
            File file = new File(fileName);
            Assert.assertFalse(file.createNewFile(), "File does not exist. Your set up method is probably broken.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

