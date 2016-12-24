package com.softwaretestingtraning.app;

import org.junit.FixMethodOrder;
import org.junit.rules.ExternalResource;
import org.junit.runners.MethodSorters;

import java.nio.file.Path;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class CreateNewFileTestBase {

    Path tempDirectoryWithoutWritingPermissions;

    BaseFileRule baseFileRule = new BaseFileRule();

}
