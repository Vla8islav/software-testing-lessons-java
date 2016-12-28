package com.softwaretestingtraning.app;

import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.rules.RuleChain;
import org.junit.runner.RunWith;
import java.io.File;
import java.io.IOException;
import static com.softwaretestingtraning.app.DataSource.Type.RESOURCE;
import static org.hamcrest.CoreMatchers.is;


@RunWith(DataProviderRunner.class)
public class CreateNewFilePositiveTest extends CreateNewFileTestBase {

    @Rule
    public RuleChain rules = RuleChain
            .outerRule(baseFileRule).around(repetitionRule);

    @Test
    @Category({PositiveTests.class})
    public void testFileCreationParameters() throws IOException {
        System.out.println("Running first positive test");
        File file = new File(baseFileRule.fileName);
        Assert.assertThat("File already exists. Something went wrong during the preparation.",
                file.createNewFile(), is(true));
        Assert.assertThat("File creation failed.",
                file.exists(), is(true));
        Assert.assertThat("Created file is not empty.",
                file.length(), is(0L));
    }


    @Test
    @Category({PositiveTests.class, BrokenTests.class})
    public void testFileIsEmptyIncorrect() {
        File file = new File(baseFileRule.fileName);
        Assert.assertThat("Created file is not empty.",
                file.length(), is(1L));
    }

    static private int variableForTheUnstableTest = 0;
    @Test
    @Category({PositiveTests.class, UnstableTests.class})
    @Unstable(2)
    public void testFileIsEmptyUnstable() {
        File file = new File(baseFileRule.fileName);
        if(0 == variableForTheUnstableTest) {
            variableForTheUnstableTest++;
            Assert.assertThat("Created file is not empty.",
                    file.length(), is(1L));
        }
        else
        {
            Assert.assertThat("Created file is not empty.",
                    file.length(), is(0L));
        }
    }

    @Test
    @Category({PositiveTests.class})
    @UseDataProvider(value = "dataSourceLoader", location = UniversalDataProviders.class)
    @DataSource(value = "/UnusualFilenames.data", type = RESOURCE)
    public void testCreateFilesWithUnusualValidFilenames(String currentFileName) throws IOException {
        String currentFullFileName = baseFileRule.tempDirectory.toString() + "/" + currentFileName;
        File file = new File(currentFullFileName);
        Assert.assertThat("Unable to create file named " + currentFileName,
                file.createNewFile(), is(true));
    }
}

