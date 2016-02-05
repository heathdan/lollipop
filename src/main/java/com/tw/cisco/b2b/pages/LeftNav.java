package com.tw.cisco.b2b.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by aswathyn on 20/01/16.
 */
public class LeftNav extends BasePage<LeftNav> {



    @FindBys(@FindBy(xpath=".//li[@class='active']/button/span[@class='menu-text']"))
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

    @FindBy(xpath = ".//span[text()='Knowledge Library']")
    private WebElement knowledgeLibrary;

    @FindBy(xpath = ".//span[text()='My Files']")
    private WebElement myFiles;

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

    @FindBy(xpath = ".//span[text()='User']")
    private WebElement user;

    @FindBy(xpath = ".//span[text()='System']")
    private WebElement system;

    @FindBy(xpath = ".//span[text()='Mobile']")
    private WebElement mobile;

    @FindBy(xpath = ".//span[text()='Collaborate']")
    private WebElement collaborate1;

    @FindBy(xpath = ".//span[text()='Reporting']")
    private WebElement reporting;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Users']")
    private WebElement usersTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Roles and Permissions']")
    private WebElement rolesNPermissionTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Pending Registrations']")
    private WebElement pendingRegTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Define Expertise']")
    private WebElement defineExpertiseTab;

    public LeftNav(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfAllElements(leftNavPanel);
    }

    @Override
    protected void instantiatePage(LeftNav page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public UserPage navUsersTab(){
        admin.click();
        user.click();
        usersTab.click();
        return new UserPage(driver);
    }

    public DefineExpertisePage navDefineExpertiseTab(){
        admin.click();
        user.click();
        defineExpertiseTab.click();
        return new DefineExpertisePage(driver);
    }

    public RolesAndPermissionPage NavRolesTab(){
        admin.click();
        user.click();
        rolesNPermissionTab.click();
        return new RolesAndPermissionPage(driver);
    }
    /***********************GET/SET METHODS*********************/

    public List<WebElement> getLeftNavPanel() {
        return leftNavPanel;
    }

    public WebElement getWorkspace() {
        return workspace;
    }

    public WebElement getKnowledgeMap() {
        return knowledgeMap;
    }

    public WebElement getActivityStream() {
        return ActivityStream;
    }

    public WebElement getLearrning() {
        return learrning;
    }

    public WebElement getTrainingCatalog() {
        return trainingCatalog;
    }

    public WebElement getMyEnrollments() {
        return myEnrollments;
    }

    public WebElement getSharedLearning() {
        return sharedLearning;
    }

    public WebElement getLearningPlans() {
        return learningPlans;
    }

    public WebElement getKnowledgeCenter() {
        return knowledgeCenter;
    }

    public WebElement getKnowledgeLibrary() {
        return knowledgeLibrary;
    }

    public WebElement getMyFiles() {
        return myFiles;
    }

    public WebElement getCollaborate() {
        return collaborate;
    }

    public WebElement getCommunities() {
        return communities;
    }

    public WebElement getMyCommunities() {
        return myCommunities;
    }

    public WebElement getBlogs() {
        return blogs;
    }

    public WebElement getDiscussions() {
        return discussions;
    }

    public WebElement getWikiLibrary() {
        return wikiLibrary;
    }

    public WebElement getRSSFeeds() {
        return RSSFeeds;
    }

    public WebElement getPeople() {
        return people;
    }

    public WebElement getAllPeople() {
        return allPeople;
    }

    public WebElement getExperts() {
        return experts;
    }

    public WebElement getMobileFolder() {
        return mobileFolder;
    }

    public WebElement getMyCollections() {
        return myCollections;
    }

    public WebElement getMyNotes() {
        return myNotes;
    }

    public WebElement getSharedFolders() {
        return sharedFolders;
    }

    public WebElement getExternalData() {
        return externalData;
    }

    public WebElement getAdmin() {
        return admin;
    }

    public WebElement getMyTeam() {
        return myTeam;
    }

    public WebElement getUser() {
        return user;
    }

    public WebElement getSystem() {
        return system;
    }

    public WebElement getMobile() {
        return mobile;
    }

    public WebElement getCollaborate1() {
        return collaborate1;
    }

    public WebElement getReporting() {
        return reporting;
    }
}
