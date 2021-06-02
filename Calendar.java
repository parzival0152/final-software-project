import java.util.ArrayList;

public class Calendar {

    
    ArrayList<Booking>[] dateArr;

    Calendar()
    {
        
        
        dateArr = new ArrayList[30];
        //initializing all 30 arrays
        for(int i=0; i<30; i++)
        {
            dateArr[i] = new ArrayList<Booking>();
        }
    }
    

    


    
}
