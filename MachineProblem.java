package machineproblem;

import java.io.*;
import java.util.Scanner;

public class MachineProblem {
    static final String FOLDER_PATH = "files";
    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        // Ensure the folder exists
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdir();
        }

        while (true) {
            System.out.println("\n=== File Manager Menu ===");
            System.out.println("1. Create a File");
            System.out.println("2. Write to a File");
            System.out.println("3. Read a File");
            System.out.println("4. Delete a File");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = scan.nextLine().trim();

            switch (choice) {
                case "1":
                    createFile();
                    break;
                case "2":
                    writeToFile();
                    break;
                case "3":
                    readFile();
                    break;
                case "4":
                    deleteFile();
                    break;
                case "5":
                    System.out.println("Thank you for using the app!");
                    return;
                default:
                    System.out.println("Invalid choice. Please select from 1 to 5.");
            }
        }
    }

    public static void createFile() {
        System.out.print("Enter file name (without .txt): ");
        String fileName = scan.nextLine().trim() + ".txt";
        File file = new File(FOLDER_PATH + "/" + fileName);

        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public static void writeToFile() {
        showFiles();
        System.out.print("Enter the file name to write to (without .txt): ");
        String fileName = scan.nextLine().trim() + ".txt";
        File file = new File(FOLDER_PATH + "/" + fileName);

        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            while (true) {
                System.out.print("Enter text (or 'exit' to stop): ");
                String input = scan.nextLine();
                if (input.equalsIgnoreCase("exit")) break;
                writer.write(input);
                writer.newLine();
            }
            System.out.println("Write successful.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void readFile() {
        showFiles();
        System.out.print("Enter the file name to read (without .txt): ");
        String fileName = scan.nextLine().trim() + ".txt";
        File file = new File(FOLDER_PATH + "/" + fileName);

        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            System.out.println("\n--- File Contents ---");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            System.out.println("Read successful.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public static void deleteFile() {
        showFiles();
        System.out.print("Enter the file name to delete (without .txt): ");
        String fileName = scan.nextLine().trim() + ".txt";
        File file = new File(FOLDER_PATH + "/" + fileName);

        if (file.delete()) {
            System.out.println("File deleted: " + file.getName());
        } else {
            System.out.println("File not found or could not be deleted.");
        }
    }

    public static void showFiles() {
        File folder = new File(FOLDER_PATH);
        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        System.out.println("\n--- Existing Files ---");
        if (files == null || files.length == 0) {
            System.out.println("No files found.");
        } else {
            for (File f : files) {
                System.out.println("- " + f.getName().replace(".txt", ""));
            }
        }
    }
}
