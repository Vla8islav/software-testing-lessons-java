package com.softwaretestingtraning.app;

import com.codeborne.selenide.Configuration;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExternalResource;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SelenideBankiruSampleTest {

    @ClassRule
    public static ExternalResource selenideRule = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            System.out.println("Setting Chromium as a default browser.");
            Configuration.browser = "Chrome";
            System.setProperty("webdriver.chrome.driver", "/usr/lib/chromium-browser/chromedriver");
        }

        @Override
        protected void after() {
            System.out.println("Exiting sample selenide test");
        }
    };


    @Test
    @Category({PositiveTests.class, SeleniumTests.class})
    public void testNewsMainPage() {
        open("http://www.banki.ru/news/");
        $("img.header__logo__img").shouldBe(visible);
    }

}
