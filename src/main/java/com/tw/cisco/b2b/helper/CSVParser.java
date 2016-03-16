package com.tw.cisco.b2b.helper;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.util.*;

/**
 * Created by aswathyn on 11/03/16.
 */
public class CSVParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(CSVParser.class);

    protected static List<UserDetails> parseUserCSVToBean(String fileName,Boolean value) throws IOException {
        LOGGER.trace(">> parseUserCSVToBean()");
        File csvFile = null;
        HeaderColumnNameTranslateMappingStrategy<UserDetails> beanStrategy = new HeaderColumnNameTranslateMappingStrategy<UserDetails>();
        String file = fileName+".csv";
        LOGGER.info("-- Reading CSV file :"+ file);
        if(value) {
            csvFile = new File(file);
        } else {
            csvFile = new File(Classpath.filePathFor("CSVFiles/"+file));
        }

        beanStrategy.setType(UserDetails.class);
        Map<String,String> columnMapping = new HashMap<String,String>();
        columnMapping.put("Username","uname");
        columnMapping.put("First Name","fname");
        columnMapping.put("Last Name","lname");
        columnMapping.put("Address","address");
        columnMapping.put("City","city");
        columnMapping.put("Country Code","code");
        columnMapping.put("Title","title");
        columnMapping.put("Organization", "org");
        columnMapping.put("Manager", "manager");
        columnMapping.put("Active", "active");

        beanStrategy.setColumnMapping(columnMapping);
        CsvToBean<UserDetails> csvToBean = new CsvToBean<UserDetails>();
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        List<UserDetails> usersFromCSV= csvToBean.parse(beanStrategy,reader);
        LOGGER.trace("<< parseUserCSVToBean()");
        return usersFromCSV;
    }

    protected static String writeToCSV(List<UserDetails> users,String fileName) throws IOException {
        LOGGER.trace(">> writeToCSV()");
        String file = fileName+".csv";
        File csvFile = new File(file);
        CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFile), CSVWriter.DEFAULT_SEPARATOR, CSVWriter.NO_QUOTE_CHARACTER, CSVWriter.DEFAULT_ESCAPE_CHARACTER);
        List<String[]> data = toStringArray(users, file);

        csvWriter.writeAll(data);
        csvWriter.close();
        LOGGER.trace("<< writeToCSV()");
        return csvFile.getAbsolutePath();
    }


    private static List<String[]> toStringArray(List<UserDetails> users, String fileName){
        LOGGER.trace(">> toStringArray()");
        List<String[]> records = new ArrayList<>();
        String[] header = new String[]{"Username,First Name,Last Name,Address,City,Country Code,Title,Organization,Manager,Active"};
        records.add(header);

        Iterator<UserDetails> iterator = users.iterator();
        while (iterator.hasNext()) {
            UserDetails user = iterator.next();
            if(fileName.equals("no_manager_assigned.csv")) {
                user.setUname(CommonMethodsHelper.timeStamp(user.getFname()) + "@mailinator.com");
                user.setManager(user.getUname());

            } else if(fileName.equals("upload_users.csv")) {
                user.setUname(CommonMethodsHelper.timeStamp(user.getFname()) + "@mailinator.com");

            } else {
                user.setUname(CommonMethodsHelper.timeStamp(user.getFname()) + "@mailinator.com");
                user.setOrg(CommonMethodsHelper.timeStamp(user.getOrg()));
                user.setTitle(CommonMethodsHelper.timeStamp(user.getTitle()));
            }
            records.add(new String[]{user.getUname(), user.getFname(), user.getLname(), user.getAddress(), user.getCity(), user.getCode(),
                    user.getTitle(), user.getOrg(), user.getManager(), user.getActive()});
        }
        LOGGER.trace("<< toStringArray()");
        return records;
    }


}
