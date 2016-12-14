package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

@Test
public class CreateNewFileNegativeTest extends CreateNewFileTestBase {

    @Test(groups = {"negative"})
    public void testAttemptToCreateAFileInWithTheIncorrectFilename() {
        System.out.println("Running first negative test.");
        String fileNameInvalidDirectory = tempDirectory.toString() + "//";
        try {
            File file = new File(fileNameInvalidDirectory);
            Assert.assertFalse(file.createNewFile(), "You just successfully created the file named '/'. It's not allowed it Windows and in most -nix distributions.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"negative"})
    public void testAttemptToCreateAFileInFolderWithoutWritingPermissions() {
        String fileNameInvalidDirectory = tempDirectoryWithoutWritingPermissions.toString() + "/filename";
        try {
            File file = new File(fileNameInvalidDirectory);
            Assert.assertFalse(file.createNewFile(), "You just successfully created the file in the directory without writing permissions.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}

