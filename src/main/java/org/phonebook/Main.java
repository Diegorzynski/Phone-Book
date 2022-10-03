package org.phonebook;

public class Main {
    public static void main(String[] args) {

        PhoneBook.loadPhoneBook();
        PhoneBook.addContact("Harry", "Styles", "01236956852");
        PhoneBook.search("Diego");
        PhoneBook.save();
    }
}