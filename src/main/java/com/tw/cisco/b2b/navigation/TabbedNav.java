package com.tw.cisco.b2b.navigation;

import com.tw.cisco.b2b.exceptions.ClickIconNotFoundException;
import com.tw.cisco.b2b.pages.*;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by aswathyn on 08/02/16.
 */
public class TabbedNav extends BasePage<TabbedNav> {

    private LeftNav leftNav;
    public enum TabName {
        USERS,
        ROLESPERMISSIONS,
        DEFINEEXPERTISE,
        PROFILE,
        MYACTIVITIES,
        LICENSING;

    }
    static final Logger LOGGER = LoggerFactory.getLogger(TabbedNav.class);

    @FindBy(xpath=".//ul[@id='adminTab']//a[text()='Users']")
    private WebElement userTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Roles and Permissions']")
    private WebElement rolesNPermissionTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Pending Registrations']")
    private WebElement pendingRegTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Define Expertise']")
    private WebElement defineExpertiseTab;

    @FindBy(xpath = ".//ul[@id='adminTab']//a[text()='Licensing']")
    private WebElement licensingTab;

    @FindBy(xpath=".//div[@class='tabbable toggle_view admin-tabs']/ul[@id='adminTab']/li")
    private List<WebElement> adminTabs;

    @FindBy(xpath=".//div[@id='user-profile']//a[contains(text(),'Profile')]")
    private WebElement myProfile;

    @FindBy(xpath =".//div[@id='user-profile']//a[contains(text(),'My Activities')]")
    private WebElement myActivities;

    HeaderNav headerNav;
    public TabbedNav(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        headerNav = new HeaderNav(driver);
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOfAllElements(adminTabs);
    }

    @Override
    protected void instantiatePage(TabbedNav page) {
        try {
            LOGGER.info("** instantiatePage(): "+ page.getClass().getSimpleName());
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            LOGGER.error("--- Error in instantiating page:",page.getClass().getSimpleName());
        }
    }

    public void isTabPresent(String tabName) {
        boolean value= false;
        for(WebElement tab : adminTabs) {
            if(tab.getText().equals(tabName)) {
                value = true;
                break;
            }
        }
        Assert.assertTrue(value);
    }

    public BasePage navToTab(TabName tabName) throws ClickIconNotFoundException {
        LOGGER.trace(">>navToTab() "+tabName);
        BasePage page = null;
        switch (tabName) {
            case USERS:
                clickIcon(userTab, "USER_TAB");
                LOGGER.debug("Clicked userTab");
                page = new UserPage(driver);
                break;

                 /*   case "Pending Registrations":
                        pendingRegTab.click();
                        page= new PendingRegPage(driver);
                        break;
                        */
            case ROLESPERMISSIONS:
                clickIcon(rolesNPermissionTab, "ROLES_PERMISSION_TAB");
                LOGGER.debug("clicked role and permission");
                page = new RolesAndPermissionPage(driver);
                break;

            case DEFINEEXPERTISE:
                clickIcon(defineExpertiseTab, "DEFINE_EXPERTISE");
                LOGGER.debug("Navigating to DefineExpertise");
                page = new DefineExpertisePage(driver);
                break;

            /*case PENDINGREGISTRATIONS:
                pendingRegTab.click();
                page = new DefineExpertisePage(driver);
                break;
                */
            case LICENSING:
                clickIcon(licensingTab,"LICENSING");
                page = new LicensingPage(driver);
                break;
            /*
                    case "Site Management":
                        status = page.getMobileFolder().isDisplayed();
                        break;
                    case "Mobile Folder":
                        status = page.getPeople().isDisplayed();
                        break;
                    case "Categories":
                        status = page.getPeople().isDisplayed();
                        break;
                    case "Blog Proxy Roles":
                        status = page.getPeople().isDisplayed();
                        break;
                    case "Usage":
                        status = page.getPeople().isDisplayed();
                        break;
                    case "Global Activity Stream":
                        status = page.getPeople().isDisplayed();
                        break; */

            case PROFILE:
                clickIcon(myProfile, "MY_PROFILE");
                LOGGER.debug("Navigating to My Profile");
                page = new ProfilePage(driver);
                break;

            case MYACTIVITIES:
                clickIcon(myActivities, "MY_ACTIVITIES");
                LOGGER.debug("Navigating to My Activity");
                page = new MyActivityPage(driver);
                break;

            default:
                LOGGER.error("--- "+tabName+" not found");
                break;
            }
        return page;
    }

    /************************GET/SET METHODS******************************/

    public LeftNav getLeftNav() {
        return leftNav;
    }

    public void setLeftNav(LeftNav leftNav) {
        this.leftNav = leftNav;
    }

    public HeaderNav getHeaderNav() {
        return headerNav;
    }

    public void setHeaderNav(HeaderNav headerNav) {
        this.headerNav = headerNav;
    }
}
