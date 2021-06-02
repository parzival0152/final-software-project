import javax.swing.JOptionPane;

//class helper prints out menu
interface Printable {
    
    public static int option(String... options)
    {
        int choice = -1;
        do {

            String message;
            //options are given in selected class and are sent here
            for (int i = 0; i < options.length; i++) {
                message=message + String.valueOf(i+1)+options[i]+"\n";
            }
            choice=Integer.parseInt(JOptionPane.showInputDialog("Please choose one of the following options:\n "+ message+"\n"));
           
            System.out.print("Your choice: ");

            //sending back to class selection by user
            try {

                if (App.Input.hasNext())
                {
                    choice = Integer.parseInt(App.Input.nextLine());
                }
                    

            } catch (Exception e) {
                choice = -1;
            }
            if(!(choice>0 && choice<= options.length))
            {
                System.out.println("Error: not an option");
            }
        } while (!(choice>0 && choice<= options.length));
        //do while there are choices
        return choice;
    }
}