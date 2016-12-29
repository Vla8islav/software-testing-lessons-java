package com.softwaretestingtraning.app;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.HashSet;
import java.util.Set;

@Test
public class CreateNewFileTestBase {

    Path tempDirectory;
    String fileName;

    @BeforeMethod(alwaysRun = true)
    public void exampleBeforeMethod(Method m) throws IOException {

        TempDir tmpDir = m.getAnnotation(TempDir.class);

        System.out.println("Creating temporary directory with custom permissions");
        Set<PosixFilePermission> perms = new HashSet<>();
        boolean dirWrite = true;
        boolean dirRead = true;
        boolean dirExecute = true;
        if (null != tmpDir) {
            dirRead = tmpDir.read();
            dirWrite = tmpDir.write();
            dirExecute = tmpDir.execute();
        }
        if (dirRead) {
            perms.add(PosixFilePermission.OWNER_READ);
            perms.add(PosixFilePermission.GROUP_READ);
            perms.add(PosixFilePermission.OTHERS_READ);
        }
        if (dirWrite) {
            perms.add(PosixFilePermission.OWNER_WRITE);
            perms.add(PosixFilePermission.GROUP_WRITE);
            perms.add(PosixFilePermission.OTHERS_WRITE);
        }
        if (dirExecute) {
            perms.add(PosixFilePermission.OWNER_EXECUTE);
            perms.add(PosixFilePermission.GROUP_EXECUTE);
            perms.add(PosixFilePermission.OTHERS_EXECUTE);
        }
        FileAttribute<Set<PosixFilePermission>> attr =
                PosixFilePermissions.asFileAttribute(perms);

        tempDirectory = Files.createTempDirectory("test-createNewFile-directory", attr);
        fileName = tempDirectory.toString() + "/test-filename-fixed";
        System.out.println("TMP: " + tempDirectory.toString());

    }

    @AfterMethod(alwaysRun = true)
    public void exampleAfterMethod() throws IOException {
        System.out.println("Removing temporary directory.");
        FileUtils.deleteDirectory(new File(tempDirectory.toString()));
    }
}
