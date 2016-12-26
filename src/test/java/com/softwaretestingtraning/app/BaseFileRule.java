package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

class BaseFileRule extends ExternalResource {
    Path tempDirectory;
    String fileName;

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
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
