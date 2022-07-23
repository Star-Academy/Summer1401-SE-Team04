package main;


import java.io.IOException;
import java.util.*;

public class Main {


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


        List<String> finalResult = new Search().getFileNamesBySearchWords(searchWords, invertedIndex);

        if (finalResult == null || finalResult.size() == 0) {
            System.out.println("word was not found!");
            return;
        }

        for (String fileName : finalResult) {
            System.out.println(fileName);
        }

    }

}

