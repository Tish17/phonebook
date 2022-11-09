import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Path FILE_PATH = Path.of("./resources/contacts.txt");

    public static void main(String[] args) throws IOException {
        System.out.print("Welcome to awesome phonebook!" +
                "\nYou can manage your contacts here!" +
                "\nList of commands:" +
                "\n'add firstName lastName phone type(Рабочий/Семья/Друзья)' for save contact" +
                "\n'delete phone' for remove contact" +
                "\n'all' for show all contacts" +
                "\n'find firstName/lastName' for search contact" +
                "\n'quit' for exit from application" +
                "\nEnter command: ");
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        while (!command.equals("quit")) {
            if (command.startsWith("add")) {
                addContact(command);
            } else if (command.startsWith("delete")) {
                deleteContact(command);
            } else if (command.startsWith("all")) {
                showAllContacts();
            } else if (command.startsWith("find")) {
                findContact(command);
            } else {
                System.out.println("Wrong command! Please try again!");
            }
            System.out.println();
            System.out.print("Enter command: ");
            command = scanner.nextLine();
        }
        System.out.println("Good bye!");
    }

    private static void addContact(String command) throws IOException {
        Files.writeString(FILE_PATH, command.substring(command.indexOf(" ") + 1) + "\n", StandardOpenOption.APPEND);
        System.out.println("Contact is successfully saved!");
    }

    private static void deleteContact(String command) throws IOException {
        List<String> contacts = Files.readAllLines(FILE_PATH);
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).contains(command.substring(command.indexOf(" ") + 1))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Contact is not found!");
        } else {
            contacts.remove(index);
            Files.write(FILE_PATH, contacts);
            System.out.println("Contact is successfully removed!");
        }
    }

    private static void showAllContacts() throws IOException {
        System.out.println("All contacts:");
        List<String> contacts = Files.readAllLines(FILE_PATH);
        for (String contact : contacts) {
            System.out.println(contact);
        }
    }

    private static void findContact(String command) throws IOException {
        List<String> contacts = Files.readAllLines(FILE_PATH);
        int index = -1;
        for (int i = 0; i < contacts.size(); i++) {
            if (contacts.get(i).contains(command.substring(command.indexOf(" ") + 1))) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            System.out.println("Contact is not found!");
        } else {
            System.out.println(contacts.get(index));
        }
    }
}