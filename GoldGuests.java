public class GoldGuests extends Guests{
    private boolean bonus;

    public GoldGuests(String name, int totalnumber, int numKids) {
        super(name, totalnumber, numKids);
    }

    public double getMult() {
        return 0.75;
    }
}