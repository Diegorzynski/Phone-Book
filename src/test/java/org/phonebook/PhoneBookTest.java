package org.phonebook;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;


import static org.junit.Assert.*;

public class PhoneBookTest {

    private static Logger log = LogManager.getLogger(PhoneBookTest.class);

    @Before
    public void loadPhoneBook(){
        PhoneBook.loadPhoneBook();
    }

    @After
    public void clear(){
        PhoneBook.deleteAll();
    }

    @Test
    public void isInstanceOfPhoneEntry(){
        //Given
        PhoneEntry expected = PhoneBook.search("Diego");
        //Then
        assertTrue(expected instanceof PhoneEntry);
    }


    @Test
    public void searchByNameWhenFull(){
        PhoneEntry expected = PhoneBook.search("Diego");

        assertEquals("Gorzynski", expected.getLastName());
        assertEquals("48883548757", expected.getPhoneNumber());

    }

    @Test
    public void searchByNameWhenPartial(){
        PhoneEntry expected = PhoneBook.search("ieg");

        assertEquals("Gorzynski", expected.getLastName());
        assertEquals("48883548757", expected.getPhoneNumber());

    }

    @Test
    public void searchByNumber(){
        PhoneEntry expected = PhoneBook.search("01236956852");

        assertEquals("Styles", expected.getLastName());
        assertEquals("Harry", expected.getName());

    }

    @Test
    public void searchNull(){
        PhoneEntry expected = PhoneBook.search("NonExistingPerson");

        assertNull(expected);
    }

    @Test
    public void searchByLastName(){
        PhoneEntry expected = PhoneBook.search("Styles");

        assertEquals("01236956852", expected.getPhoneNumber());
        assertEquals("Styles", expected.getLastName());

    }

    @Test
    public void addingNewContactToFile() throws IOException {
        //Given
        int linesBefore = countLines();
        //When
        PhoneBook.addContact("New", "Contact","0123456789");
        PhoneBook.save();
        // Then
       int linesAfter = countLines();

        assertEquals(5, linesBefore);
        assertEquals(6, linesAfter);
    }

    @Test
    public void addingNullContact(){
        assertFalse( PhoneBook.addContact(null, null, null));
    }

    @Test
    public void addingEmptyContact(){
        assertFalse( PhoneBook.addContact(" ", " ", " "));
    }


    @Test
    public void deleteByNameFromFile() throws IOException {
       //Given
        PhoneBook.addContact("New", "Contact", "0123456789");
        PhoneBook.save();
        int linesBefore = countLines();
        //when
        System.out.println("Lines before deleting " + linesBefore);
        assertEquals(6, linesBefore);
        PhoneBook.deleteContact("New");
        // then
        PhoneBook.save();
        int linesAfter = countLines();
        System.out.println("Lines after deleting " + linesAfter);
        assertEquals(5, linesAfter);

    }


    private int countLines() throws IOException {
        LineNumberReader lineNumberReader = new LineNumberReader(new FileReader("src/main/resources/phonebook.cvs"));
        lineNumberReader.skip(Long.MAX_VALUE);
        return lineNumberReader.getLineNumber();
    }





}