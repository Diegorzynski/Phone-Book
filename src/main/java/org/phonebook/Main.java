package org.phonebook;

public class Main {
    public static void main(String[] args) {

        PhoneBook.loadPhoneBook();
        PhoneBook.search("Diego");
        PhoneBook.addContact("New", "Contact","01234567899");
        PhoneBook.save();
    }
}