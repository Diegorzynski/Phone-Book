package org.phonebook;

import javax.mail.MessagingException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws MessagingException {
        AppProperties.loadProperties();


        PhoneBook.loadPhoneBook();
//        PhoneBook.search("Diego");
//        PhoneBook.addContact("New", "Contact","01234567899");
//        PhoneBook.save();

        EmailSender.sendEmail("recipient@email.com");
    }
}