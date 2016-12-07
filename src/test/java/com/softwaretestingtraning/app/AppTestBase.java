package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Test
public class AppTestBase {

    private Path tempDirectory;
    protected String fileName;

    @BeforeClass
    public void exampleBeforeMethod() {
        System.out.println("Creating temporary directory.");
        try {
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            fileName = tempDirectory.toString() + fileName;
            System.out.println("TMP: " + tempDirectory.toString());
            File file = new File(fileName);
            Assert.assertTrue(file.createNewFile(), "File already exists. Your cleanup method is probably broken.");

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @AfterClass
    public void exampleAfterMethod(){
        System.out.println("Removing temporary directory.");
        try {
            FileUtils.deleteDirectory(new File(tempDirectory.toString()));
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
