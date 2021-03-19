/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package azamak.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Azamak
 */
public class Config {

    private String property_file;

    public String getProperty_file() {
        return property_file;
    }

    public void setProperty_file(String property_file) {
        this.property_file = property_file;
    }

    public Config(String property_file) {
        this.property_file = property_file;
    }

    public void setProperty(String key, String value) {
        try  {
            Properties props;
            try (InputStream in = Config.class.getResourceAsStream(property_file)) {
                props = new Properties();
                props.load(in);
            }
            OutputStream output = new FileOutputStream(property_file);
            // set the properties value
            props.setProperty(key, value);
            // save properties to project root folder
            props.store(output, null);
//            System.out.println(prop);
            output.close();
        } catch (IOException io) {
        }
    }
    public void setPropertyIn(String key, String value) {
        try  {
            Properties props;
            try (InputStream in = new FileInputStream(property_file)) {
                props = new Properties();
                props.load(in);
            }
            OutputStream output = new FileOutputStream(property_file);
            // set the properties value
            props.setProperty(key, value);
            // save properties to project root folder
            props.store(output, null);
//            System.out.println(prop);
            output.close();
        } catch (IOException io) {
        }
    }

    public String getProperty(String key) {
        try (InputStream input = Config.class.getResourceAsStream(property_file)) {
            Properties prop = new Properties();
            prop.load(new InputStreamReader(input, "UTF-8"));
            return prop.getProperty(key, key);
        } catch (IOException ex) {
        }
        return null;
    }

    public String getPropertyIn(String key) {
        try (InputStream input = new FileInputStream(property_file)) {
            Properties prop = new Properties();
            prop.load(new InputStreamReader(input, "UTF-8"));
            return prop.getProperty(key, key);
        } catch (IOException ex) {
        }
        return null;
    }

    public Map<String, String> loadAll() {

        try {
            Map<String, String> result = new HashMap<>();
            Properties p = new Properties();
            p.load(new InputStreamReader(Config.class.getResourceAsStream(property_file), "UTF-8"));
            p.forEach((Object key, Object value) -> {
                result.put((String) key, (String) value);
//                System.out.println((String) key + " = " + (String) value);
            });
            return result;
        } catch (IOException ex) {
        }
        return null;
    }

    public Map<String, String> loadAllIn() {

        try {
            Map<String, String> result = new HashMap<>();
            Properties p = new Properties();
            p.load(new InputStreamReader(new FileInputStream(property_file), "UTF-8"));
            p.forEach((Object key, Object value) -> {
                result.put((String) key, (String) value);
//                System.out.println((String) key + " = " + (String) value);
            });
            return result;
        } catch (IOException ex) {
        }
        return null;
    }
    
}
