package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.*;

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

    Path tempDirectory;
    Path tempDirectory2;
    Path tempDirectoryWithoutWritingPermissions;
    String fileName;

    @BeforeMethod
    public void exampleBeforeMethod() {
        System.out.println("Creating temporary directory.");
        try {
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            tempDirectory2 = Files.createTempDirectory("test-createNewFile-directory-2");
            fileName = tempDirectory.toString() + "/" + fileName;
            System.out.println("TMP: " + tempDirectory.toString());

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

    @AfterMethod
    public void exampleAfterMethod(){
        System.out.println("Removing temporary directory.");
        try {
            FileUtils.deleteDirectory(new File(tempDirectory.toString()));
            FileUtils.deleteDirectory(new File(tempDirectory2.toString()));
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
