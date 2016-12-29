package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

@Test
public class CreateNewFileNegativeTest extends CreateNewFileTestBase {


    @Test(groups = {"negative"})
    public void testAttemptToCreateAFileInWithTheIncorrectFilename() throws IOException {
        System.out.println("Running first negative test.");
        String fileNameInvalidDirectory = tempDirectory.toString() + "//";
        File file = new File(fileNameInvalidDirectory);
        Assert.assertFalse(file.createNewFile(), "You just successfully created the file named '/'. It's not allowed it Windows and in most -nix distributions.");
    }

    @Test(groups = {"negative"}, expectedExceptions = { IOException.class })
    @TempDir(read = true, write = false)
    public void cannotCreateFileInAReadOnlyDir() throws IOException {
        String fileNameInvalidDirectory = tempDirectory.toString() + "/filename";
        File file = new File(fileNameInvalidDirectory);
        Assert.assertFalse(file.createNewFile(), "You just successfully created the file in the directory without writing permissions.");
    }

    @Test(groups = {"negative"})
    public void testFileCannotBeCreatedIfAlreadyExists() throws IOException {
        File file = new File(fileName);
        Assert.assertTrue(file.createNewFile(),
                "Something went wrong during the test. The target directory is not empty");
        Assert.assertFalse(file.createNewFile(),
                "Successfully created already existing file.");
    }
}

