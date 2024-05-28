import javax.swing.JFileChooser;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Project_4 {
    public static void main(String[] args) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            String filename = fileChooser.getSelectedFile().getPath();
            // Read and process the file
            String[] words = inputFromFile(filename);
            // Count word frequencies
            TreeMap<String, Integer> wordCounts = countWordFrequencies(words);
            // Print the results
            printWordFrequencies(wordCounts);
        } else {
            System.out.println("No file was selected.");
        }
    }

    public static String[] inputFromFile(String filename) {
        TextFileInput input = new TextFileInput(filename);
        String[] newList = new String[100]; // Adjust size as needed or use dynamic data structure
        int i = 0;
        String line = input.readLine();
        while (line != null) {
            StringTokenizer myTokens = new StringTokenizer(line, " ,.;!?()-\"\'\n\r\t[]{}<>"); // Tokenize with broader delimiters
            while (myTokens.hasMoreTokens()) {
                String word = myTokens.nextToken().toLowerCase(); // Normalize to lowercase
                if (i >= newList.length) {
                    // Resize array if more space is needed
                    String[] temp = new String[newList.length * 2];
                    System.arraycopy(newList, 0, temp, 0, newList.length);
                    newList = temp;
                }
                newList[i++] = word;
            }
            line = input.readLine();
        }
        input.close();
        // Adjust the final array size to match the actual number of words
        String[] finalList = new String[i];
        System.arraycopy(newList, 0, finalList, 0, i);
        return finalList;
    }

    public static TreeMap<String, Integer> countWordFrequencies(String[] words) {
        TreeMap<String, Integer> wordCounts = new TreeMap<>();
        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                wordCounts.put(word, wordCounts.getOrDefault(word, 0) + 1);
            }
        }
        return wordCounts;
    }

    public static void printWordFrequencies(TreeMap<String, Integer> wordCounts) {
        for (String word : wordCounts.keySet()) {
            System.out.println(word + " - " + wordCounts.get(word));
        }
    }
}