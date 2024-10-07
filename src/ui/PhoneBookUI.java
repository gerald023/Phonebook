package ui;

import model.PhoneBookEntity;
import service.implementation.PhoneBookServiceImpl;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class PhoneBookUI {
    private static final Scanner sc = new Scanner(System.in);
    private final PhoneBookServiceImpl phoneBook = new PhoneBookServiceImpl();

    // Main menu for user interaction
    public void menu() throws IOException {
        phoneBook.loadContactsFromFile();
        boolean exit = false;
        while (!exit) {
            System.out.println("Phone Book Menu:\n1. Add Contact\n2. Search\n3. Edit Contact \n4. Delete Contact\n5. Display All Contact\n6. Count\n7. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1: addContact(); break;
                case 2: searchContact(); break;
                case 3: editContact(); break;
                case 4: deleteContact(); break;
                case 5: displayALlContact(); break;
                case 6: System.out.println("Total Entries: " + phoneBook.countEntries()); break;
                case 7: exit = true; phoneBook.saveToFile(); break;
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
//        phoneBook.saveToFile();
        System.out.println("Contact added!");

        // Get input from user and call `phoneBook.addEntry(...)`
    }

    private void searchContact() throws IOException {
        System.out.println("Search by last name, city, or phone number...");
        String searchParams = sc.nextLine();
        List<PhoneBookEntity> results = phoneBook.search(searchParams);
        System.out.println("Contacts found: " + results.size());
        System.out.println(results);
//        if (result != null) {
//            System.out.println("Contact Found:\n" + result);
//        } else {
//            System.out.println("results found: " + result);
//            System.out.println("No contact found with the provided query.");
//        }
    }

    private void editContact() throws IOException {
        System.out.println("Enter Phone number you wish to edit: ");

        String phoneNumber = sc.next();
        PhoneBookEntity existingEntry = (PhoneBookEntity) phoneBook.search(phoneNumber).getFirst();

        if (existingEntry != null) {
            System.out.println("Contact found:\n" + existingEntry);

            // Prompt the user for new details
            System.out.println("Enter new first name (Leave blank to keep current): ");
            String newFirstName = sc.nextLine();
            if (newFirstName.isEmpty()) newFirstName = existingEntry.getFirstname();

            System.out.println("Enter new last name (Leave blank to keep current): ");
            String newLastName = sc.nextLine();
            if (newLastName.isEmpty()) newLastName = existingEntry.getLastname();

            System.out.println("Enter new address (Leave blank to keep current): ");
            String newAddress = sc.nextLine();
            if (newAddress.isEmpty()) newAddress = existingEntry.getAddress();

            System.out.println("Enter new city (Leave blank to keep current): ");
            String newCity = sc.nextLine();
            if (newCity.isEmpty()) newCity = existingEntry.getCity();

            // Ensure the new phone number is unique or keep the existing one
            System.out.println("Enter new phone number (Leave blank to keep current): ");
            String newPhoneNumber = sc.nextLine();

//            System.out.println(!phoneBook.search(newPhoneNumber).isEmpty());
//            System.out.println(!newPhoneNumber.equals(existingEntry.getPhoneNumber()));
            if (newPhoneNumber.isEmpty()) newPhoneNumber = existingEntry.getPhoneNumber();
            else if (!newPhoneNumber.equals(existingEntry.getPhoneNumber()) && !phoneBook.search(newPhoneNumber).isEmpty()) {
                System.out.println("This phone number already exists for another contact!");
                return; // Stop the modification if the new phone number is not unique
            }

            // Create a new entry with the updated details
            PhoneBookEntity updatedEntry = new PhoneBookEntity(newFirstName, newLastName, newAddress, newCity, newPhoneNumber);

            // Modify the contact in the phone book
            phoneBook.editContact(phoneNumber, updatedEntry);
            System.out.println("Contact successfully updated.");
        } else {
            System.out.println("No contact found with the provided phone number.");
        }
    }


    private void deleteContact() throws IOException {
        System.out.println("Delete contact by name or phone number: ");
        // Implement delete logic here
        String phoneNumber = sc.nextLine();
        Boolean result = phoneBook.deleteContact(phoneNumber);
        if (result == true) {
            System.out.println("Contact successfully deleted!");
        }else System.out.println("Contact not found- (-_-)");
    }

    private void displayALlContact()throws IOException{
        List<PhoneBookEntity> newArr = phoneBook.displayAll();
        System.out.println(newArr);
    }
}
