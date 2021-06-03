import java.util.ArrayList;

import javax.lang.model.util.ElementScanner6;

public class MaContinental {
    private Room[][] roomAr = new Room[5][4];
    private RoomService room_service;
    private Calendar booking_Calendar;


    public MaContinental()
    {

        //5 floors 4 room each
        //room numbers start from 100. hundred indicates floor num.
        for(int i=1; i <= 5; i++)
        {
          for(int j = 0; j < 3; j++)
          {
            if ((j == 0 || j == 3) && i > 1 && i < 5)
              roomAr[i-1][j]=new RoomwView(100*i+j);
            else if (i == 5)
              roomAr[i-1][j]=new SuiteRoom(500+j);
            else 
              roomAr[i-1][j]=new NormalRoom(100*i+j);
          }
        }

        room_service=new RoomService();
        booking_Calendar= new Calendar();
    }

    //makes a check string for every room in list. will eventually print.
    //also sets room to available and clears the purchase list.
    public void checkOut (ArrayList<Integer> rooms_num)
     {
       Double sum = 0.0, value = 0.0;
       String check =""; 
       //go over every room's purchase list and add the prices of the items.
       for(int num:rooms_num)
       {
          check.concat("Room " + String.valueOf(num)+":\n"); //printing check for every room.
          int row = num/100;
          int column = num%100;
          Room room = roomAr[row][column];
          for(String[] product:room.getPurchaseList())
          {
            value = Double.valueOf(product[1])*Double.valueOf(product[2]);
            check.concat(product[0] + " x" + product[1] + " $" + String.valueOf(value)+"\n");
            sum += value;
          }
          int stayTime = booking_Calendar.getStay(room.getRoomId(),room.getOccupants());
          if(room instanceof SuiteRoom)
            sum += 300*stayTime;
          else if(room instanceof RoomwView)
            sum += 180*stayTime;
          else
            sum += 150*stayTime;
          check.concat("Room total = " + String.valueOf(sum) + "\n\n");
          //clearing room for next guest
          room.setAvailabe(true);
          room.emptyPurchaseList();
       }
     }

     public void checkIn()
     {

     }
    

}