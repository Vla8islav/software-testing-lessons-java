package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

class NegativeFileRule extends ExternalResource {
    Path tempDirectoryWithoutWritingPermissions;

    @Override
    protected void before() throws Throwable {
        System.out.println("Creating temporary directory without writing permissions");
        Set<PosixFilePermission> perms =
                PosixFilePermissions.fromString("r-xr-xr-x");
        FileAttribute<Set<PosixFilePermission>> attr =
                PosixFilePermissions.asFileAttribute(perms);
        tempDirectoryWithoutWritingPermissions = Files.createTempDirectory("test-nonWritableDir", attr);
    }

    @Override
    protected void after() {
        System.out.println("Removing temporary directory without writing permissions.");
        try {
            FileUtils.deleteDirectory(new File(tempDirectoryWithoutWritingPermissions.toString()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
