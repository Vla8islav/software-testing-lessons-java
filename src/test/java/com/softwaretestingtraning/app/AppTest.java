package com.softwaretestingtraning.app;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    @Test()
    public void testFirst() {

        App obj = new App();

        Assert.assertNotNull(obj);

    }

}
