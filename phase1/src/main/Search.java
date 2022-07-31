package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Search {
    public List<String> getFileNamesBySearchWords(List<String> searchWords,
                                                         HashMap<String, List<String>> invertedIndex) {

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

    private List<String> createFinalList(HashMap<String, List<String>> normalSearchWords, HashMap<String,
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

}
