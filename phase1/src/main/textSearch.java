package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class textSearch {

    public static List<String> getFileNamesBySearchWords(List<String> searchWords,
                                                         HashMap<String, List<String>> invertedIndex) {
        List<String> result = new ArrayList<>();
        HashMap<String, List<String>> positiveSearchWords = new HashMap<>();
        HashMap<String, List<String>> negativeSearchWords = new HashMap<>();
        HashMap<String, List<String>> normalSearchWords = new HashMap<>();
        for (String searchWord : searchWords) {
            if (searchWord.startsWith("+")) {
                searchWord = searchWord.substring(1);
                positiveSearchWords.put(searchWord, invertedIndex.get(searchWord));
            } else if (searchWord.startsWith("-")) {
                searchWord = searchWord.substring(1);
                negativeSearchWords.put(searchWord, invertedIndex.get(searchWord));
            } else {
                normalSearchWords.put(searchWord, invertedIndex.get(searchWord));
            }
        }

        return createFinalList(normalSearchWords,positiveSearchWords,negativeSearchWords);
    }

    public static List<String> createFinalList(HashMap<String, List<String>> normalSearchWords, HashMap<String,
            List<String>> positiveSearchWords, HashMap<String, List<String>> negativeSearchWords) {
        Map.Entry<String, List<String>> firstNormalSearchWord = normalSearchWords.entrySet().iterator().next();
        if (firstNormalSearchWord.getValue() == null) {
            return null;
        }
        List<String> result = new ArrayList<>();
        for (String fileName : firstNormalSearchWord.getValue()) {

            boolean flag = true;
            for (List<String> normalList : normalSearchWords.values()) {
                if (normalList == null) {
                    continue;
                }
                if (!normalList.contains(fileName)) {
                    flag = false;
                    break;
                }
            }
            for (List<String> negativeList : negativeSearchWords.values()) {
                if (negativeList == null) {
                    continue;
                }
                if (negativeList.contains(fileName)) {
                    flag = false;
                    break;
                }
            }
            boolean positiveFlag = false;
            for (List<String> positiveList : positiveSearchWords.values()) {
                if (positiveList == null) {
                    continue;
                }
                if (positiveList.contains(fileName)) {
                    positiveFlag = true;
                    break;
                }
            }
            if (!positiveFlag && positiveSearchWords.size() != 0) {
                flag = false;
            }
            if (flag) {
                result.add(fileName);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> invertedIndex = null;
        try {
            invertedIndex = new InvertedIndex().makeInvertedIndex();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("please enter the words");
        String words = scanner.nextLine().toUpperCase();
        List<String> searchWords = List.of(words.split(" "));


        List<String> finalResult = getFileNamesBySearchWords(searchWords, invertedIndex);

        if (finalResult == null || finalResult.size() == 0) {
            System.out.println("word was not found!");
            return;
        }

        for (String fileName : finalResult) {
            System.out.println(fileName);
        }

    }

}

