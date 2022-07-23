package main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvertedIndex {
    private final String directoryPath = new File("").getAbsolutePath() + "\\phase1\\database";
    private final HashMap<String, List<String>> invertedIndex = new HashMap<>();


    private void addWordToInvertedIndex(String word, String fileName) {

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

    public HashMap<String, List<String>> makeInvertedIndex() throws IOException {
        HashMap<String, List<String>> wordsInFiles = new FileReader().readDocs(directoryPath);
        for (String fileName : wordsInFiles.keySet()) {
            List<String> words = wordsInFiles.get(fileName);
            for (String word : words) {
                addWordToInvertedIndex(word, fileName);
            }
        }

        return invertedIndex;
    }
}
