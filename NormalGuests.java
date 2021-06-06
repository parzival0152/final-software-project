public class NormalGuests extends Guests {

    public NormalGuests(String name, int totalnumber, int numKids) {
        super(name, totalnumber, numKids);
    }

    public double getMult() {
        return 1;
    }
}