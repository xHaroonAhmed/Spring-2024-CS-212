import javax.swing.JOptionPane;
import java.awt.TextArea;
import java.awt.Container;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;
import java.io.IOException;

public class Project3 {
    public static void main(String args[]) {
    ArrayList<String> answerList = new ArrayList<>();
    answerList = inputFromFile("P1input.txt");
    PuzzleGUI puzzleGUI = new PuzzleGUI();
  }

  public static void print(PuzzleGUI puzzleGUI, ArrayList<String> answerList) {
    String letters = answerList.getFirst();
    char firstLetter=letters.charAt(0);
    int userScore=0;
    int guessedWordsCount = 0;
    Container contentPane = puzzleGUI.getContentPane();
    TextArea letterScreen = new TextArea();
    TextArea guessScreen = new TextArea();
    contentPane.add(letterScreen);
    contentPane.add(guessScreen);
    UnsortedWordList unsortedWordList=new UnsortedWordList();
    SortedWordList sortedWordList = new SortedWordList();

    for(int i=0;i<answerList.size();i++){
        try {
            Word nWord=new Word(answerList.get(i));
            unsortedWordList.add(nWord);
        } catch (IllegalWordException e) {
            System.out.println("error.");
        }
    }

    letterScreen.append(answerList.getFirst());

    while (true) {

        String userInput = JOptionPane.showInputDialog("guess a word or Stop to quit");
        if (userInput.equalsIgnoreCase("Stop")) break;

        boolean validInput = true;
        while (validInput) {

            if(!userInput.contains(String.valueOf(firstLetter))) {
                JOptionPane.showMessageDialog(null, "Invalid input! Word does not contain \'l'.");
                break;
            }

            boolean validLetters = true;
            for (int i = 0; i < userInput.length(); i++) {
                if (!letters.contains(String.valueOf(userInput.charAt(i)))) {
                    validLetters = false;
                    break;
                }
            }

            if (!validLetters) {
                JOptionPane.showMessageDialog(null, "Invalid input! Word contains invalid letters.");
                break;
            }

            if (userInput.length() < 5) {
                JOptionPane.showMessageDialog(null,"Users guess is less than five letters, guess a word again or Stop to quit");
                break;
            }

            boolean inSolution = false;

            WordNode p =unsortedWordList.head.getNext();
            while(p!=null){
                if(userInput.equals(p.getData().getWord())){
                    inSolution=true;
                    Word w=new Word(userInput);
                    sortedWordList.add(w);
                    guessedWordsCount++;
                }
                p=p.getNext();
            }

            if (inSolution == false) {
                JOptionPane.showMessageDialog(null,
                        "Users guess is not in the solutions, guess a word again or Stop to quit");
                break;
            }

            boolean containsAllLetters = true;
            for(int i=0;i<=userInput.length();i++){
                char c=letters.charAt(i);
                if(!userInput.contains(String.valueOf(c))) {
                    containsAllLetters = false;
                    break;
                }
            }

            if(containsAllLetters==false) userScore++;
            else userScore+=3;

            guessScreen.setText("");

            WordNode currentNode =sortedWordList.head.getNext();

            while(currentNode !=null){
                guessScreen.append(currentNode .getData().getWord()+"\n");
                currentNode = currentNode .getNext();
            }

            guessScreen.append("Your score is: " + userScore);

            WordNode qNode =sortedWordList.head.getNext();
            HashSet<String> answerSpan = new HashSet<>();

            while(qNode !=null){
                answerSpan.add(qNode.getData().getWord());
                guessScreen.append(qNode.getData().getWord()+"\n");
                qNode = qNode.getNext();
            }
            
            

            break;
        }
    }
}


  public static ArrayList<String> inputFromFile(String filename) {
    TextFileInput in = new TextFileInput(filename);
    String line = in.readLine();

    ArrayList<String> answerList = new ArrayList<>();

    while (line != null) {
      StringTokenizer myTokens = new StringTokenizer(line, ",");

      while (myTokens.hasMoreTokens()) {

        String s = myTokens.nextToken();
        answerList.add(s);
        
      }
      line = in.readLine();
    }
    in.close();
    return answerList;

  }

}


