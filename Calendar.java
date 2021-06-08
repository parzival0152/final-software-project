import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Calendar implements UIable{

    Object[] dateArr;
    HashMap<String, Booking> bookMap;
    ArrayList<Integer> allRooms;

    public Calendar() {
        allRooms=new ArrayList<Integer>();
        bookMap=new HashMap<String, Booking>();
    
        for (int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
                allRooms.add((i+1)*100+j);
        } 
        
    }

    public void run()
    {
        String name, stay, done;
        int totNum, kidNum, guestType, roomNum;
        boolean quit = false;
        while (!quit) {
            int option = UIable.askOption("Add Booking", "Delete Booking", "Edit Booking","Exit");
            switch (option) {
                case 1:
                    name=UIable.askString("please enter guest name");
                    stay= UIable.askString("enter date of check in dd/mm");
                    done= UIable.askString("enter date of check out dd/mm");
                    totNum=UIable.askNum("enter number of people staying");
                    kidNum=UIable.askNum("enter number of children staying");
                    roomNum=UIable.askNum("enter number of rooms you want");
                    UIable.showString("Please choose what type of guest you are.");
                    guestType=UIable.askOption("Regular Guest", "Gold Guest", "Platinum Guest");

                    BetterDate start= new BetterDate();
                    start.turnDate(stay);
                    BetterDate finish= new BetterDate();
                    finish.turnDate(done);
                    addBooking(name, start, finish, totNum, kidNum, guestType, roomNum);
                    break;
                case 2:
                    name=UIable.askString("please enter guest name");
                    deleteBooking(name);
                    break;
                case 3:
                    name=UIable.askString("please enter guest name");
                    editBooking(name);
                    break;
                case 4:
                    quit=true;
                    break;
                default:
                    break;
            }
        }


    }

    public ArrayList<String> getAvailable(ArrayList<Integer> available)
    {
        ArrayList<String> availableRooms = new ArrayList<String>();
        String tmp;
        for(int i=0; i<available.size(); i++)
        {
            tmp=Integer.toString(available.get(i));
            if(available.get(i)>=500)
                tmp+=" - suite room";
            else if((available.get(i)%100==0 || available.get(i)%100==3 )&& available.get(i)>=200)
                tmp+=" - room with view";
            else
                tmp+=" - regular room";
            availableRooms.add(tmp);
        }
        return availableRooms;

    }

    // we get name,dates,number of guest and how many rooms from any type
    public void addBooking(String name, BetterDate start, BetterDate finish, int totalnumber, int numKids, int guestType, int roomNum) {
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        Guests g = new Guests(name, totalnumber, numKids, guestType);

        // this is the list of the rooms that are not available
        available = findRooms(start, finish);

        int option;

        //here we ask the user which rooms he wants and put it in selectRoom
        UIable.showString("Please choose what room you want to stay in.");
        for(int i=0; i< roomNum; i++)
        {
            option=UIable.askOption(getAvailable(available));
            selectRoom.add(available.get(option-1));
            available.remove(option-1);
        }
        
        //create booking
        Booking b = new Booking(g, selectRoom, start, finish);
        bookMap.put(name, b);
    }

    public ArrayList<Integer> findRooms(BetterDate start, BetterDate finish) {
        ArrayList<Integer> roomArr = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (HashMap.Entry<String, Booking> entry : bookMap.entrySet()) {
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
                    int select;
                    //tell guest that the room is unavailable and need to pick another one
                    UIable.showString("Room " + roomArr.get(i) +" is unavailable. Please choose one of the following rooms.");
                    //send available list
                    select = UIable.askOption(getAvailable(available));;
                    selectRoom.add(available.get(select-1));
                    available.remove(select-1);
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
