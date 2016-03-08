package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
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
 * Created by aswathyn on 20/01/16.
 */
public class LeftNav extends BasePage<LeftNav> {

    @FindBys(@FindBy(xpath = ".//li[@class='active']/button/span[@class='menu-text']"))
    private List<WebElement> leftNavPanel;

    @FindBy(xpath = ".//span[text()=' Workspace']")
    private WebElement workspace;

    @FindBy(xpath = ".//span[text()='Knowledge Map']")
    private WebElement knowledgeMap;

    @FindBy(xpath = ".//span[text()='Activity Stream']")
    private WebElement ActivityStream;

    @FindBy(xpath = ".//span[text()=' Learning']")
    private WebElement learrning;

    @FindBy(xpath = ".//span[text()='Training Catalog']")
    private WebElement trainingCatalog;

    @FindBy(xpath = ".//span[text()='My Enrollments']")
    private WebElement myEnrollments;

    @FindBy(xpath = ".//span[text()='Shared Learning']")
    private WebElement sharedLearning;

    @FindBy(xpath = ".//span[text()='Learning Plans']")
    private WebElement learningPlans;

    @FindBy(xpath = ".//span[text()=' Knowledge Center']")
    private WebElement knowledgeCenter;

    @FindBy(xpath = ".//span[text()=' Collaborate']")
    private WebElement collaborate;

    @FindBy(xpath = ".//span[text()='Communities']")
    private WebElement communities;

    @FindBy(xpath = ".//span[text()='My Communities']")
    private WebElement myCommunities;

    @FindBy(xpath = ".//span[text()='Blogs']")
    private WebElement blogs;

    @FindBy(xpath = ".//span[text()='Discussions']")
    private WebElement discussions;

    @FindBy(xpath = ".//span[text()='Wiki Library']")
    private WebElement wikiLibrary;

    @FindBy(xpath = ".//span[text()='RSS Feeds']")
    private WebElement RSSFeeds;

    @FindBy(xpath = ".//span[text()=' People']")
    private WebElement people;

    @FindBy(xpath = ".//span[text()='All People']")
    private WebElement allPeople;

    @FindBy(xpath = ".//span[text()='Experts']")
    private WebElement experts;

    @FindBy(xpath = ".//span[text()=' Mobile Folder']")
    private WebElement mobileFolder;

    @FindBy(xpath = ".//span[text()='My Collections']")
    private WebElement myCollections;

    @FindBy(xpath = ".//span[text()='My Notes']")
    private WebElement myNotes;

    @FindBy(xpath = ".//span[text()='Shared Folders']")
    private WebElement sharedFolders;

    @FindBy(xpath = ".//span[text()='External Data']")
    private WebElement externalData;

    @FindBy(xpath = ".//span[(text()='Admin')]")
    private WebElement admin;

    @FindBy(xpath = ".//span[contains(text(),'My Team')]")
    private WebElement myTeam;

    static final Logger LOGGER = LoggerFactory.getLogger(LeftNav.class);

    public LeftNav(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfAllElements(leftNavPanel);
    }

    @Override
    protected void instantiatePage(LeftNav page) {
        try {
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            LOGGER.error("--- Error instantiating :"+page.toString());
        }
    }

    public AdminPage navToAdmin() {
        try {
            clickIcon(admin,"Admin Tab");
        } catch (ClickIconNotFoundException ex) {
            LOGGER.error("Admin access is missing", ex);
        }
        return new AdminPage(driver);
    }

    public KCPage navToAdminKC() {
        admin.click();
        return new KCPage(driver);
    }

    /***********************GET/SET METHODS*********************/
}
