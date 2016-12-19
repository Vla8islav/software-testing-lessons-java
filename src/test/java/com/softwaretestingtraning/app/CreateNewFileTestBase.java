package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.runner.Description;
import org.junit.runners.MethodSorters;
import org.junit.runners.model.Statement;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CreateNewFileTestBase {

    public Path tempDirectory;
    public Path tempDirectory2;
    public String fileName;
    public Path tempDirectoryWithoutWritingPermissions;
    @Before
    public void beforeZ() throws Throwable {
        System.out.println("Creating temporary directory.");
        tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
        tempDirectory2 = Files.createTempDirectory("test-createNewFile-directory-2");
        fileName = tempDirectory.toString() + "/test-filename-fixed";
        System.out.println("TMP: " + tempDirectory.toString());
    }

    @After
    public void afterZ() {
        System.out.println("Removing temporary directory.");
        try {
            FileUtils.deleteDirectory(new File(tempDirectory.toString()));
            FileUtils.deleteDirectory(new File(tempDirectory2.toString()));
        }
        catch (IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

}
