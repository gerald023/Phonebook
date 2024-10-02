package service.implementation;

import model.PhoneBookEntity;
import service.PhoneBookService;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class PhoneBookServiceImpl implements PhoneBookService {
    private final List<PhoneBookEntity> entries = new ArrayList<>();
    private static final String FILE_PATH = "C:/Users/GERALD/Desktop/Phone_Book/" +
            "src/database/phonebook.txt";

    @Override
    public void saveToFile() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
        for (PhoneBookEntity entry : entries) {
            bw.write(entry.getFirstname() + "," + entry.getLastname() + ","
                    + entry.getAddress() + "," + entry.getCity() + "," + entry.getPhoneNumber());
            bw.newLine();
        }
        bw.close();
    }

    @Override
    public void addEntry(PhoneBookEntity entry) throws IOException {
        if (entries.stream().noneMatch(e -> e.getPhoneNumber().equals(entry.getPhoneNumber()))) {
            entries.add(entry);
        } else {
            System.out.println("Phone number already exists!");
        }
    }

    @Override
    public void loadContactsFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
//            if (data.length == 5) {
//                entries.add(new PhoneBookEntity(data[0], data[1], data[2], data[3], data[4]));
//            }
            System.out.println("{" + '\n' +
                    '\t' + "First name: " + data[0] +
                    '\n' + '\t' + "Last name: " + data[1] + '\n' +
                    '\t' + "Address: " + data[2] + ' ' + data[3] +
                    '\n' + '\t' +  "Phone number: " + data[4] + '\n' + "}"
            );
        }
        br.close();
    }

    @Override
    public List<PhoneBookEntity> search(String query) throws IOException {
//        List<PhoneBookEntity> results = new ArrayList<>();
//        for (PhoneBookEntity entry : entries) {
//            if (entry.getLastname().equalsIgnoreCase(query) ||
//                    entry.getCity().equalsIgnoreCase(query) ||
//                    entry.getPhoneNumber().equals(query)) {
//                return (List<PhoneBookEntity>) entry;
//            }
//        }
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            for (String item : data) {
                if (Objects.equals(item, query)) {
                    System.out.println(line);
                }
            }
        }

        return null;
    }

    @Override
    public void displayAll() {
        for (PhoneBookEntity entry : entries) {
            System.out.println(entry);
        }
    }

    // Count records
    public int countEntries() {
        return entries.size();
    }

    // Modify an entry by phone number
    public void editContact(String phoneNumber, PhoneBookEntity newEntry) {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getPhoneNumber().equals(phoneNumber)) {
                entries.set(i, newEntry);
                break;
            }
        }
    }

    // Delete an entry by phone number
    public void deleteEntry(String phoneNumber) {
        entries.removeIf(entry -> entry.getPhoneNumber().equals(phoneNumber));
    }


}
