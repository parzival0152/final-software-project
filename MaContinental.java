import java.util.ArrayList;

public class MaContinental {
    private Room[] roomAr = new Room[20];
    private RoomService room_service;
    private Calendar booking_Calendar;


    public MaContinental()
    {

        //5 floors 4 room each
        for(int i=0; i<20; i++)
        {
            if (i>4&&(i%4==0||i%4==3)&&i<17)
              roomAr[i]=new RoomwView(i);
            else if (i>=17)
              roomAr[i]=new SuiteRoom(i);
            else 
              roomAr[i]=new NormalRoom(i);
        }

        room_service=new RoomService();
        booking_Calendar= new Calendar();
    }
    public void checkOut (ArrayList<Integer> rooms_num)
     {
        //going over the rooms calculating printing the result
        //delete the data


     }
    

}