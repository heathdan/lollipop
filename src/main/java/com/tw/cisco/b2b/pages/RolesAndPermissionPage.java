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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aswathyn on 22/01/16.
 */
public class RolesAndPermissionPage extends BasePage<RolesAndPermissionPage> {

    private HeaderNav headerNav;
    private LeftNav leftNav;
    private TabbedNav tabNav;

    @FindBy(className = "admin-table-content")
    private WebElement rolesTable;

    @FindBy(className = "icon-caret-down")
    private WebElement filter;

    @FindBy(xpath = ".//a[@class='sorter-toggle'][@data-icon-before='sort-descending']")
    private WebElement sortDescending;

    @FindBy(xpath = ".//a[@class='sorter-toggle'][@data-icon-before='sort-ascending']")
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

    @FindBys(@FindBy(xpath = ".//p[contains(@class,'item-name')]"))
    private List<WebElement> rolesList;

    @FindBys(@FindBy(xpath = ".//p[contains(@class,'item-name')]/parent::div/parent::div//span[text()='Status']/parent::div"))
    private List<WebElement> statusList;

    @FindBy(xpath = ".//p[contains(@class,'item-name') and text()='Learner']/parent::div/parent::div//span[text()='Status']/parent::div")
    private WebElement learnerStatus;

    @FindBy(xpath = ".//p[contains(@class,'item-name') and text()='Learner']/parent::div/parent::div//span[text()='Actions']/parent::div//button")
    private WebElement learnerViewRole;

    @FindBys(@FindBy(xpath = ".//button[@data-original-title='Deactivate Role']"))
    private List<WebElement> deactivateRoleList;

    @FindBys(@FindBy(xpath = ".//[@data-original-title='Edit Permissions']"))
    private List<WebElement> editRoleList;

    @FindBys(@FindBy(xpath = ".//[@data-original-title= 'Delete Role']"))
    private List<WebElement> deleteRoleList;

    @FindBy(xpath = ".//p[text()='PLP Manager']/parent::div/parent::div//button[@data-original-title='Edit Permissions']")
    private WebElement customEditRole;

    @FindBy(xpath = ".//p[text()='PLP Manager']/parent::div/parent::div//button[@data-original-title='Deactivate Role']")
    private WebElement customDeActivateRole;

    @FindBy(xpath = ".//p[text()='CustomSystemAdminRole']/parent::div/parent::div//button[@data-original-title='Delete Role']")
    private WebElement customDeleteRole;

    @FindBy(xpath = ".//ul[@class='pagination']/li/a[text()='← Previous']")
    private WebElement paginationPrevious;

    @FindBy(xpath = ".//ul[@class='pagination']//a[text()='Next →']")
    private WebElement paginationNext;

    @FindBy(xpath=".//ul[@class='pagination']//a[text()='Next →']/parent::li")
    private WebElement paginationNextDisabled;

    @FindBy(xpath=".//p[text()='CustomSystemAdminRole']")
    private WebElement customSystemAdminRole;

    @FindBy(xpath=".//div[@class='admin-table-content']//p[@class='item-name eclipse-text']")
    private List<WebElement> roleNames;

    @FindBy(xpath=".//p[text()='Selected Role has been deleted successfully.']")
    private WebElement roleDeletionSuccessPopupMessage;

    @FindBy(xpath=".//div[@id='confirmation']//h4[text()='Delete Roles']")
    private WebElement deleteRolePopupHeader;

    @FindBy(xpath=".//button[@id='confirmButton']")
    private  WebElement deleteRoleConfirm;

    private static int count,counter =0;
    public static List<String> currentElements=new ArrayList<String>();
    public static List<String> prevElements=new ArrayList<String>();
    private boolean isMatchNotFound=true;

    public RolesAndPermissionPage(WebDriver driver) {
        super(driver);
        instantiatePage(this);
        //waitForPageToLoad(getPageLoadCondition());
        tabNav = new TabbedNav(driver);
        headerNav = new HeaderNav(driver);
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

   /* public RolesAndPermissionPage searchAndDeleteRole(String roleName) {
        System.out.println("counter:" + ++counter);

        for(WebElement element: roleNames) {
            System.out.println(++count);
            currentElements.add(element.getText());
            if (roleName.equals(element.getText())) {
                customDeleteRole.click();
                waitForElement(ExpectedConditions.visibilityOf(deleteRolePopupHeader));
                deleteRoleConfirm.click();
                new RolesAndPermissionPage(driver).getHeaderNav().waitForSpinnerToStop();
                waitForElement(ExpectedConditions.visibilityOf(roleDeletionSuccessPopupMessage));
                isMatchNotFound = false;
                break;
            }
        }
        if(isMatchNotFound) {
            paginationNext.click();
            headerNav.waitForSpinnerToStop();
            if (!prevElements.equals(currentElements)) {
                System.out.println("equals");
                //System.out.println("not the same" + prevElements + " : " + currentElements);
                prevElements = new ArrayList<String>();
                prevElements.addAll(currentElements);
                currentElements = new ArrayList<String>();
                new RolesAndPermissionPage(driver).searchAndDeleteRole(roleName);
            }
        }
        return this;
    }*/

    public RolesAndPermissionPage findRoleAndDelete(String roleName) {
        for(WebElement element: roleNames) {
            if (roleName.equals(element.getText())) {
                customDeleteRole.click();
                waitForElement(ExpectedConditions.visibilityOf(deleteRolePopupHeader));
                deleteRoleConfirm.click();
                new RolesAndPermissionPage(driver).getHeaderNav().waitForSpinnerToStop();
                waitForElement(ExpectedConditions.visibilityOf(roleDeletionSuccessPopupMessage));
                isMatchNotFound=false;
                break;
            }
        }
        if(isMatchNotFound && !("disabled".equals(paginationNextDisabled.getAttribute("class")))) {
            paginationNext.click();
            headerNav.waitForSpinnerToStop();
            new RolesAndPermissionPage(driver).findRoleAndDelete(roleName);
        }
        return this;
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
