package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static java.lang.Math.pow;

@Test
public class CreateNewFileNegativeTest extends CreateNewFileTestBase {

    /**
     * Produces Fat32 limit of filenames
     * 2^16 - 1 according to http://stackoverflow.com/questions/466521/how-many-files-can-i-put-in-a-directory
     */
    @DataProvider
    public Iterator<Object[]> getFat32LimitFileNames() {
        List<Object[]> data = new ArrayList<>();

        List<String> filenameList = new ArrayList<>();
        for (int i = 0; i < (int) pow(2, 16); i++) {
            filenameList.add("file_" + Integer.toString(i));
        }
        data.add(new Object[]{"2^16 - 1 filenames set", filenameList});
        return data.iterator();
    }

    @Test(groups = {"negative"})
    public void testAttemptToCreateAFileInWithTheIncorrectFilename() {
        System.out.println("Running first negative test.");
        String fileNameInvalidDirectory = tempDirectory.toString() + "//";
        try {
            File file = new File(fileNameInvalidDirectory);
            Assert.assertFalse(file.createNewFile(), "You just successfully created the file named '/'. It's not allowed it Windows and in most -nix distributions.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"negative"})
    public void testAttemptToCreateAFileInFolderWithoutWritingPermissions() {
        String fileNameInvalidDirectory = tempDirectoryWithoutWritingPermissions.toString() + "/filename";
        try {
            File file = new File(fileNameInvalidDirectory);
            Assert.assertFalse(file.createNewFile(), "You just successfully created the file in the directory without writing permissions.");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Test(groups = {"negative", "long"}, dataProvider = "getFat32LimitFileNames")
    public void testAttemptToCreateALimitOfFilesInOneDirectory(String testName, List<String> filenameList) {
        System.out.println("Attempting to create Fat32 directory limit of files and check whether they are created successfully. Using file set named" + testName);

        for (String fileNameCurrent : filenameList
                ) {
            String fileNameCurrentValid = tempDirectory2.toString() + "/" + fileNameCurrent;
            try {
                File file = new File(fileNameCurrentValid);
                Assert.assertTrue(file.createNewFile(), "Cannot create file " + fileNameCurrent + "while attempting to create 2^16-1 files in a directory " + tempDirectory2);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

