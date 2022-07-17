package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class textSearch {
    public static HashMap<String, List<String>> invertedIndex = new HashMap<String, List<String>>();
    public static String directoryPath = "E:\\java\\folder";

    public static void readDoc() throws IOException {

        final File directory = new File(directoryPath);
        for (final File file : directory.listFiles()) {
            String fileName = file.getName();
            if (!file.isDirectory()) {
                FileReader fileReader = new FileReader(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String text = bufferedReader.readLine().trim().toUpperCase();
                text = text.replaceAll("(\\W+)", " ").trim();

                String[] words = text.split(" ");
                for (String word : words) {
                    addTextToInvertedIndex(word, fileName);
                }

            }
        }



    }
    public static void addTextToInvertedIndex(String word, String fileName) {

    }

    public static void main(String[] args) {

        try {
            readDoc();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
