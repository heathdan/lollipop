package com.tw.cisco.b2b.pages.knowledgeCenter;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by chandrad on 3/16/16.
 */
public class KCPropertiesPage extends BasePage<KCPropertiesPage> {

    @FindBy(id = "profile-layout")
    private WebElement kcPageWait;

    @FindBy(xpath = ".//span[@class='file-type-icon']/div")
    private WebElement fileExtension;

    @FindBy(className = "lefttitle eclipse-text")
    private WebElement fileTitle;

    @FindBy(xpath = ".//span[@class='rightbutton']//a")
    private WebElement viewButtonForEpubMP4;

    @FindBys(@FindBy (xpath = ".//div[@class='rating_container']/i"))
    private List<WebElement> ratingIconList;

    @FindBy(xpath = ".//div[@class='rating_container']/i[@class='glyphicon glyphicon-star']")
    private List<WebElement> starredRatingList;

    @FindBy(xpath = ".//div[@class='rating_container']/i[@class='glyphicon glyphicon-star-empty']")
    private List<WebElement> emptyRatingList;

    @FindBy(id = "__thumbs_count")
    private WebElement likeCount;

    @FindBy(xpath = ".//i[@data-original-title='Favorite']")
    private WebElement markAsFavourite;

    @FindBy(xpath = ".//i[@data-original-title='Undo Favorite']")
    private WebElement undoFavourite;

    @FindBy(xpath = ".//i[@data-original-title='Follow']")
    private WebElement follow;

    @FindBy(xpath = ".//i[@data-original-title='Un-Follow']")
    private WebElement unfollow;

    @FindBy(xpath = ".//li[@class='download-enabled']//a")
    private WebElement downloadFile;

    @FindBy(xpath = ".//li[@class='editing-enabled']//i")
    private WebElement editFile;

    @FindBy(xpath = ".//li//i[@class='icon-arrow-share']")
    private WebElement shareFile;

    @FindBy(xpath = "//label[@class='tc-course-title']/div")
    private WebElement fileName;

    public KCPropertiesPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    static final Logger LOGGER = LoggerFactory.getLogger(KCPropertiesPage.class);

    @Override
    protected void instantiatePage(KCPropertiesPage page) {
        try {
            LOGGER.info("** instantiatePage(): "+page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.getClass().getSimpleName());
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(kcPageWait);
    }

    public EditFilePage navToEditFile() throws ClickIconNotFoundException{
        clickIcon(editFile,"Edit File");
        return new EditFilePage(driver);
    }

    public void editFileRating() {

    }

}
