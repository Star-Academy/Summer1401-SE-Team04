package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
        System.out.println("please enter the words");
        String words = scanner.nextLine().toUpperCase();
        List<String> searchWords = List.of(words.split(" "));
        List<String> finalResult = new ArrayList<>();
        HashMap<String,List<String>> positiveSearchWords = new HashMap<>();
        HashMap<String,List<String>> negativeSearchWords = new HashMap<>();
        HashMap<String,List<String>> normalSearchWords = new HashMap<>();
        for (String searchWord : searchWords) {
            if (searchWord.startsWith("+")){
                searchWord = searchWord.substring(1);
                positiveSearchWords.put(searchWord,invertedIndex.get(searchWord));
            }else if(searchWord.startsWith("-")){
                searchWord = searchWord.substring(1);
                negativeSearchWords.put(searchWord,invertedIndex.get(searchWord));
            }else {
                normalSearchWords.put(searchWord,invertedIndex.get(searchWord));
            }
        }
        Map.Entry<String,List<String>> firstNormalSearchWord = normalSearchWords.entrySet().iterator().next();
        if (firstNormalSearchWord.getValue() == null){
            System.out.println("word was not found!");
            return;
        }
        for (String fileName : firstNormalSearchWord.getValue()) {

            boolean flag = true;
            for (List<String> normalList : normalSearchWords.values()) {
                if (normalList == null) {continue;}
                if (!normalList.contains(fileName)) {
                    flag = false;
                    break;
                }
            }
            for (List<String> negativeList : negativeSearchWords.values()) {
                if (negativeList == null) {continue;}
                if (negativeList.contains(fileName)) {
                    flag = false;
                    break;
                }
            }
            boolean positiveFlag = false;
            for (List<String> positiveList : positiveSearchWords.values()) {
                if (positiveList == null) {continue;}
                if (positiveList.contains(fileName)) {
                    positiveFlag = true;
                    break;
                }
            }
            if (!positiveFlag && positiveSearchWords.size() != 0){
                flag = false;
            }
            if (flag){
                finalResult.add(fileName);
            }
        }



        if (finalResult.size() == 0) {
            System.out.println("word was not found!");
            return;
        }

        for (String fileName : finalResult) {
            System.out.println(fileName);
        }

    }
}

