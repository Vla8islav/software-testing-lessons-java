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
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

@Test
public class CreateNewFileTestBase {

    protected Path tempDirectory;
    protected Path tempDirectoryWithoutWritingPermissions;
    protected String fileName;

    @BeforeClass(alwaysRun = true)
    public void exampleBeforeMethod() {
        System.out.println("Creating temporary directory.");
        try {
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            fileName = tempDirectory.toString() + "/" + fileName;
            System.out.println("TMP: " + tempDirectory.toString());
            File file = new File(fileName);
            Assert.assertTrue(file.createNewFile(), "File already exists. Your cleanup method is probably broken.");

            Set<PosixFilePermission> perms =
                    PosixFilePermissions.fromString("r-xr-xr-x");
            FileAttribute<Set<PosixFilePermission>> attr =
                    PosixFilePermissions.asFileAttribute(perms);

            Path nonWritableDir = Paths.get(tempDirectory.toString(), "nonWritableDir");
            tempDirectoryWithoutWritingPermissions = Files.createDirectory(nonWritableDir, attr);


        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @AfterClass(alwaysRun = true)
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