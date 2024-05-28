import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.Container;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.IOException;

public class Project2 {

  public static void main(String args[]) {

    ArrayList<String> Solutions = new ArrayList<>();
    Solutions = inputFromFile("P1input.txt");
    

    PuzzleGUI pg = new PuzzleGUI();
    print(pg, Solutions);

    // testing our linked list
    UnsortedWordList unsortedWordList = new UnsortedWordList();
    SortedWordList sortedWordList = new SortedWordList();
      // Word w1=new Word("gate");
        // Word w2=new Word("dog");
          // Word w3=new Word("cat");
            // Word w4=new Word("bat");
            // sortedWordList.add(w1);
              // sortedWordList.add(w2);
                // sortedWordList.add(w3);
                  // sortedWordList.add(w4);

                    // sortedWordList.print();
   




  }

  public static void print(PuzzleGUI pg, ArrayList<String> SolutionsList) {
    String SubjectLetters = SolutionsList.getFirst(); // subject letters that users can make words with
    char firstLetter = SubjectLetters.charAt(0); // takes out first letter from subject letters
   int score = 0; // keeps track of the score
   // ArrayList<String> correctInputs = new ArrayList<>(); // stores all correct user inputs
    Container myContentPane = pg.getContentPane();
    TextArea myTextArea = new TextArea();
    TextArea mySubscripts = new TextArea();
    myContentPane.add(myTextArea);
    myContentPane.add(mySubscripts);
    UnsortedWordList unsortedWordList = new UnsortedWordList();
    SortedWordList sortedWordList = new SortedWordList();

    //adding solutions from inputfile to Unsorted Linked List
    for(int i = 0;i < SolutionsList.size();i++){
      Word w = new Word(SolutionsList.get(i));
      unsortedWordList.add(w);
      //sortedWordList.add(w);
    }
    //sortedWordList.print();



    myTextArea.append(SolutionsList.getFirst());
    String userInput = JOptionPane.showInputDialog("Type anything to start game");

    while (!userInput.equalsIgnoreCase("Stop")) { // loop to keep on running so user can constantly put inputs

      userInput = JOptionPane.showInputDialog("guess a word or Stop to quit");

      boolean z = true;
      while (z && !userInput.equalsIgnoreCase("Stop")) {

       // first letter must be in all cases
       if(!userInput.contains(String.valueOf(firstLetter))) {
        JOptionPane.showMessageDialog(null, "Invalid input! Word does not contain first letter.");
        break;
       }

       
       
        // l
        boolean validLetters = true;
        for (int i = 0; i < userInput.length(); i++) {
          if (!SubjectLetters.contains(String.valueOf(userInput.charAt(i)))) {
            validLetters = false;
            break;
          }
        }

        if (!validLetters) {
          JOptionPane.showMessageDialog(null, "Invalid input! Word contains invalid letters.");
          break;
        }

        // length case
        if (userInput.length() < 5) {
          JOptionPane.showMessageDialog(null,
              "Users guess is less than five letters, guess a word again or Stop to quit");
          break;
        }
        

        // Array List solutions case
        boolean d = false;
        // for (int i = 0; i < SolutionsList.size() - 1; i++) {
        //   if (userInput.equalsIgnoreCase(SolutionsList.get(i))) {
        //     correctInputs.add(userInput);
        //     // c++;
        //     // mySubscripts.append(userInput); // adds userInput to gui
        //     d = true;

        //   }
        // }
        
        // linkedlist solutions case
          WordNode p =unsortedWordList.head.getNext();
          while(p!=null){
            if(userInput.equals(p.getData().getWord())){
              d=true;
              Word w=new Word(userInput);
              sortedWordList.add(w);
            }
            p=p.getNext();
          }

        if (d == false) {
          JOptionPane.showMessageDialog(null,
              "Users guess is not in the solutions, guess a word again or Stop to quit");
          break;
        }

        

        //contains all letters
        boolean b = true;
        for(int i = 0;i < userInput.length();i++){
          char c = SubjectLetters.charAt(i);
          //System.out.println("user input "+userInput);
          //System.out.println("subject letters "+SubjectLetters);
          if(!userInput.contains(String.valueOf(c))) {
            b = false;
            break;
          }
        }

        if(b == false) score++;
        else score += 3;


      
        mySubscripts.setText("");
        // for (String correctInput : correctInputs) {
        //   mySubscripts.append(correctInput + "\n");
        // }
      WordNode t = sortedWordList.head.getNext();
      while(t!=null){
        mySubscripts.append(t.getData().getWord()+"\n");
        t = t.getNext();
      }

      mySubscripts.append("Your score is: " + score);
        userInput = JOptionPane.showInputDialog("guess a word or Stop to quit");
      }
      

    }
  }

    
  

  public static ArrayList<String> inputFromFile(String filename) { // inputTextFile method to take in data from text file and
    // store them in string array
    TextFileInput in = new TextFileInput(filename);
    String line = in.readLine();

    ArrayList<String> SolutionsList = new ArrayList<>(); // array where we will store the values
    

    while (line != null) {
      StringTokenizer myTokens = new StringTokenizer(line, ","); // implement a tokenizer to break apart any texts
      // with commas(,)

      while (myTokens.hasMoreTokens()) {

        String s = myTokens.nextToken();
        SolutionsList.add(s);
        
      }
      line = in.readLine();
    }
    in.close();
    return SolutionsList;

  }

}