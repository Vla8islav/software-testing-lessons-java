package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@Test
public class CreateNewFileNegativeTest extends AppTestBase {

    public void testAttemptToCreateAFileInNonexistentDirectory() {
        String fileNameInvalidDirectory = tempDirectory.toString() + "nonexistentDir/" + fileName;
        try {
            File file = new File(fileNameInvalidDirectory);
            Assert.assertTrue(file.createNewFile(), "File was created successfully in the nonexistent folder.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}

