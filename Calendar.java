/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Calendar implements Printable{

    //contains all the bookings with name as key
    private HashMap<String, Booking> bookMap;
    private static ArrayList<Integer> allRooms;

    public Calendar() {
        allRooms=new ArrayList<Integer>();
        bookMap=new HashMap<String, Booking>();
    
        //containing all the rooms
        for (int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
                allRooms.add((i+1)*100+j);
        } 
        
    }

    public void run()
    {
        String name, stay="", done="";
        String tmp;
        int totNum, kidNum, guestType, roomNum;
        boolean quit = false;
        while (!quit) {
            int option = UI.askOption("Add Booking", "Delete Booking", "Edit Booking", "Show all booking", "Exit");
            switch (option) {
                //add booking
                case 1:
                    BetterDate start= new BetterDate();
                    BetterDate finish= new BetterDate();
                    try
                    {
                        //name
                        name=UI.askString("Please enter guest name");
                        if (name == "" || name == null)
                            throw new NullPointerException("");
                        while(bookMap.containsKey(name))
                        {
                            UI.showString("This name already exists in our system.");
                            name=UI.askString("Please enter guest name");
                            if (name == "" || name == null)
                                throw new NullPointerException("");
                        }

                        //check in date
                        stay=enterDate("Enter date of check in dd/mm");
                        if(stay == null)
                            throw new NullPointerException("");
                        start.turnDate(stay);

                        //check out date
                        done=enterDate("Enter date of check out dd/mm");
                        if(done == null)
                            throw new NullPointerException("");
                        finish.turnDate(done);

                        //check if check out date comes before check in date
                        if(!(finish.compareTo(start)>0))
                        {
                            boolean flag=false;
                            while(!flag)
                            {
                                UI.showString("Your check out date can't be before or at the same day as the check in date.");
                                stay=enterDate("Enter date of check in dd/mm.");
                                if(stay == null)
                                    throw new NullPointerException("");
                                start = new BetterDate();
                                start.turnDate(stay);
            
                                done=enterDate("Enter date of check out dd/mm.");
                                if(done == null)
                                    throw new NullPointerException("");
                                finish = new BetterDate();
                                finish.turnDate(done);
                                if(finish.compareTo(start)>0)
                                    flag=true;
                            }
                            
                        }

                        //people num
                        tmp=UI.askNum("Enter number of people staying.");
                        if(tmp==null)
                            throw new NullPointerException("");
                        totNum=Integer.parseInt(tmp);
                        while(totNum<1)
                        {
                            UI.showString("Please enter a number bigger than zero.");
                            tmp=UI.askNum("Enter number of people staying.");
                            if(tmp==null)
                                throw new NullPointerException("");
                            totNum=Integer.parseInt(tmp);
                        }

                        //children num
                        tmp=UI.askNum("Enter number of children staying.");
                        if(tmp==null)
                            throw new NullPointerException("");
                        kidNum=Integer.parseInt(tmp);
                        while(kidNum<0)
                        {
                            UI.showString("Please enter zero or a number bigger than zero.");
                            tmp=UI.askNum("Enter number of children staying.");
                            if(tmp==null)
                                throw new NullPointerException("");
                            kidNum=Integer.parseInt(tmp);
                        }

                        //room num
                        tmp=UI.askNum("Enter number of rooms you want");
                        if(tmp==null)
                            throw new NullPointerException("");
                        roomNum=Integer.parseInt(tmp);
                        while(roomNum<1 || roomNum>allRooms.size())
                        {
                            UI.showString("Please enter a number bigger than zero and smaller than "+(allRooms.size()+1)+" .");
                            tmp=UI.askNum("Enter number of rooms you want");
                            if(tmp==null)
                                throw new NullPointerException("");
                            roomNum=Integer.parseInt(tmp);
                        }

                        //guest type
                        UI.showString("Please choose what type of guest you are.");
                        guestType=UI.askOption("Regular Guest", "Gold Guest", "Platinum Guest");
                        if(guestType == -1)
                            throw new NullPointerException("");
                    }
                    catch(NullPointerException e)
                    {
                        break;        
                    }
                    addBooking(name, start, finish, totNum, kidNum, guestType, roomNum);
                    break;
                
                //Delete booking
                case 2:
                    name=UI.askString("Please enter guest name");
                    try{
                        if (name==null)
                            throw new NullPointerException("");
                    }
                    catch(NullPointerException e)
                    {
                        break;        
                    }
                    try{
                        if(!bookMap.containsKey(name))
                            throw new NullPointerException("There is no booking by this name.");
                    }
                    catch(NullPointerException e)
                    {
                        UI.showString(e.getMessage());
                        break;        
                    }
                    deleteBooking(name);
                    UI.showString("Booking deleted successfully.");
                    break;
                
                //edit booking
                case 3:
                    try{
                        name=UI.askString("Please enter guest name.");
                        if(name == null)
                            throw new NullPointerException("");
                    }
                    catch(NullPointerException e)
                    {
                        break;
                    }
                    if(!bookMap.containsKey(name))
                    {
                        UI.showString("There is no booking by this name.");
                        break;
                    }
                    editBooking(name);
                    break;
                //Show all booking
                case 4:
                    showData();
                    break;
                default:
                    quit=true;
                    break;
            }
        }


    }

    //turn array list from int to string with notes
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

        //if number of rooms wanted are smaller than rooms available
        if(available.size()<roomNum)
        {
            UI.showString("There are not enough rooms available.");
        }
        else
        {
            int option;
            //here we ask the user which rooms he wants and put it in selectRoom
            UI.showString("Please choose what room you want to stay in.");
            try
            {
                for(int i=0; i< roomNum; i++)
                {
                    option=UI.askOption(getAvailable(available));
                    if(option == -1)
                        throw new NullPointerException("");
                    selectRoom.add(available.get(option));
                    available.remove(option);
                }
            }
            catch(NullPointerException e)
            {
                return;
            }
            Collections.sort(selectRoom);
            //create booking
            Booking b = new Booking(g, selectRoom, start, finish);
            bookMap.put(name, b);
        }
    }

    // runs only on startup to start with booked rooms
    public void bookingWithRooms(String name, BetterDate start, BetterDate finish, int totalnumber, int numKids, int guestType, ArrayList<Integer> roomNum)
    {
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();
        Guests g = new Guests(name, totalnumber, numKids, guestType);
        for(int room:roomNum)
            selectRoom.add(room);
        Collections.sort(selectRoom);
        Booking b = new Booking(g, selectRoom, start, finish);
        bookMap.put(name, b);
    }

    //find all available rooms in specific date
    public ArrayList<Integer> findRooms(BetterDate start, BetterDate finish) {
        boolean flag1=false, flag2=false;
        ArrayList<Integer> roomArr = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        for (HashMap.Entry<String, Booking> entry : bookMap.entrySet()) {

            // go over all bookings
            BetterDate start2 = entry.getValue().getArrival_date();
            BetterDate finish2 = entry.getValue().getLeaving_date();

            // if our desired date is completely before another
            flag1=!(start.compareTo(start2) < 0 && finish.compareTo(start) <= 0);
            // if our desired date is completely after another
            flag2=!(start.compareTo(finish2) >= 0 && finish.compareTo(finish2) > 0);

            if(flag1&&flag2)
            {
                //put in roomArr all rooms that are unavailable
                for (int j = 0; j < entry.getValue().getRooms().size(); j++)
                    roomArr.add(entry.getValue().getRooms().get(j));
            }

        }

        //available contains all the rooms that are available
        //basically do available= allRooms-roomArr
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
        boolean quit = false;
        while (!quit) {
            int option = UI.askOption("Change date", "Change number of people staying" ,"Change Rooms", "Exit");
            switch (option) {
                case 1:
                    changeDate(name);
                    break;
                case 2:
                    changeGuestNum(name);
                    break;
                case 3:
                    changeRoomNum(name);
                    break;
                default:
                    quit=true;
                    break;
            }
        }

    }

    //return number of days that a guest stays
    public int getStay(int a,Guests b){
        return bookMap.get(b.getName()).returnNumberDays();
    }

    //chenge dates in booking
    public void changeDate(String name) {
        BetterDate start= new BetterDate();
        BetterDate finish= new BetterDate();

        UI.showString("Your current check in date is: "+ bookMap.get(name).getArrival_date()
        +".\nYour current check out date is: " + bookMap.get(name).getLeaving_date()+".");

        //getting check in and check out date
        String stay = enterDate("Enter new date of check in dd/mm.");
        try{
            if(stay == null)
                throw new NullPointerException("");
        }
        catch(NullPointerException e)
        {
            return;
        }
        String done = enterDate("Enter new date of check out dd/mm.");
        try{
            if(done == null)
                throw new NullPointerException("");
        }
        catch(NullPointerException e)
        {
            return;
        }
        finish.turnDate(done);
        start.turnDate(stay);

        //check if check out date comes before check in date
        if(!(finish.compareTo(start)>0))
        {
            boolean flag=false;
            while(!flag)
            {
                try{
                UI.showString("Your check out date can't be before or at the same day as check in date.");
                stay=enterDate("Enter date of check in dd/mm.");
                if(stay == null)
                    throw new NullPointerException("");
                start= new BetterDate();
                start.turnDate(stay);

                done=enterDate("Enter date of check out dd/mm.");
                if(done == null)
                    throw new NullPointerException("");
                finish= new BetterDate();
                finish.turnDate(done);
                if(finish.compareTo(start)>0)
                    flag=true;
                }
                catch(NullPointerException e)
                {
                    return;
                }
            }
        }
        
        //turning to better date
        start.turnDate(stay);
        finish.turnDate(done);

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
                    UI.showString("Room " + roomArr.get(i) +" is unavailable in the new date. Please choose one of the following rooms.");
                    //send available list
                    select = UI.askOption(getAvailable(available));
                    if(select == -1)
                        return;
                    selectRoom.add(available.get(select));
                    available.remove(select);
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

    //change number of guests or kids
    public int changeGuestNum(String name) {
        String tmp;
        UI.showString("Your current number of guests is: "+ bookMap.get(name).getNum_Guests()
        +".\nYour current number of children is: " + bookMap.get(name).getNum_Kids()+".");

        tmp=UI.askNum("Enter number of people staying.");
        if(tmp==null)
            return -1;
        int totNum=Integer.parseInt(tmp);
        while(totNum<1)
        {
            UI.showString("Please enter a number bigger than zero.");
            tmp=UI.askNum("Enter number of people staying.");
            if(tmp==null)
                return -1;
            totNum=Integer.parseInt(tmp);
        }
        
        tmp=UI.askNum("Please enter number of children.");
        if(tmp==null)
            return -1;
        int kidNum=Integer.parseInt(tmp);
        while(kidNum<0)
        {
            UI.showString("Please enter zero or a number bigger than zero.");
            tmp=UI.askNum("Please enter number of children.");
            if(tmp==null)
                return -1;
            kidNum=Integer.parseInt(tmp);
        }
        bookMap.get(name).setNum_Guests(totNum);
        bookMap.get(name).setNum_Kids(kidNum);
        return 0;
    }

    public void changeRooms(String name,int roomNum)
    {
        ArrayList<Integer> selectRoom = new ArrayList<Integer>();
        ArrayList<Integer> available = new ArrayList<Integer>();

        //getting dates
        BetterDate start=bookMap.get(name).getArrival_date();
        BetterDate finish=bookMap.get(name).getLeaving_date();

        //change rooms to current empty selectRoom
        bookMap.get(name).setRooms(selectRoom);


        //this is the list of the rooms that are available
        available = findRooms(start, finish);


        int option;
        //here we ask the user which rooms he wants and put it in selectRoom
        UI.showString("Please choose what room you want to stay in.");
        for(int i=0; i< roomNum; i++)
        {
            option=UI.askOption(getAvailable(available));
            if(option == -1)
                return;
            selectRoom.add(available.get(option));
            available.remove(option);
        }

        bookMap.get(name).setRooms(selectRoom);
    }

    public void changeRoomNum(String name)
    {
        UI.showString("Your current number of rooms is: "+ bookMap.get(name).getRooms().size()
        +".\nYour selected rooms are: " + bookMap.get(name).getRooms()+".");

        ArrayList<Integer> available = new ArrayList<Integer>();
        int room;
        String tmp;
        boolean quit = false;

        while (!quit) {
            available = findRooms(bookMap.get(name).getArrival_date(), bookMap.get(name).getLeaving_date());

            int option = UI.askOption("Add room", "Remove room" ,"Change number of rooms", "Exit");
            switch (option) {
                //add one room
                case 1:
                    UI.showString("Please choose which room you want to add.");
                    room=UI.askOption(getAvailable(available));
                    if(room == -1)
                        break;
                    // adds room to booking in correct place, rooms are sorted in ascending order
                    for(int i =bookMap.get(name).getRooms().size()-1; i >= 0 ; i--)
                    {
                        if(bookMap.get(name).getRooms().get(i) < available.get(room))
                        {
                            bookMap.get(name).getRooms().add(i+1,available.get(room));
                            break;
                        }
                    }
                    if(!bookMap.get(name).getRooms().contains(available.get(room)))
                        bookMap.get(name).getRooms().add(0,available.get(room));
                    break;

                //remove one room
                case 2:
                    UI.showString("Please choose which room you want to remove.");
                    room=UI.askOption(getAvailable(bookMap.get(name).getRooms()));
                    if(room == -1)
                        break;
                    removeRoom(name, room);
                    break;
                //change num of rooms
                case 3:
                    tmp=UI.askNum("Enter the number of rooms you want:");
                    if(tmp==null)
                        break;
                    int roomNum=Integer.parseInt(tmp);
                    //check if number of selected rooms minus the number of rooms already owned is too much
                    if(available.size()<roomNum-bookMap.get(name).getRooms().size())
                    {
                        UI.showString("There are not enough rooms available.");
                        break;
                    }
                    changeRooms(name, roomNum);
                    break;
                default:
                    quit=true;
                    break;
            }
        }

    }

    //return booking according to name
    public Booking findBooking(String name) {
        return bookMap.get(name);
    }

    //enter date in the correct format
    public String enterDate(String message)
    {
        boolean flag=false;
        String buffer="";
        while(!flag)
        {   
            try{
                buffer= UI.askString(message);
                if(buffer == null)
                    throw new NullPointerException("");
            }
            catch(NullPointerException e)
            {
                break;
            }

            //only proceed if contains "/" so day and month can be split
            if(buffer.contains("/"))
            {
                //create array to put day and month in
                int arr[]= new int[2];
                int count=0;
                for (String s : buffer.split("/")) {
                    //put in arr[0] day, and in arr[1] month
                    try{
                        arr[count]=(Integer.parseInt(s));
                    }
                    catch(NumberFormatException e)
                    {
                        break;
                    }
                    
                    count++;
                    //if there are more than one "/", ignore and use only dd/mm part of string
                    if(count==2)
                    {
                        buffer=arr[0]+"/"+arr[1];
                        break;
                    }
                        
                }
                //make sure days and months are ok
                if(!((arr[0]<1||arr[0]>31)||(arr[1]<1||arr[1]>12)))
                    flag=true;
                else if(count<2)
                    UI.showString("Your format is wrong.");
                else
                    UI.showString("please enter a day 1-31 and a month 1-12");
            }
            else
                UI.showString("Your date format is incorrect, please enter dd/mm");

        }
        return buffer;
    }

    public void removeRoom(String name, int room_num)
    {
        //if we got place in allRoom array
        if(room_num < 20)
            bookMap.get(name).getRooms().remove(room_num);
    
        //if we got room number
        else if(room_num >= 100)
        {
            for(int i = 0; i < bookMap.get(name).getRooms().size(); i++)
            {
                if(bookMap.get(name).getRooms().get(i).equals(room_num))
                    bookMap.get(name).getRooms().remove(i); 
            }
        }
        if(bookMap.get(name).getRooms().size() == 0)
            deleteBooking(name);
    }

    @Override
    public void showData() {
        String allBookings="";

        for (HashMap.Entry<String, Booking> entry : bookMap.entrySet()) {
            allBookings+= entry.getKey()+":"
            +"\n    Arrival date: " + entry.getValue().getArrival_date()
            +"\n    Leaving date: " + entry.getValue().getLeaving_date()
            +"\n    Number of guests: " + entry.getValue().getNum_Guests()
            +"\n    Number of children: " + entry.getValue().getNum_Kids()
            +"\n    Rooms: " + entry.getValue().getRooms()+"\n\n";
        }

        if(bookMap.isEmpty())
            UI.showString("There are no bookings.");
        else
            UI.showString(allBookings);
    
    }
}
