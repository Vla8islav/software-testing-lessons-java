package com.softwaretestingtraning.app;

import com.codeborne.selenide.Configuration;
import org.testng.Assert;
import org.testng.annotations.*;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


@Test
public class SelenideBankiruSampleTest {

    @BeforeClass(alwaysRun = true)
    public void exampleBeforeMethod() {
        System.out.println("Setting Chromium as a default browser.");
        Configuration.browser = "Chrome";
        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
    }

    @Test(groups = {"positive", "selenium"})
    public void testNewsMainPage() {
        open("http://www.banki.ru/news/");
        $("img.header__logo__img").shouldBe(visible);
    }

    @AfterClass(alwaysRun = true)
    public void exampleAfterMethod(){
        System.out.println("Exiting sample selenide test");
    }
}
