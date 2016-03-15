package com.tw.cisco.b2b.helper;

import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;

/**
 * Created by aswathyn on 14/03/16.
 */
public class Classpath {
    public Classpath() {
    }

    public static String readAsString(String resourceName) {
        try {
            return IOUtils.toString(Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName), "UTF-8");
        } catch (IOException var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public static File readAsFile(String resourceName) {
        return new File(Thread.currentThread().getContextClassLoader().getResource(resourceName).getPath());
    }

    public static String filePathFor(String resourceName) {
        return readAsFile(resourceName).getAbsolutePath();
    }

    public static String directoryPathFor(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResource(resourceName).getPath();
    }
}
