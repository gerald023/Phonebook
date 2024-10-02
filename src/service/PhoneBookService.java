package service;

import model.PhoneBookEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface PhoneBookService {
    public void saveToFile() throws IOException;
    public void addEntry(PhoneBookEntity entry) throws IOException;
    public void loadContactsFile() throws IOException;

    public List<PhoneBookEntity> search(String lastName
//                                        String city, String phoneNumber
    ) throws IOException;
    public void displayAll();
}
