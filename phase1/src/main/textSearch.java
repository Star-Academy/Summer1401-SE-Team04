package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class textSearch {

    public static HashMap<String, List<String>> invertedIndex = new HashMap<String, List<String>>();
    public static String directoryPath = new File("").getAbsolutePath() + "\\phase1\\database";

    public static void readDoc() throws IOException {

        final File directory = new File(directoryPath);
        for (final File file : directory.listFiles()) {
            String fileName = file.getName();
            if (!file.isDirectory()) {
                FileReader fileReader = new FileReader(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String text = bufferedReader.readLine();
                if (text == null) {
                    continue;
                }
                text = text.toUpperCase().trim();
                text = text.replaceAll("(\\W+)", " ").trim();

                String[] words = text.split(" ");
                for (String word : words) {
                    addTextToInvertedIndex(word, fileName);
                }

            }
        }
    }

    public static void addTextToInvertedIndex(String word, String fileName) {

        List<String> files = invertedIndex.get(word);
        if (files == null) {
            List<String> fileNames = new ArrayList<>();
            fileNames.add(fileName);
            invertedIndex.put(word, fileNames);
            return;
        } else {
            for (String file : files) {
                if (file.equals(fileName)) {
                    return;
                }

            }
        }
        files.add(fileName);


    }

    public static void main(String[] args) {
        try {
            readDoc();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the word");
        String word = scanner.nextLine().toUpperCase();
        List<String> fileNames = invertedIndex.get(word);
        if (fileNames == null) {
            System.out.println("word was not found!");
            return;
        }

        for (String fileName : fileNames) {
            System.out.println(fileName);
        }
    }
}

