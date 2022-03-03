package test;

import driver.DriverHelper;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;

public class YouTubeResultFromGoogleSearchValidation_Test {
    BasePage basePage = new BasePage();
    private String firstVideoTitle = "";
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

        firstVideoTitle = basePage.getFirstVideoText();

        System.out.println("I open the first video");
        basePage.openFistVideo();

        System.out.println("I click play button");
        basePage.clickPlayButton();

        System.out.println("I pause the video after 10 seconds");
        basePage.clickPlayButtonAfterTime();

        System.out.println("The video title should be same to Google result");
        Assert.assertEquals(firstVideoTitle, basePage.getVideoTitle(),
                "The video title should be same to Google result");

        System.out.println("The video should be played or paused accordingly");
        Assert.assertFalse(basePage.isVideoPlay(),
                "The video should be played or paused accordingly");
    }

    @AfterMethod
    public void closeBrowser() {
        DriverHelper.quitBrowser();
    }
}
