package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.rules.ExternalResource;
import org.junit.runners.MethodSorters;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CreateNewFileTestBase {

    Path tempDirectory;
    String fileName;
    Path tempDirectoryWithoutWritingPermissions;

    ExternalResource baseFileRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("Creating temporary directory.");
            tempDirectory = Files.createTempDirectory("test-createNewFile-directory");
            fileName = tempDirectory.toString() + "/test-filename-fixed";
            System.out.println("TMP: " + tempDirectory.toString());
        }

        @Override
        protected void after() {
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
