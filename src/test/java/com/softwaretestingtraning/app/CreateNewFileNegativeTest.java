package com.softwaretestingtraning.app;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExternalResource;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static org.hamcrest.CoreMatchers.is;

@RunWith(DataProviderRunner.class)
public class CreateNewFileNegativeTest extends CreateNewFileTestBase {

    private Path tempDirectory2;

    private ExternalResource negativeFileRule = new ExternalResource() {
        @Override
        public void before() throws Throwable {
            System.out.println("Creating temporary directory without writing permissions");
            tempDirectory2 = Files.createTempDirectory("test-createNewFile-directory-2");
            Set<PosixFilePermission> perms =
                    PosixFilePermissions.fromString("r-xr-xr-x");
            FileAttribute<Set<PosixFilePermission>> attr =
                    PosixFilePermissions.asFileAttribute(perms);
            Path nonWritableDir = Paths.get(tempDirectory.toString(), "nonWritableDir");
            tempDirectoryWithoutWritingPermissions = Files.createDirectory(nonWritableDir, attr);
        }

        @Override
        public void after() {
            System.out.println("Removing temporary directory.");
            try {
                FileUtils.deleteDirectory(new File(tempDirectoryWithoutWritingPermissions.toString()));
                FileUtils.deleteDirectory(new File(tempDirectory2.toString()));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    };

    @Rule
    public RuleChain rules = RuleChain
            .outerRule(baseFileRule)
            .around(negativeFileRule);

    @Test
    @Category({NegativeTests.class})
    public void testAttemptToCreateAFileInWithTheIncorrectFilename() throws IOException {
        System.out.println("Running first negative test.");
        String fileNameInvalidDirectory = tempDirectory.toString() + "//";
        File file = new File(fileNameInvalidDirectory);
        Assert.assertThat("You just successfully created the file named '/'. It's not allowed it Windows and in most -nix distributions.",
                file.createNewFile(), is(false));
    }

    @Test(expected = IOException.class)
    @Category({NegativeTests.class})
    public void testAttemptToCreateAFileInFolderWithoutWritingPermissions() throws IOException {
        String fileNameInvalidDirectory = tempDirectoryWithoutWritingPermissions.toString() + "/filename";
        File file = new File(fileNameInvalidDirectory);
        Assert.assertThat("You just successfully created the file in the directory without writing permissions.",
                file.createNewFile(), is(false));
    }

    @Test
    @Category({NegativeTests.class})
    public void testFileCannotBeCreatedIfAlreadyExists() throws IOException {
        File file = new File(fileName);
        Assert.assertThat("Something went wrong during the test. The target directory is not empty",
                file.createNewFile(), is(true));
        Assert.assertThat("Successfully created already existing file.",
                file.createNewFile(), is(false));
    }

    /**
     * Produces Fat32 limit of filenames
     * 2^16 - 1 according to http://stackoverflow.com/questions/466521/how-many-files-can-i-put-in-a-directory
     */
    @DataProvider
    public static Object[][] getFat32LimitFileNames() {

        List<String> filenameList = new ArrayList<>();
        for (int i = 0; i < (int) pow(2, 16); i++) {
            filenameList.add("file_" + Integer.toString(i));
        }
        return new Object[][]{{"2^16 - 1 filenames set", filenameList}};
    }

    @Test
    @Category({NegativeTests.class, LongTests.class})
    @UseDataProvider("getFat32LimitFileNames")
    public void testAttemptToCreateALimitOfFilesInOneDirectory(String testName, List<String> filenameList) {
        System.out.println("Attempting to create Fat32 directory limit of files and check whether they are created successfully. Using file set named" + testName);

        for (String fileNameCurrent : filenameList
                ) {
            String fileNameCurrentValid = tempDirectory2.toString() + "/" + fileNameCurrent;
            try {
                File file = new File(fileNameCurrentValid);
                Assert.assertThat("Cannot create file " + fileNameCurrent + "while attempting to create 2^16-1 files in a directory " + tempDirectory2,
                        file.createNewFile(), is(true));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

