package com.tests;
import com.pages.HomePages;
import com.util.DriverInit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.sql.Driver;
import java.util.Iterator;
import java.util.Set;

public class SampleTest extends DriverInit{

    public HomePages homePages;

    @BeforeClass
    public void initializeDriver(){
        DriverInit.driverInit("chrome");
    }

    @BeforeMethod(alwaysRun = true)
    public void init_PageObjects() {
        homePages = new HomePages(driver);
    }
    @Test
    public void capture_screenshot() {
        String price = homePages.checkPriceValidation();
        Assert.assertEquals(price, "3000");
    }
    @AfterMethod
    public void check(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File file = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("C:\\Users\\seeni\\IdeaProjects\\POM\\screenshots\\test.png"));
        }
    }

    @AfterClass
    public void quit(){
        driver.quit();
    }

}
