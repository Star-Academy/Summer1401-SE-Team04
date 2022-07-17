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



    public static void addTextToInvertedIndex(String word, String fileName) {
        try {
            List<String> files = invertedIndex.get(word);
            if (files == null) {
                List<String> f = new ArrayList<>();
                f.add(fileName);
                invertedIndex.put(word, f);
                return;
            } else {
                for (String f : files) {
                    if (f.equals(fileName)) {
                        return;
                    }

                }
            }
            files.add(fileName);

        } catch (Exception ignored) {

        }
    }

    public static void main(String[] args) {
        //readDoc();
        addTextToInvertedIndex("tt", "abc");
        addTextToInvertedIndex("tt", "abcd");
        addTextToInvertedIndex("bb", "qwerty");
        Scanner sc = new Scanner(System.in);
        System.out.println("please enter the word");
        String word = sc.nextLine();
        List<String> f = invertedIndex.get(word);
        if (f == null) {
            System.out.println("word was not found!!");
            return;
        }

        for (String s : f) {
            System.out.println(s);
        }
    }
}
