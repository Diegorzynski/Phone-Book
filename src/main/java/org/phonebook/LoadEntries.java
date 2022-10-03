package org.phonebook;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

public class LoadEntries {

    private static Logger log = LogManager.getLogger(PhoneBook.class);

    private static final String FILE_PATH = "src/main/resources/phonebook.cvs";


    public static void load(Set<PhoneEntry> phoneEntries){
        try(FileReader fileReader = new FileReader(FILE_PATH);
            CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(1).build()
        ) {
                csvReader.readAll().forEach(entry ->
                        PhoneBook.addContact(entry[0],entry[1],entry[2]));
                log.trace("Phone Book Loaded");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
