import java.util.ArrayList;

/*
booking contain guests,which rooms are booked
and the dates of the booking
there are get and set functions and also a method for calculating 
the numbers of days of staying in the hotel

*/



public class Booking {
    private Guests booking_Guest;
    private ArrayList<Integer> rooms;
    private BetterDate arrival_date;
    private BetterDate leaving_date;

    // Get and set functions
    public Guests getBooking_Guest() {
        return booking_Guest;
    }

    public void setBooking_Guest(Guests booking_Guest) {
        this.booking_Guest = booking_Guest;
    }

    public ArrayList<Integer> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Integer> rooms) {
        this.rooms = rooms;
    }

    public int getNum_Guests() {
        return booking_Guest.getTotalnumber();
    }
    public void setNum_Guests(int num_Guests) {
        booking_Guest.setTotalnumber(num_Guests);
    }

    public int getNum_Kids() {
        return booking_Guest.getNumKids();
    }
    public void setNum_Kids(int num_kids) {
        booking_Guest.setNumKids(num_kids);
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

    // returns the number of days the booking is for
    public int returnNumberDays() {
        int num_days;
        double arrival = arrival_date.getMonth()*30 + arrival_date.getDay();
        double leave = leaving_date.getMonth()*30 + leaving_date.getDay();
        num_days = (int) (leave - arrival);
        return num_days;
    }

    // constructor for new booking
    public Booking(Guests booking_Guest, ArrayList<Integer> rooms, BetterDate arrival_date,
            BetterDate leaving_date) {
        this.booking_Guest = booking_Guest;
        this.rooms = rooms;
        this.arrival_date = arrival_date;
        this.leaving_date = leaving_date;
    }

}
