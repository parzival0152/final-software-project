public abstract class Guests {
    private String name;
    private int totalnumber;
    private int numKids;

    public Guests(String name, int totalnumber, int numKids) {
        this.name = name;
        this.totalnumber = totalnumber;
        this.numKids = numKids;
    }

    public abstract double getMult();

    // Get and set functions
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalnumber() {
        return totalnumber;
    }

    public void setTotalnumber(int totalnumber) {
        this.totalnumber = totalnumber;
    }

    public int getNumKids() {
        return numKids;
    }

    public void setNumKids(int numKids) {
        this.numKids = numKids;
    }
}