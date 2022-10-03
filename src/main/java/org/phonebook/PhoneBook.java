package org.phonebook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

public class PhoneBook {

    private static Logger log = LogManager.getLogger(PhoneBook.class);
    private static final Set<PhoneEntry>phoneEntries = new HashSet<>();


    public static void addContact(String name, String lastName, String number){
        if(Stream.of(name, lastName, number).anyMatch(Objects::isNull)){
            log.warn("Name or number cannot be null");
            return;
        }if(Stream.of(name, lastName, number).anyMatch(String::isEmpty)){
            log.warn("Name or number cannot be empty");
            return;
        }
        phoneEntries.add(new PhoneEntry(name, lastName, number));
    }

    public static void deleteContact(String entryInfo){
        PhoneEntry temp = search(entryInfo);
        phoneEntries.remove(temp);
        log.trace("{} {} has been removed", temp.getName(), temp.getLastName());
    }

    public static void deleteAll(){
        phoneEntries.clear();
    }


    public static PhoneEntry search(String entryInfo) {

        for (PhoneEntry entry : phoneEntries) {
            if (entry.getName().contains(entryInfo)) {
                print(entry);
                return entry;
            }
             else if (entry.getLastName().contains(entryInfo)) {
                print(entry);
                return entry;
            }
            else if(entry.getPhoneNumber().contains(entryInfo)){
                print(entry);
                return entry;
            }
        }
        log.trace("Not Found");
        return null;
    }

    public static void save() {
        Save.save(phoneEntries);
    }

    public static void loadPhoneBook(){
        LoadEntries.load(phoneEntries);
    }

    private static PhoneEntry print(PhoneEntry entry) {
        numberFormatter(entry.getPhoneNumber());
        String tempNumber = numberFormatter(entry.getPhoneNumber());
        log.trace("found: {} {} - phone number: {}", entry.getName(), entry.getLastName(), tempNumber);
        return entry;
    }

    private static String numberFormatter(String phoneNumber) {
        return phoneNumber.replaceFirst("(\\d{2})(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3-$4");
    }
}
