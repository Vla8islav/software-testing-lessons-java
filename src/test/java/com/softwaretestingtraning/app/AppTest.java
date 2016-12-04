package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Test
public class AppTest {

    private Path tempDirectory;
    private String fileName;

    @BeforeClass
    public void exampleBeforeMethod() {
        System.out.println("Creating temporary directory.");
        try {
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            fileName = tempDirectory.toString() + fileName;
            System.out.println("TMP: " + tempDirectory.toString());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void testFirst() {
        File file = new File(fileName);
        try {
            Assert.assertTrue(file.createNewFile(), "File already exists");
        }
        catch (IOException e){
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
