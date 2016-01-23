package com.tw.cisco.b2b.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created by aswathyn on 22/01/16.
 */
public class EditRolePermissionPopupPage extends BasePage<EditRolePermissionPopupPage> {

    @FindBy(xpath = "//h4[text()='Set Permissions']/parent::div/parent::div")
    private WebElement editPermissionModal;

    @FindBys(@FindBy(xpath="//table[@class='admin-ui']//input"))
    private List<WebElement> permissionList;

    @FindBys(@FindBy(xpath="//h4[text()='Set Permissions']/parent::div/parent::div//input"))
    private List<WebElement> permissionList1;

    @FindBys(@FindBy(xpath="/h4[text()='Set Permissions']/parent::div/parent::div//p"))
    private List<WebElement> permissionDescriptionList;

    @FindBys(@FindBy(xpath="//table[@class='admin-ui']//p"))
    private List<WebElement> permissionDescriptionList1;

    @FindBy(xpath = "//button[contains(text(),'Set Permissions')]")
    private WebElement savePermission;

    @FindBy(xpath = "//h4[text()='Set Permissions']/parent::div/parent::div//button[contains(text(),'Set Permissions')]")
    private WebElement savePermission1;

    @FindBy(xpath = "//button[text()='Cancel']")
    private WebElement cancelPermission ;

    @FindBy(xpath = "//h4[text()='Set Permissions']/parent::div/parent::div//button[contains(text(),'Cancel')]")
    private WebElement cancelPermission1 ;



    public EditRolePermissionPopupPage(WebDriver driver) {
        super(driver);
        waitForPageToLoad(getPageLoadCondition());
    }

    @Override
    protected ExpectedCondition getPageLoadCondition() {
        return null;
    }

    @Override
    protected void instantiatePage(EditRolePermissionPopupPage page) {
        try {
            PageFactory.initElements(driver, page);
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    /***********************GET/SET METHODS*********************/

    public WebElement getEditPermissionModal() {
        return editPermissionModal;
    }

    public List<WebElement> getPermissionList() {
        return permissionList;
    }

    public List<WebElement> getPermissionList1() {
        return permissionList1;
    }

    public List<WebElement> getPermissionDescriptionList() {
        return permissionDescriptionList;
    }

    public List<WebElement> getPermissionDescriptionList1() {
        return permissionDescriptionList1;
    }

    public WebElement getSavePermission() {
        return savePermission;
    }

    public WebElement getSavePermission1() {
        return savePermission1;
    }

    public WebElement getCancelPermission() {
        return cancelPermission;
    }

    public WebElement getCancelPermission1() {
        return cancelPermission1;
    }



}
