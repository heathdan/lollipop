package com.tw.cisco.b2b.helper;

import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * Created by aswathyn on 02/02/16.
 */
public class CommonMethodsHelper {

    public void iterateSuggestionBox(List<WebElement> elements, String searchItem) {
        for( WebElement element : elements) {
            if(element.getText().equals(searchItem)) {

            }
        }
    }
}
