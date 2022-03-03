package test;

import driver.DriverHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;


public class GoogleSearchResultValidation_Test {
    BasePage basePage = new BasePage();
    private String txtSearch = "The Beatles";
    private String sections = "Video";

    @BeforeMethod
    @Parameters("browser")
    public void setUpBrowser(String browser) {
        DriverHelper.openChromeBrowser();
    }

    @Test
    public void googleTest() {
        System.out.println("I navigate to the Google page");
        basePage.navigateToGoogle();

        System.out.println("I search for " + txtSearch);
        basePage.inputSearchInput(txtSearch);

        System.out.println("The main result should contains " + txtSearch + " text");
        Assert.assertEquals(basePage.getMainResultText(), txtSearch,
                "The main result should contains " + txtSearch + " text");

        System.out.println("The special sections " + sections + " should contains " + txtSearch + " text");
        Assert.assertTrue(basePage.isVideoText(txtSearch),
                "The special sections " + sections + " should contains " + txtSearch + " text");

        System.out.println("The text \"" + txtSearch + "\" remained on the search box");
        Assert.assertEquals(basePage.getSearchInputText(), txtSearch.toLowerCase(),
                "The search box should contains query text");
    }

    @AfterMethod
    public void closeBrowser() {
        DriverHelper.quitBrowser();
    }

}
