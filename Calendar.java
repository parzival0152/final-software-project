import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Calendar{

    private HashMap<String, Booking> bookMap;
    private ArrayList<Integer> allRooms;

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
            int option = UI.askOption("Add Booking", "Delete Booking", "Edit Booking","Exit");
            switch (option) {
                case 1:
                    name=UI.askString("please enter guest name");
                    stay= UI.askString("enter date of check in dd/mm");
                    done= UI.askString("enter date of check out dd/mm");
                    totNum=UI.askNum("enter number of people staying");
                    kidNum=UI.askNum("enter number of children staying");
                    roomNum=UI.askNum("enter number of rooms you want");
                    UI.showString("Please choose what type of guest you are.");
                    guestType=UI.askOption("Regular Guest", "Gold Guest", "Platinum Guest");

                    BetterDate start= new BetterDate();
                    start.turnDate(stay);
                    BetterDate finish= new BetterDate();
                    finish.turnDate(done);
                    addBooking(name, start, finish, totNum, kidNum, guestType, roomNum);
                    break;
                case 2:
                    name=UI.askString("please enter guest name");
                    deleteBooking(name);
                    break;
                case 3:
                    name=UI.askString("please enter guest name");
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
        UI.showString("Please choose what room you want to stay in.");
        for(int i=0; i< roomNum; i++)
        {
            option=UI.askOption(getAvailable(available));
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

            // if our desired date isn't completely before another
            if (!(start.compareTo(start2) < 0 && finish.compareTo(start) <= 0)) {
                // go over all the rooms in that booking
                for (int j = 0; j < entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));

            }

            // if our desired date isn't completely after another
            if (!(start.compareTo(finish2) >= 0 && finish.compareTo(finish2) > 0)) {
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
        String stay, done;
        int totNum, kidNum;
        boolean quit = false;
        while (!quit) {
            int option = UI.askOption("Change date", "change number of people staying" ,"Change Rooms", "Exit");
            switch (option) {
                case 1:
                    stay= UI.askString("enter new date of check in dd/mm");
                    done= UI.askString("enter new date of check out dd/mm");

                    BetterDate start = new BetterDate();
                    BetterDate finish = new BetterDate();
                    start.turnDate(stay);
                    finish.turnDate(done);

                    changeDate(name, start, finish);

                    break;
                case 2:
                    totNum=UI.askNum("please enter number of guests");
                    kidNum=UI.askNum("please enter number of children");
                    changeGuestNum(name, totNum, kidNum);
                    deleteBooking(name);
                    break;
                case 3:
                    changeRooms(name);
                    break;
                case 4:
                    quit=true;
                    break;
                default:
                    break;
            }
        }

    }

    public int getStay(int a,Guests b){
        return bookMap.get(b.getName()).returnNumberDays();
    }

    // you need to check the room is available on those dates,delete and creat new
    // booking
    public void changeDate(String name, BetterDate start, BetterDate finish) {
        
        ArrayList<Integer> available = new ArrayList<Integer>();
        ArrayList<Integer> roomArr = new ArrayList<Integer>();
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();

        //this is the list of the rooms the booking has
        roomArr=bookMap.get(name).getRooms();

        //getting all the information needed before deleting booking
        Guests g=bookMap.get(name).getBooking_Guest();
        BetterDate start_og=bookMap.get(name).getArrival_date();
        BetterDate finish_og=bookMap.get(name).getLeaving_date();
        //delete booking so oocupied rooms will be available
        deleteBooking(name);
        //create new booking with empty selectRoom
        Booking b = new Booking(g, selectRoom, start_og, finish_og);
        

        // this is the list of the rooms that are available
        available = findRooms(start, finish);


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
            b.setRooms(roomArr);
            b.setArrival_date(start);
            b.setLeaving_date(finish);
        }
        else
        {
            for(int i=0; i<roomArr.size(); i++)
            {
                if(!available.contains(roomArr.get(i)))
                {   
                    int select;
                    //tell guest that the room is unavailable and need to pick another one
                    UI.showString("Room " + roomArr.get(i) +" is unavailable in new date. Please choose one of the following rooms.");
                    //send available list
                    select = UI.askOption(getAvailable(available));;
                    selectRoom.add(available.get(select-1));
                    available.remove(select-1);
                }
                //keep the room
                else
                    selectRoom.add(roomArr.get(i));

            }

        }
        b.setRooms(selectRoom);
        b.setArrival_date(start);
        b.setLeaving_date(finish);
        bookMap.put(name, b);
    }

    public void changeGuestNum(String name, int guests_num, int kid_num) {
        bookMap.get(name).setNum_Guests(guests_num);
        bookMap.get(name).setNum_Kids(kid_num);
    }

    public void changeRooms(String name)
    {
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        //getting all the information needed before deleting booking
        Guests g=bookMap.get(name).getBooking_Guest();
        BetterDate start=bookMap.get(name).getArrival_date();
        BetterDate finish=bookMap.get(name).getLeaving_date();
        //delete booking so oocupied rooms will be available
        deleteBooking(name);
        //create new booking with empty selectRoom
        Booking b = new Booking(g, selectRoom, start, finish);


        // this is the list of the rooms that are not available
        available = findRooms(bookMap.get(name).getArrival_date(), bookMap.get(name).getLeaving_date());


        int option;
        //here we ask the user which rooms he wants and put it in selectRoom
        UI.showString("Please choose what room you want to stay in.");
        for(int i=0; i< bookMap.get(name).getRooms().size(); i++)
        {
            option=UI.askOption(getAvailable(available));
            selectRoom.add(available.get(option-1));
            available.remove(option-1);
        }

        b.setRooms(selectRoom);
    }

    public Booking findBooking(String name) {

        return bookMap.get(name);

    }

}
