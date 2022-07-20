package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class InvertedIndex {

    private final HashMap<String, List<String>> invertedIndex = new HashMap<>();
    private final String directoryPath = new File("").getAbsolutePath() + "\\phase1\\database";

    private void readDoc() throws IOException {

        final File directory = new File(directoryPath);


        for (final File file : Objects.requireNonNull(directory.listFiles())) {
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

    private void addTextToInvertedIndex(String word, String fileName) {

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

    public HashMap<String, List<String>>  makeInvertedIndex() throws IOException {
        readDoc();
        return invertedIndex;
    }
}
