package com.tw.cisco.b2b.helper;

import org.openqa.selenium.WebElement;

/**
 * Created by aswathyn on 28/02/16.
 */
public class DriverHelper {

    public void type(WebElement webElement, String message) {
        String msg;
        try {
            webElement.clear();
            webElement.sendKeys(message);
        } catch (Exception e) {

        }

    }
}
