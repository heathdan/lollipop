package com.tw.cisco.b2b.helper;

/**
 * Created by aswathyn on 12/03/16.
 */
public class UploadDataDetails {

    private String filePath;
    private UserDetails userData;
    private String fileName;

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public UserDetails getUserData() {
        return userData;
    }

    public void setUserData(UserDetails userData) {
        this.userData = userData;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
