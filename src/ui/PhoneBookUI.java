package ui;

import model.PhoneBookEntity;
import service.implementation.PhoneBookServiceImpl;

import java.io.IOException;
import java.util.Scanner;

public class PhoneBookUI {
    private static final Scanner sc = new Scanner(System.in);
    private final PhoneBookServiceImpl phoneBook = new PhoneBookServiceImpl();

    // Main menu for user interaction
    public void menu() throws IOException {
        phoneBook.loadContactsFile();
        boolean exit = false;
        while (!exit) {
            System.out.println("Phone Book Menu:\n1. Add Contact\n2. Search\n3. Edit Contact\n8. loadCon  \n4. Delete Contact\n5. Display All Contact\n6. Count\n7. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: addContact(); break;
                case 2: searchContact(); break;
                case 3: editContact(); break;
                case 4: deleteContact(); break;
                case 5: phoneBook.displayAll(); break;
                case 6: System.out.println("Total Entries: " + phoneBook.countEntries()); break;
                case 7: exit = true; phoneBook.saveToFile(); break;
                case 8: phoneBook.loadContactsFile(); break;
                default: System.out.println("Invalid option!");
            }
        }
    }


    private void addContact() throws IOException {
        System.out.println("First name: ");
        String firstname = sc.next();
        System.out.println("Last name: ");
        String lastname = sc.next();
        System.out.println("Address: ");
        String address = sc.next();
        System.out.println("City: ");
        String city = sc.next();
        System.out.println(" Phone number: ");
        String phoneNumber = sc.next();
        System.out.println("Adding new entry...");

        phoneBook.addEntry(new PhoneBookEntity(firstname, lastname, address, city, phoneNumber));
        phoneBook.saveToFile();
        System.out.println("Contact added!");

        // Get input from user and call `phoneBook.addEntry(...)`
    }

    private void searchContact() throws IOException {
        System.out.println("Search by last name, city, or phone number...");
        String searchParams = sc.nextLine();
        PhoneBookEntity result = (PhoneBookEntity) phoneBook.search(searchParams);
        if (result != null) {
            System.out.println("Contact Found:\n" + result);
        } else {
            System.out.println("results found: " + result);
            System.out.println("No contact found with the provided query.");
        }
    }

    private void editContact() {
        System.out.println("Modify entry...");
        // Implement modify logic here
    }

    private void deleteContact() {
        System.out.println("Delete entry...");
        // Implement delete logic here
    }
}
