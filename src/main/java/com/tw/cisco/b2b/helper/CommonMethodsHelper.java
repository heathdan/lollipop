package com.tw.cisco.b2b.helper;

import com.tw.cisco.b2b.exceptions.CSVParsingException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by aswathyn on 02/02/16.
 */
public class CommonMethodsHelper {
    String result = "";
    InputStream inputStream;
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
            throw new CSVParsingException("Could not find " + csvFileName);
        }
        return userDetails;
    }

    public String getPropValue(String env, String item) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "environment.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty(env + "." + item);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    public String getPropValue(String item) throws IOException {

        try {
            Properties prop = new Properties();
            String propFileName = "environment.properties";

            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

            result = prop.getProperty(System.getProperty("environment") + "." + item);
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            inputStream.close();
        }
        return result;
    }

    public List<String> getUserList() {

        List<String> Users = null;
        try {
            String splitUser = getPropValue("users");
            String[] items = splitUser.split(",");
            Users = Arrays.asList(items);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Users;
    }

    public List<Map<String, String>> users() throws IOException {
        List<Map<String,String>> users = new ArrayList<Map<String,String>>();
        for(String user : getUserList() ) {
            HashMap<String, String> userInfo = new HashMap<String, String>();
            userInfo.put("username", user.split("\\|")[0]);
            userInfo.put("email", user.split("\\|")[0]);
            userInfo.put("password", user.split("\\|")[1]);
            userInfo.put("First Name", user.split("\\|")[2]);
            userInfo.put("Last Name", user.split("\\|")[3]);
            users.add(userInfo);
        }
        return users;
    }
}


