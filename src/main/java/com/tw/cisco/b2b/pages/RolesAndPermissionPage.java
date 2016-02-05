package com.tw.cisco.b2b.pages;

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

    @FindBy(className ="admin-table-content")
    private WebElement rolesTable;

    @FindBy(className = "icon-caret-down")
    private WebElement filter ;

    @FindBy(xpath = "//a[@class='sorter-toggle'][@data-icon-before='sort-descending']")
    private WebElement sortDescending ;

    @FindBy(xpath = "//a[@class='sorter-toggle'][@data-icon-before='sort-ascending']")
    private WebElement sortAscending ;

    @FindBy(id = "qa_automation_Name")
    private WebElement filterByName ;

    @FindBy(id = "qa_automation_Date Created")
    private WebElement filterByDateCreated ;

    @FindBy(id = "qa_automation_Date Modified")
    private WebElement filterByDateModified ;

    @FindBy(id = "qa_automation_Last Activated / Deactivated")
    private WebElement filterByLastStatusChange ;

    @FindBy(className = "btn upload-btn")
    private WebElement createRole;

    @FindBys(@FindBy(xpath = "//p[contains(@class,'item-name')]"))
    private List<WebElement> rolesList;

    /* //span[text()='Status']/parent::div */

    @FindBys(@FindBy(xpath="//p[contains(@class,'item-name')]/parent::div/parent::div//span[text()='Status']/parent::div"))
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

    @FindBy(xpath ="//ul[@class='pagination']//a[text()='Next →']")
    private WebElement paginationNext;

    public RolesAndPermissionPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected void instantiatePage(RolesAndPermissionPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return ExpectedConditions.visibilityOf(rolesTable);
    }

    /***********************GET/SET METHODS*********************/

    public WebElement getFilter() {
        return filter;
    }

    public WebElement getSortDescending() {
        return sortDescending;
    }

    public WebElement getSortAscending() {
        return sortAscending;
    }

    public WebElement getFilterByName() {
        return filterByName;
    }

    public WebElement getFilterByDateCreated() {
        return filterByDateCreated;
    }

    public WebElement getFilterByDateModified() {
        return filterByDateModified;
    }

    public WebElement getFilterByLastStatusChange() {
        return filterByLastStatusChange;
    }

    public WebElement getCreateRole() {
        return createRole;
    }

    public List<WebElement> getRolesList() {
        return rolesList;
    }

    public List<WebElement> getStatusList() {
        return statusList;
    }

    public WebElement getLearnerStatus() {
        return learnerStatus;
    }

    public WebElement getLearnerViewRole() {
        return learnerViewRole;
    }

    public List<WebElement> getDeactivateRoleList() {
        return deactivateRoleList;
    }

    public List<WebElement> getEditRoleList() {
        return editRoleList;
    }

    public List<WebElement> getDeleteRoleList() {
        return deleteRoleList;
    }

    public WebElement getCustomEditRole() {
        return customEditRole;
    }

    public WebElement getCustomDeActivateRole() {
        return customDeActivateRole;
    }

    public WebElement getCustomDeleteRole() {
        return customDeleteRole;
    }

    public WebElement getPaginationPrevious() {
        return paginationPrevious;
    }

    public WebElement getPaginationNext() {
        return paginationNext;
    }
}
