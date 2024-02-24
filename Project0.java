import javax.swing.JOptionPane;

public class Project0 {
    public static void main(String[] args){
        int counter_e = 0, counter_E = 0;
        String str1 = "Stop", str2 = "STOP";
        while (true){
            char match_e = 'e', match_E = 'E';
            String userInput = JOptionPane.showInputDialog("Please enter a sentence");
            if (userInput.equalsIgnoreCase(str1) || userInput.equalsIgnoreCase(str2)) break;
            for (int i = 0; i < userInput.length() - 1; i++){
                if (userInput.charAt(i) == match_e) counter_e++;
                else if (userInput.charAt(i) == match_E) counter_E++;
            }
        }
        JOptionPane.showMessageDialog(null, "The number of e's: " + counter_e + "\n" + "The number of E's: " + counter_E);
    }
}