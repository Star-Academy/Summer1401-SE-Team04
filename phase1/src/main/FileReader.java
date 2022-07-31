package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class FileReader {


    public HashMap<String, List<String>> readDocs(String directoryPath) throws IOException {
        HashMap<String, List<String>> result = new HashMap<>();
        final File directory = new File(directoryPath);


        for (final File file : Objects.requireNonNull(directory.listFiles())) {
            String fileName = file.getName();
            List<String> words = new ArrayList<>();
            if (!file.isDirectory()) {
                java.io.FileReader fileReader = new java.io.FileReader(file.getPath());
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String text = bufferedReader.readLine();
                if (text == null) {
                    continue;
                }
                text = text.toUpperCase().trim();
                text = text.replaceAll("(\\W+)", " ").trim();

                words = List.of(text.split(" "));


            }
            result.put(fileName, words);
        }
        return result;
    }

}
