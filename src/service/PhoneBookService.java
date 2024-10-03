package service;

import model.PhoneBookEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PhoneBookService {
    public void saveToFile() throws IOException;
    public void addEntry(PhoneBookEntity entry) throws IOException;
    public void loadContactsFile() throws IOException;

    public List<PhoneBookEntity> search(String query) throws IOException;
    public void editContact(String phoneNumber, PhoneBookEntity newEntry) throws IOException;
    public boolean deleteContact(String query) throws IOException;

}
