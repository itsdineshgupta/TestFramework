package com.gradeup.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 
 * @author dgupta54
 *
 */
public class PropertyReader {

    private static PropertyReader instance = null;

    Properties properties = new Properties();
    InputStream inputStream = null;

    /**
     * Constructor
     * 
     * @param filePath
     * @throws IOException
     */
    private PropertyReader(String filePath) throws IOException {
        loadProperties(filePath);
    }

    /**
     * Return the PropertyReader class instance
     * 
     * @return
     */
    public static PropertyReader getInstance() {
        if (instance == null) {
            try {
                instance = new PropertyReader(Constants.PROPERTYPATH);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        return instance;
    }

    /**
     * Load all the properties from Properties file
     * 
     * @param filePath
     * @throws IOException
     */
    private void loadProperties(String filePath) throws IOException {
        try {
            inputStream = new FileInputStream(filePath);
            properties.load(inputStream);
        } catch (IOException e) {
            throw e;
        }
    }

    /**
     * Read property from Properties file
     * 
     * @param key
     * @return
     */
    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
