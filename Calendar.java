import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Calendar {

    Object[] dateArr;
    Map<String ,Booking> bookMap;
    ArrayList<Integer> roomsType[];

    public Calendar()
    {
        for(int i=0; i<20; i++)
        {
            if (i>4&&(i%4==0||i%4==3)&&i<17)
                roomsType[1].add(i);
            else if (i>=17)
                roomsType[2].add(i);
            else 
                roomsType[0].add(i);
        }

    }
    public void addBooking(String name, BetterDate start, BetterDate finish, int num_guest, int room_type, int room_num, int guests_num)
    {
        ArrayList<Integer> roomArr= new ArrayList<Integer>();
        ArrayList<Integer> selectRoom= new ArrayList<Integer>();
        int counter=0;

        //this is the list of the rooms that are not available
        roomArr= findRooms(start, finish);
        

        for(int i=0; i<roomsType[room_type].size();i++)
        {
            //if one of the rooms are not in roomArr, add to selectRoom
            if(!roomArr.contains(roomsType[room_type].get(i)))
            {
                selectRoom.add(roomsType[room_type].get(i));
                counter++;
                if(counter==room_num)
                    break;
            }
        }

        if(counter==room_num)
        {
            //create booking
            Booking new1 = new Booking(name, selectRoom, guests_num, start, finish);
            bookMap.put(name, new1);
        }


    }

    public ArrayList<Integer> findRooms(BetterDate start, BetterDate finish)
    {
        ArrayList<Integer> roomArr= new ArrayList<Integer>();

        for(Map.Entry<String,Booking> entry : bookMap.entrySet())
        {
            //go over all bookings in specific starting date

            BetterDate start2=entry.getValue().getArrival_date();
            BetterDate finish2=entry.getValue().getLeaving_date();

            //if our desired date starts before another finishes
            if(start.compareTo(start2)>0&&start.compareTo(finish2)<0)
            {
                //go over all the rooms in that booking
                for(int j=0; j<entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));
                    
            }

            //if our desired date finishes after another starts
            if(start.compareTo(start2)<0 &&(finish.compareTo(start2)>0))
            {
                for(int j=0; j<entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));
            }
        }

        return roomArr;
    }

    public void deleteBooking(String name)
    {
        bookMap.remove(name);
    }

    public void editBooking(String name)
    {

    }

    public void changeDate(String name, BetterDate start, BetterDate finish)
    {
        bookMap.get(name).setArrival_date(start);
        bookMap.get(name).setLeaving_date(finish);
    }

    public void chengeGuestNum(String name, int guests_num)
    {
        bookMap.get(name).setNum_Guests(guests_num);
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
