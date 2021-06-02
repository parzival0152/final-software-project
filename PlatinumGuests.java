public class PlatinumGuests extends Guests{
    private boolean bonus;

    public PlatinumGuests(String name, int totalnumber, int numKids) {
        super(name, totalnumber, numKids);
    }

    public double getMult() {
        return 0.5;
    }

}