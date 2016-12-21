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

    Path tempDirectory;
    String fileName;
    Path tempDirectoryWithoutWritingPermissions;

    ExternalResource baseFileRule = new ExternalResource() {
        @Override
        public void before() throws Throwable {
            System.out.println("Creating temporary directory.");
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            fileName = tempDirectory.toString() + "/test-filename-fixed";
            System.out.println("TMP: " + tempDirectory.toString());
        }

        @Override
        public void after() {
            System.out.println("Removing temporary directory.");
            try {
                FileUtils.deleteDirectory(new File(tempDirectory.toString()));
            }
            catch (IOException e)
            {
                System.err.println(e.getMessage());
            }
        }

    };
}
