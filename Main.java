package cwiczenie4;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Main {

    public static void main(String args[]) throws Exception {
        printFilesSimple("C:\\");
        printFilesDetails("C:\\");
        printFiles("C:\\", ".txt");
        printTree("C:\\");
    }

    public static void printFilesSimple(String path) {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }
    }

    public static void printFilesDetails(String path) throws Exception {
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        int spaces1 = 60;
        int spaces2 = 30;
        String dirSize = null;
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd mm:ss");
        System.out.println("Nazwa pliku" + addSpacesChar("Nazwa pliku", spaces1) + "DIR/rozmiar [B]" + addSpacesChar("DIR/rozmiar [B]", spaces2) + "Data utworzenia");

        for (File file : listOfFiles) {
            String fileName = file.getName();
            BasicFileAttributes attributes = Files.readAttributes(Paths.get(path), BasicFileAttributes.class);
            if (file.isDirectory()) {
                dirSize = "DIR";
            } else {
                dirSize = String.valueOf(attributes.size());
            }
            String creationDate = df.format(attributes.creationTime().toMillis());
            System.out.println(fileName + addSpacesChar(fileName, spaces1) + dirSize + addSpacesChar(dirSize, spaces2) + creationDate);
        }
    }

    static String addSpacesChar(String itemName, int distanceSize) {
        int value = distanceSize - itemName.length();
        String spacesChars = "";
        while (value != 0) {
            spacesChars += " ";
            value--;
        }
        return spacesChars;
    }

    public static void printFiles(String path, String extensionFilter) {
        File fileName = new File(path);
        FilenameFilter filter = new FilenameFilter() {
            public boolean accept(File directory, String fileName) {
                return fileName.endsWith(extensionFilter);
            }
        };
        printList(fileName.listFiles(filter));
    }

    private static void printList(File[] listOfFiles) {
        for (File file : listOfFiles) {
            System.out.println(file.getName());
        }
    }

    public static void printTree(String path) {
        File fileName = new File(path);
        File[] listOfFiles = fileName.listFiles();
        for (File file : listOfFiles) {
            if (file.isDirectory() && file.listFiles() != null) {
                printTree(file.getPath());
            } else {
                System.out.println(file.getPath());
            }
        }
    }

}
