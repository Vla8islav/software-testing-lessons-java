package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Test
public class CreateNewFileTestBase {

    Path tempDirectory;
    Path tempDirectory2;
    String fileName;

    @BeforeMethod
    public void exampleBeforeMethod() throws IOException {
        System.out.println("Creating temporary directory.");
        tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
        tempDirectory2 = Files.createTempDirectory("test-createNewFile-directory-2");
        fileName = tempDirectory.toString() + "/test-filename-fixed";
        System.out.println("TMP: " + tempDirectory.toString());
    }

    @AfterMethod
    public void exampleAfterMethod() throws IOException {
        System.out.println("Removing temporary directory.");
        FileUtils.deleteDirectory(new File(tempDirectory.toString()));
        FileUtils.deleteDirectory(new File(tempDirectory2.toString()));
    }
}
