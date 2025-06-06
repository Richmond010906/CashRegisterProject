package java_file;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class File_Handling {
    private static final String LOG_FILE = "logs.txt";
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("File Handling Application");
        
        String name = promptForName();
        if (name.equalsIgnoreCase("exit")) {
            System.exit(0);
        }
        
        System.out.println("Hello " + name);
        
        while (true) {
            displayMenu();
            String choice = scanner.nextLine().trim();
            
            switch (choice) {
                case "1":
                    writeToFile();
                    break;
                case "2":
                    readFromFile();
                    break;
                case "3":
                    deleteFile();
                    break;
                case "4":
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    private static String promptForName() {
        System.out.print("Enter a name (or 'exit' to quit): ");
        return scanner.nextLine().trim();
    }
    
    private static void displayMenu() {
        System.out.println("\nEnter 1 to write to a file, 2 to read from a file, 3 to delete a file, or 4 to exit:");
    }
    
    private static void writeToFile() {
        System.out.print("Enter text to write to the log file: ");
        String entry = scanner.nextLine();
        
        try (FileWriter writer = new FileWriter(LOG_FILE, true);
             BufferedWriter bufferedWriter = new BufferedWriter(writer)) {
            
            bufferedWriter.write("Entry to the logs");
            bufferedWriter.newLine();
            bufferedWriter.write(entry);
            bufferedWriter.newLine();
            
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    
    private static void readFromFile() {
        try {
            if (!Files.exists(Paths.get(LOG_FILE))) {
                System.out.println("Log file does not exist yet.");
                return;
            }
            
            System.out.println("File contents:");
            Files.lines(Paths.get(LOG_FILE)).forEach(System.out::println);
            
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }
    
    private static void deleteFile() {
        try {
            if (!Files.exists(Paths.get(LOG_FILE))) {
                System.out.println("Log file does not exist yet.");
                return;
            }
            
            Files.delete(Paths.get(LOG_FILE));
            System.out.println("File deleted successfully.");
            
        } catch (IOException e) {
            System.out.println("An error occurred while deleting the file: " + e.getMessage());
        }
    }
}