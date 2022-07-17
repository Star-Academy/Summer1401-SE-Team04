import java.util.*;

public class textSearch {
    public static HashMap<String, List<String>> invertedIndex = new HashMap<>();
    public String directory = "";

    public static void readDoc() {
        try {
            String word = null, fileName = null;


            addTextToInvertedIndex(word, fileName);
        } catch (Exception ignored) {

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
