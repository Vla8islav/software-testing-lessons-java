package com.softwaretestingtraning.app;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@RunWith(DataProviderRunner.class)
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    @Test
    @Category({PositiveTests.class})
    public void testFileCreationParameters() throws IOException {
        System.out.println("Running first positive test");
        File file = new File(fileName);
        Assert.assertThat("File already exists. Something went wrong during the preparation.",
                file.createNewFile(), is(true));
        Assert.assertThat("File creation failed.",
                file.exists(), is(true));
        Assert.assertThat("Created file is not empty.",
                file.length(), is(equalTo(0L)));
    }

    @Test
    @Category({PositiveTests.class, BrokenTests.class})
    public void testFileIsEmptyIncorrect() {
        File file = new File(fileName);
        Assert.assertThat("Created file is not empty.",
                file.length(), is(1));
    }

    @DataProvider
    public static Object[][] loadFilenameFromFile() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class.getResourceAsStream("/UnusualFilenames.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }

        in.close();

        Object[] array = userData.toArray(new Object[userData.size()]);

        Object[][] array2 = new Object[][]{{"someStubString"}};

        return array2;
    }

    @Test
    @Category({PositiveTests.class, BrokenTests.class})
    @UseDataProvider("loadFilenameFromFile")
    public void testCreateFilesWithUnusualValidFilenames(String currentFileName) {
        String currentFullFileName = tempDirectory.toString() + "/" + currentFileName;
        File file = new File(currentFullFileName);
        try {
            Assert.assertTrue(file.createNewFile()/*, "Unable to create file named " + currentFileName*/);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
}

