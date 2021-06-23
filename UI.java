/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

//class helper prints out menu
public class UI {

    // creates drop down menu with string options. returns position (starting at 1).
    public static int askOption(String... options) {
        if(options.length < 5)
        {
            return JOptionPane.showOptionDialog(null, "Pick an option",
            "MA Continental the hotel for you",
            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0])+1;
        }
        else
        {
            String pick = (String)JOptionPane.showInputDialog(null, "Pick an option",
            "MA Continental the hotel for you",
            JOptionPane.QUESTION_MESSAGE, null, 
            options, // Array of choices
            options[0]); // Initial choice
            if(pick == null)
                return -1;
            for (int i = 0; i < options.length; i++) 
            {
                if(pick.equals(options[i]))
                    return i+1;
            }
            return -1;   
        }
    }

    // creates drop down menu with string arraylist options. returns position (starting at 1) op picked choice
    public static int askOption(ArrayList<String> loptions){
        
        Object[] options = loptions.toArray();
        String pick = (String)JOptionPane.showInputDialog(null, "Pick an option",
            "MA Continental the hotel for you",
            JOptionPane.QUESTION_MESSAGE, null, 
            options, // Array of choices
            options[0]); 
        if(pick == null)
            return -1;
        for (int i = 0; i < options.length; i++) 
        {
            if(pick.equals(options[i]))
                return i;
        }
        return -1;  
    }

    public static String askString(String prompt) {
        return JOptionPane.showInputDialog(prompt + "\n");
    }

    public static String askNum(String prompt) {
        
        
        return JOptionPane.showInputDialog(prompt + "\n");
           
    }

    public static void showString(String message) {
        //check number of lines
        int numLines = message.split("\n").length;
        if(numLines<35)
            JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);
        //if number of lines is 35 or more create scroll option
        else
        {
            JTextArea textArea = new JTextArea(message);
            JScrollPane scrollPane = new JScrollPane(textArea);  
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            scrollPane.setPreferredSize( new Dimension( 200, 500 ) );
            JOptionPane.showMessageDialog(null, scrollPane, "" ,JOptionPane.PLAIN_MESSAGE);
        }

    }

    // Get image from path and display
    public static void displayImage(String path) {
        ImageIcon view;
        try {
            view = new ImageIcon(UI.class.getResource(path));
        } catch (Exception e) {
            view = new ImageIcon(UI.class.getResource("/Views/default.jpg"));
        }
        JOptionPane.showMessageDialog(null, null, "Your view out the window", JOptionPane.INFORMATION_MESSAGE, view);
    }
}
