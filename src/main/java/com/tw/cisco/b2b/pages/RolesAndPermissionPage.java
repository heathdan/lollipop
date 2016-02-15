package com.tw.cisco.b2b.pages;

import com.tw.cisco.b2b.navigation.HeaderNav;
import com.tw.cisco.b2b.navigation.TabbedNav;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Created by aswathyn on 22/01/16.
 */
public class RolesAndPermissionPage extends BasePage<RolesAndPermissionPage> {

    private HeaderNav headerNav;
    private LeftNav leftNav;
    private TabbedNav tabNav;

    @FindBy(className = "admin-tab-roles")
    private WebElement rolesTable;

    @FindBy(className = "icon-caret-down")
    private WebElement filter;

    @FindBy(xpath = "//a[@class='sorter-toggle'][@data-icon-before='sort-descending']")
    private WebElement sortDescending;

    @FindBy(xpath = "//a[@class='sorter-toggle'][@data-icon-before='sort-ascending']")
    private WebElement sortAscending;

    @FindBy(id = "qa_automation_Name")
    private WebElement filterByName;

    @FindBy(id = "qa_automation_Date Created")
    private WebElement filterByDateCreated;

    @FindBy(id = "qa_automation_Date Modified")
    private WebElement filterByDateModified;

    @FindBy(id = "qa_automation_Last Activated / Deactivated")
    private WebElement filterByLastStatusChange;

    @FindBy(xpath = ".//button[@class='btn upload-btn']")
    private WebElement createRole;

    @FindBys(@FindBy(xpath = "//p[contains(@class,'item-name')]"))
    private List<WebElement> rolesList;

    /* //span[text()='Status']/parent::div */

    @FindBys(@FindBy(xpath = "//p[contains(@class,'item-name')]/parent::div/parent::div//span[text()='Status']/parent::div"))
    private List<WebElement> statusList;

    @FindBy(xpath = "//p[contains(@class,'item-name') and text()='Learner']/parent::div/parent::div//span[text()='Status']/parent::div")
    private WebElement learnerStatus;

    @FindBy(xpath = "//p[contains(@class,'item-name') and text()='Learner']/parent::div/parent::div//span[text()='Actions']/parent::div//button")
    private WebElement learnerViewRole;

    @FindBys(@FindBy(xpath = "//button[@data-original-title='Deactivate Role']"))
    private List<WebElement> deactivateRoleList;

    @FindBys(@FindBy(xpath = "//[@data-original-title='Edit Permissions']"))
    private List<WebElement> editRoleList;

    @FindBys(@FindBy(xpath = "//[@data-original-title= 'Delete Role']"))
    private List<WebElement> deleteRoleList;

    @FindBy(xpath = "//p[text()='PLP Manager']/parent::div/parent::div//button[@data-original-title='Edit Permissions']")
    private WebElement customEditRole;

    @FindBy(xpath = "//p[text()='PLP Manager']/parent::div/parent::div//button[@data-original-title='Deactivate Role']")
    private WebElement customDeActivateRole;

    @FindBy(xpath = "//p[text()='PLP Manager']/parent::div/parent::div//button[@data-original-title='Delete Role']")
    private WebElement customDeleteRole;

    @FindBy(xpath = "//ul[@class='pagination']/li/a[text()='← Previous']")
    private WebElement paginationPrevious;

    @FindBy(xpath = "//ul[@class='pagination']//a[text()='Next →']")
    private WebElement paginationNext;

    @FindBy(xpath = "//li[@class='align-center']/input")
    private WebElement paginateFrom;

    @FindBy(xpath = "//li[@class='align-center']")
    private WebElement paginateUntil;

    public RolesAndPermissionPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
        leftNav = new LeftNav(driver);
        headerNav = new HeaderNav(driver);
        tabNav = new TabbedNav(driver);
    }

    @Override
    protected void instantiatePage(RolesAndPermissionPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(rolesTable);
    }

    public RoleCreatePopupPage clickRoleCreation() {
        createRole.click();
        return new RoleCreatePopupPage(driver);
    }

    public RolesAndPermissionPage printPagination() throws InterruptedException {
        paginationNext.click();
        Thread.sleep(2000);
        System.out.println(" paginate from =  " +  paginateFrom.getAttribute("value"));
        System.out.println(" paginate until =  " + paginateUntil.getText());
        return new RolesAndPermissionPage(driver);
    }

    /*********************GET/SET METHODS*********************/

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

    public TabbedNav getTabNav() {
        return tabNav;
    }

    public void setTabNav(TabbedNav tabNav) {
        this.tabNav = tabNav;
    }
}
