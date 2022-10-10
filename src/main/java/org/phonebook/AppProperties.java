package org.phonebook;

import java.io.*;
import java.net.URL;
import java.util.Objects;
import java.util.Properties;

public class AppProperties {
    public static Properties propperties = new Properties();
    public static void loadProperties(){

        try{

            File file = new File("src/main/resources/app.config");

            InputStream is = new FileInputStream(file);

            propperties.load(is);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
