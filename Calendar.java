import java.util.ArrayList;

public class Calendar {

    
    Object[] dateArr;

    public Calendar()
    {
        
        
        dateArr = new Object[30];
        //initializing all 30 arrays
        for(int i=0; i<30; i++)
        {
            dateArr[i] = new ArrayList<Booking>();
        }
    }
    

    


    
}
