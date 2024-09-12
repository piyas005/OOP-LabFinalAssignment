package ooplabfinal;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    private static final String APPOINTMENTS_FILE = "appointments.txt";

    // Save appointments to a file
    public static void saveAppointments(ArrayList<Appointment> appointments) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(APPOINTMENTS_FILE))) {
            oos.writeObject(appointments);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load appointments from a file
    public static ArrayList<Appointment> loadAppointments() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(APPOINTMENTS_FILE))) {
            appointments = (ArrayList<Appointment>) ois.readObject();
        } catch (FileNotFoundException e) {
            // No file found, return empty list
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return appointments;
    }

    // Create a folder
    public static File createFolder(String name) {
        try {
            File folder = new File(name);
            if (!folder.exists()) {
                folder.mkdir(); // Use mkdir() to create a single directory
            }
            return folder;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Write content to a file
    public static void writeFile(String filePath, String content, boolean overwrite) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, !overwrite))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Create a folder and write content to a file within it
    public static void createAndWriteFile(String folderName, String fileName, String content, boolean overwrite) {
        try {
            File folder = createFolder(folderName);
            if (folder != null) {
                File file = new File(folder, fileName);
                writeFile(file.getPath(), content, overwrite);
            } else {
                System.out.println("Failed to create folder: " + folderName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
