import java.util.ArrayList;

public class Booking {
    private String booking_Guest;
    private ArrayList<Integer> rooms;
    private int num_Guests;
    private BetterDate arrival_date;
    private BetterDate leaving_date;

    
    public String getBooking_Guest() {
        return booking_Guest;
    }

    public void setBooking_Guest(String booking_Guest) {
        this.booking_Guest = booking_Guest;
    }

    public ArrayList<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Integer> rooms) {
        this.rooms = rooms;
    }

    public int getNum_Guests() {
        return num_Guests;
    }

    public void setNum_Guests(int num_Guests) {
        this.num_Guests = num_Guests;
    }

    public BetterDate getArrival_date() {
        return arrival_date;
    }

    public void setArrival_date(BetterDate arrival_date) {
        this.arrival_date = arrival_date;
    }

    public BetterDate getLeaving_date() {
        return leaving_date;
    }

    public void setLeaving_date(BetterDate leaving_date) {
        this.leaving_date = leaving_date;
    }
    
    public int returnNumberDays()
    {
       int num_days;
       double arrival=arrival_date.getMonth()+arrival_date.getDay()/30;
       double leave=leaving_date.getMonth()+leaving_date.getDay()/30;
       num_days=(int)(leave-arrival)*30;
       return num_days;
    }
    
    
    public Booking(String booking_Guest, ArrayList<Integer> rooms, int num_Guests, BetterDate arrival_date,BetterDate leaving_date) {
        this.booking_Guest = booking_Guest;
        this.rooms = rooms;
        this.num_Guests = num_Guests;
        this.arrival_date = arrival_date;
        this.leaving_date = leaving_date;
    }



    
}
