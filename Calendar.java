import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class Calendar {

    Object[] dateArr;
    Map<String, Booking> bookMap;
    ArrayList<Integer> allRooms;

    public Calendar() {
        for (int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
                allRooms.add((i+1)*100+j);
        } 
    }

    // we get name,dates,number of guest and how many rooms from any type
    public void addBooking(String name, BetterDate start, BetterDate finish, int totalnumber, int numKids, int guestType) {
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();
        Guests g = new Guests(name, totalnumber, numKids, guestType);

        // this is the list of the rooms that are not available
        available = findRooms(start, finish);

        //here we ask the user which rooms he wants and put it in selectRoom
        //TBI

        //create booking
        Booking b = new Booking(g, selectRoom, start, finish);
        bookMap.put(name, b);
    }

    public ArrayList<Integer> findRooms(BetterDate start, BetterDate finish) {
        ArrayList<Integer> roomArr = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (Map.Entry<String, Booking> entry : bookMap.entrySet()) {
            // go over all bookings in specific starting date

            BetterDate start2 = entry.getValue().getArrival_date();
            BetterDate finish2 = entry.getValue().getLeaving_date();

            // if our desired date starts before another finishes
            if (start.compareTo(start2) > 0 && start.compareTo(finish2) < 0) {
                // go over all the rooms in that booking
                for (int j = 0; j < entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));

            }

            // if our desired date finishes after another starts
            if (start.compareTo(start2) < 0 && (finish.compareTo(start2) > 0)) {
                for (int j = 0; j < entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));
            }
        }

        //available contains all the rooms that are available
        for (int i = 0; i < allRooms.size() ; i++)
        {
            if(!roomArr.contains(allRooms.get(i)))
                available.add(allRooms.get(i));
        }
        return available;
    }

    public void deleteBooking(String name) {
        bookMap.remove(name);
    }

    public void editBooking(String name) {
        // TBI
    }

    public int getStay(int a,Guests b){
        return 0;
    }

    // you need to check the room is available on those dates,delete and creat new
    // booking
    public void changeDate(String name, BetterDate start, BetterDate finish) {
        
        ArrayList<Integer> available = new ArrayList<Integer>();
        ArrayList<Integer> roomArr = new ArrayList<Integer>();
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();

        // this is the list of the rooms that are available
        available = findRooms(start, finish);
        //this is the list of the rooms the booking has
        roomArr=bookMap.get(name).getRooms();

        boolean flag=true;
        for(int i=0; i<roomArr.size(); i++)
        {
            //if one of the rooms isn't available in the new date, change flag to false
            if(!available.contains(roomArr.get(i)))
            {
                flag=false;
            }
        }

        //if all the rooms are available just change the date without changing anything else
        if(flag)
        {
            bookMap.get(name).setArrival_date(start);
            bookMap.get(name).setLeaving_date(finish);
        }
        else
        {
            for(int i=0; i<bookMap.get(name).getRooms().size(); i++)
            {
                if(!available.contains(roomArr.get(i)))
                {   
                    //tell guest that the room is unavailable and need to pick another one
                    //send available list
                    //select is what the room the user chose
                    int select=0;
                    selectRoom.add(roomArr.get(select));
                    roomArr.remove(select);
                }
                //keep the room
                else
                    selectRoom.add(roomArr.get(i));

            }

        }
        Booking b = new Booking(bookMap.get(name).getBooking_Guest(), selectRoom, start, finish);
        deleteBooking(name);
        bookMap.put(name, b);
    }

    public void chengeGuestNum(String name, int guests_num) {
        bookMap.get(name).setNum_Guests(guests_num);
    }

    public Booking findBooking(String name) {

        return bookMap.get(name);

    }

    class DateComperator implements Comparator<BetterDate> {

        @Override
        public int compare(BetterDate o1, BetterDate o2) {
            if (o1.getMonth() > o2.getMonth() || (o1.getMonth() == o2.getMonth() && o1.getDay() > o2.getDay()))
                return 1;
            else if (o1.getMonth() == o2.getMonth() && o1.getDay() == o2.getDay())
                return 0;
            else
                return -1;
        }
    }

}
