package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import utils.Constants;
import java.util.List;
import java.util.stream.Collectors;

public class BasePage {

    /**
     * Element locators of the web elements' locator
     **/
    private final By _searchInput = By.cssSelector("[name='q']");
    private final By _mainResult = By.xpath("//div[@class=\"liYKde g VjDLd\"]//h2[@data-attrid=\"title\"]/span");
    private final By _videosSection = By.xpath("//div[@class=\"ULSxyf\"]//div[@class=\"sI5x9c\"]");
    private final By _playButton = By.xpath("//button[@class=\"ytp-play-button ytp-button\"]");
    private final By _videoTitle = By.xpath("//h1[@class=\"title style-scope ytd-video-primary-info-renderer\"]");

    /**
     * This is place create Web elements
     */

    private WebElement searchInput() {
        return Constants.DRIVER.findElement(_searchInput);
    }

    private WebElement mainResult() {
        return Constants.DRIVER.findElement(_mainResult);
    }

    private List<WebElement> videosSection() {
        return Constants.DRIVER.findElements(_videosSection);
    }

    private WebElement playButton() {
        return Constants.DRIVER.findElement(_playButton);
    }

    private WebElement videoTitle() {
        return Constants.DRIVER.findElement(_videoTitle);
    }

    /**
     * This is place create function
     */

    public void navigateToGoogle() {
        Constants.DRIVER.get(Constants.GOOGLE_URL);
    }

    public void openFistVideo() {
        videosSection().get(0).click();
    }

    public void inputSearchInput(String searchKey) {
        searchInput().sendKeys(searchKey);
        searchInput().sendKeys(Keys.RETURN);
    }

    public String getMainResultText() {
        return mainResult().getText();
    }

    public String getSearchInputText() {
        return searchInput().getAttribute("value").toLowerCase();
    }

    public List<String> listVideosText() {
        return videosSection().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public String getFirstVideoText() {
        String[] videoTextArr = listVideosText().get(0).split("\n");
        return videoTextArr[2].toString();
    }

    public void clickPlayButton() {
        playButton().click();
    }

    public String getVideoTitle(){
        return videoTitle().getText();
    }

    public void clickPlayButtonAfterTime() {
        try {
            Thread.sleep(Constants.PLAYTIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        playButton().click();
    }

    public boolean isVideoText(String query) {
        return listVideosText().stream().anyMatch(str -> str.trim().contains(query));
    }

    public boolean isVideoPlay() {
        return playButton().getAttribute("title").contains("Pause");
    }

}
