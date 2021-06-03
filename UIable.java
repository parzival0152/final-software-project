import javax.swing.JOptionPane;

//class helper prints out menu
interface UIable {
    
    public static int askOption(String... options)
    {
    int choice = -1;
    String message = "";
    //options are given in selected class and are sent here
    for (int i = 0; i < options.length; i++) {
        message=message + Integer.toString(i+1)+") "+options[i]+"\n";
    }
    choice=Integer.parseInt(JOptionPane.showInputDialog("Please choose one of the following options:\n"+ message+"\n"));

    //sending back to class selection by user
    while (!(choice>0 && choice<= options.length)){
        choice=Integer.parseInt(JOptionPane.showInputDialog("Error: not an option\nPlease choose one of the following options:\n"+ message+"\n"));
    }
    //do while there are choices
    return choice-1;
    }

    public static String askString(String prompt)
    {
        return JOptionPane.showInputDialog(prompt+"\n");
    }

    public static int askNum(String prompt)
    {
        boolean flag = false;
        while (true) {
            try {
                return Integer.parseInt(JOptionPane.showInputDialog(prompt+"\n"));
            } catch (Exception e) {
                if (!flag) {
                    prompt = "Error: please enter a number\n" + prompt;
                    flag = true;
                }
            }
        }
    }

    public static void showString (String message)
    {
       JOptionPane.showMessageDialog(null, message, "", JOptionPane.PLAIN_MESSAGE);


    }


}


