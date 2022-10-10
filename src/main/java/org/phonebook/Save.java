package org.phonebook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class Save {

    private static Logger log = LogManager.getLogger(Save.class);
    private static final String FILE_PATH = AppProperties.propperties.getProperty("file.path");
    private static final String HEADER = "Name, Last Name, Phone Number";
    private static final String NEW_LINE = "\n";
    private static final String COMMA =  ",";

    public static void save(Set<PhoneEntry> entries) {

        File file = new File(FILE_PATH);
        try (FileWriter writer = new FileWriter(file)
        ) {
            writer.append(HEADER);
            writer.append(NEW_LINE);
            for(PhoneEntry entry : entries){
                writer.write(entry.getName());
                writer.write(COMMA);
                writer.write(entry.getLastName());
                writer.write(COMMA);
                writer.write(entry.getPhoneNumber());
                writer.append(NEW_LINE);
            }
            log.trace("All data is saved");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
