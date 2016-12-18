package com.softwaretestingtraning.app;

import com.codeborne.selenide.Configuration;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideBankiruSampleTest {

    @BeforeClass
    public static void exampleBeforeMethod() {
        System.out.println("Setting Chromium as a default browser.");
        Configuration.browser = "Chrome";
        System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
    }

    @Test
    @Category({PositiveTests.class, SeleniumTests.class})
    public void testNewsMainPage() {
        open("http://www.banki.ru/news/");
        $("img.header__logo__img").shouldBe(visible);
    }

    @AfterClass
    public static void exampleAfterMethod(){
        System.out.println("Exiting sample selenide test");
    }
}
