package service.implementation;

import model.PhoneBookEntity;
import service.PhoneBookService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PhoneBookServiceImpl implements PhoneBookService {
    private final List<PhoneBookEntity> entries = new ArrayList<>();
    private static final String FILE_PATH = "src/database/phonebook.txt";

    @Override
    public void saveToFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(FILE_PATH);
        if (inputStream != null) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
            for (PhoneBookEntity entry : entries) {
                bw.write(entry.getFirstname() + "," + entry.getLastname() + ","
                        + entry.getAddress() + "," + entry.getCity() + "," + entry.getPhoneNumber());
                bw.newLine();
            }
            bw.close();
        }

    }

    @Override
    public void addEntry(PhoneBookEntity entry) throws IOException {
        if (entries.stream().noneMatch(e -> e.getPhoneNumber().equals(entry.getPhoneNumber())) ) {
            entries.add(entry);

//            this saves the entry to the file.
            saveToFile();
        } else {
            System.out.println("Phone number already exists!");
        }
    }

    @Override
    public void loadContactsFromFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = br.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length == 5) {
                entries.add(new PhoneBookEntity(data[0], data[1], data[2], data[3], data[4]));
            }
        }
        br.close();
    }

    @Override
    public List<PhoneBookEntity> search(String query) throws IOException {
        List<PhoneBookEntity> results = new ArrayList<>();
        for (PhoneBookEntity entry : entries) {
            if (entry.getLastname().equalsIgnoreCase(query) ||
                    entry.getFirstname().equalsIgnoreCase(query) ||
                    entry.getCity().equalsIgnoreCase(query) ||
                    entry.getPhoneNumber().equals(query)) {
                results.add(entry);

            }
        }
//        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
//        String line;
//        int noLine = 0;
//        while ((line = br.readLine()) != null) {
//            String[] data = line.split(",");
//            noLine++;
//            for (String item : data) {
//                if (item.toLowerCase().trim().contains(query.toLowerCase().trim())) {
//                    System.out.println("Contacts found: "+ noLine);
//                    PhoneBookEntity entity = new PhoneBookEntity(
//                            data[0], data[1], data[2], data[3], data[4]
//                    );
//                    System.out.println(entity);
//                }
////                else if (!item.toLowerCase().equals(query.toLowerCase())) {
////                    System.out.println("No contacts found");
////                    break;
////                }
////                break;
//            }
//        }
//        return null;
//        System.out.println("Contacts found: " + results.size());
//        System.out.println(results);
        return results;
    }

    public List<PhoneBookEntity> displayAll() {
        //            System.out.println(entry);
        return new ArrayList<>(entries);
    }

    // Count records
    public String countEntries() {
        return "You have " + entries.size() + " contacts saved.";
    }

    // Modify an entry by phone number
    @Override
    public void editContact(String phoneNumber, PhoneBookEntity newEntry) throws IOException {
        for (int i = 0; i < entries.size(); i++) {
            if (entries.get(i).getPhoneNumber().equals(phoneNumber)) {
                entries.set(i, newEntry);
                saveToFile();
                return;
            }
        }
    }

    // Delete an entry by phone number
    public boolean deleteContact(String query) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
//        String line;
//        List<String> updatedLines = new ArrayList<>();
//        while ((line = br.readLine()) != null) {
//            String[] data = line.split(",");
//
//            for (String item : data) {
//                if (!item.toLowerCase().trim().contains(query.toLowerCase().trim())) {
//                    updatedLines.add(line);
//                    break;
//                }
//                break;
//            }
////            break;
//        }
//        System.out.println(entries);
//        Files.write(Paths.get(FILE_PATH), updatedLines);
//        System.out.println("Contact deleted successfully!");
//        br.close();

       boolean result = entries.removeIf(entry ->
                entry.getPhoneNumber().equals(query)||
                entry.getLastname().equalsIgnoreCase(query)
                || entry.getFirstname().equalsIgnoreCase(query)
        );
        saveToFile();
        return result;
    }


}
