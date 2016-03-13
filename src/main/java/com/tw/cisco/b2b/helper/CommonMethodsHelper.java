package com.tw.cisco.b2b.helper;

import com.tw.cisco.b2b.exceptions.CSVParsingException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by aswathyn on 02/02/16.
 */
public class CommonMethodsHelper {

    private static Map<String, WebElement>tabs = new HashMap<String,WebElement>();

    public WebElement iterateSuggestionBox(List<WebElement> elements, String searchItem) {
        WebElement retValue=null;
        for (WebElement element : elements) {
            if (searchItem.equalsIgnoreCase(element.getText())) {
                retValue= element;
                break;
            }
        }
        return retValue;
    }

    public boolean validateDropDown(WebElement element,String[] expList) {
        List<WebElement> elements = new Select(element).getOptions();
        boolean finalValue=false;
        for(WebElement e:elements) {
            boolean match = false;
            for(int i=0; i<expList.length; i++) {
                if(e.getText().equals(expList[i])) {
                    match= true;
                }
            }
            finalValue=match;
        }
        return finalValue;
    }

    public static void storeTabs(List<WebElement> elements) {
        for(WebElement element: elements) {
            tabs.put(element.getText(),element);
        }
    }

    public static String timeStamp(String name) {
        String value = name + new SimpleDateFormat("ddMMYYhhmmss").format(new Date());
        return value;
    }

    public static String getCSVDataForUpload(String csvFileName) throws CSVParsingException {
        List<UserDetails> userDetails= null;
        String filePath = null;
        try {
            userDetails=  CSVParser.parseUserCSVToBean(csvFileName);
            filePath=CSVParser.writeToCSV(userDetails,csvFileName);
        } catch (IOException ex) {
           throw new CSVParsingException( "Could not find "+csvFileName);
        }
        return filePath;
    }

    public static List<UserDetails> parseCSVData(String csvFileName) throws CSVParsingException {
        List<UserDetails> userDetails = null;
        try {
            userDetails = CSVParser.parseUserCSVToBean(csvFileName);
        } catch (IOException ex) {
            throw new CSVParsingException("Could not find "+csvFileName);
        }

        return userDetails;
    }
}
