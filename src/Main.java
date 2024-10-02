import ui.PhoneBookUI;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        PhoneBookUI ui = new PhoneBookUI();
        ui.menu();
    }
}