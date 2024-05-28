import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Container;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Project1 {

    public static void main(String args[]) {
        String[] solutions = inputFromFile("P1input.txt");

        PuzzleGUI pg = new PuzzleGUI();
        print(pg, solutions);
    }

    public static void print(PuzzleGUI pg, String[] solutions) {
      String variable = solutions[0];
      int score = 0;
      ArrayList<String> correctInputs = new ArrayList<>();
  
      Container myContentPane = pg.getContentPane();
      TextArea puzzleLetters = new TextArea();
      TextArea userWords = new TextArea();
      myContentPane.add(puzzleLetters);
      myContentPane.add(userWords);
  
      puzzleLetters.append(variable);
      String answer = JOptionPane.showInputDialog("Type anything to start game");
  
      while (!answer.equalsIgnoreCase("Stop")) {
          answer = JOptionPane.showInputDialog("Guess a word or Stop to quit");
  
          if (!answer.equalsIgnoreCase("Stop")) {
              // Check if the word is valid
              boolean validWord = true;
              if (answer.length() < 5) {
                  validWord = false;
                  JOptionPane.showMessageDialog(null, "User's guess is less than five letters. Guess a word again or type Stop to quit");
              } else {
                  for (int i = 0; i < answer.length(); i++) {
                      if (!variable.contains(String.valueOf(answer.charAt(i)))) {
                          validWord = false;
                          break;
                      }
                  }
              }
  
              if (!validWord) {
                  JOptionPane.showMessageDialog(null, "Invalid input! Word contains invalid letters.");
                  continue;
              }
  
              // Check if the word is in the solutions
              boolean wordFound = false;
              for (String solution : solutions) {
                  if (answer.equalsIgnoreCase(solution)) {
                      correctInputs.add(answer);
                      wordFound = true;
                      score++;
                      break;
                  }
              }
  
              if (!wordFound) {
                  JOptionPane.showMessageDialog(null, "User's guess is not in the solutions. Guess a word again or type Stop to quit");
              }
  
              // Update the GUI
              userWords.setText("");
              for (String correctInput : correctInputs) {
                  userWords.append(correctInput + "\n");
              }
              userWords.append("Your score is: " + score);
          }
      }
  }
  

    public static String[] inputFromFile(String filename) {
        ArrayList<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list.toArray(new String[0]);
    }
}
