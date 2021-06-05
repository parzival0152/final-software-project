import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Calendar {

    Object[] dateArr;
    Map<BetterDate,ArrayList<Booking>> bookMap;
    

    public Calendar()
    {

    }
    public void addBooking(BetterDate start, BetterDate finish, String name, int num_guest, int room_type)
    {

    }

    public ArrayList<Integer> findRooms(BetterDate start, BetterDate finish, int room_type)
    {
        ArrayList<Integer> roomArr= new ArrayList<Integer>();

        for(Map.Entry<BetterDate,ArrayList<Booking>> entry : bookMap.entrySet())
        {
            BetterDate start2=entry.getKey();

            //go over all bookings in specific starting date
            for(int index=0; index<entry.getValue().size(); index++)
            {
                BetterDate finish2=entry.getValue().get(index).getLeaving_date();

                //if our desired date starts before another finishes
                if(start.compareTo(start2)>0&&start.compareTo(finish2)<0)
                {
                    //go over all the rooms in that booking
                    for(int j=0; j<entry.getValue().get(index).getRooms().size(); j++)
                        roomArr.add(entry.getValue().get(index).getRooms().get(j));
                        
                }

                //if our desired date finishes after another starts
                if(start.compareTo(start2)<0 &&(finish.compareTo(start2)>0))
                {
                    for(int j=0; j<entry.getValue().get(index).getRooms().size(); j++)
                        roomArr.add(entry.getValue().get(index).getRooms().get(j));
                }

            }
        }

        return roomArr;
    }
    public boolean checkColl(BetterDate start1, BetterDate finish1, BetterDate start2, BetterDate finish2)
    {
        if(start1.compareTo(start2)<0&&finish1.compareTo(start2)<=0)
            return false;
        if(start1.compareTo(finish2)>=0&&finish1.compareTo(finish2)>0)
            return false;
        return true;
    }

    public int getStay(int roomId, Guests guest) //return the number of days the guest has booked the room
    {
        return 5;
    }
    

    

    class DateComperator implements Comparator<BetterDate>{

        @Override
        public int compare(BetterDate o1, BetterDate o2) {
            if(o1.getMonth()>o2.getMonth()||(o1.getMonth()==o2.getMonth()&&o1.getDay()>o2.getDay()))
                return 1;
            else if(o1.getMonth()==o2.getMonth()&&o1.getDay()==o2.getDay())
                return 0;
            else
                return -1;
        }
    }
    
}
