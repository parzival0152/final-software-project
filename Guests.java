/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

/*
Guests contain the name of the guest who booked
the number of guest and the number of kids among them
also the type of guest:regular,gold or platinum
there are get and set methods 
and one that return the discount according to the guest type
*/

public class Guests {
    private String name;
    private int totalnumber;
    private int numKids;
    private int type;

    //constructor
    public Guests(String name, int totalnumber, int numKids, int type) {
        this.name = name;
        this.totalnumber = totalnumber;
        this.numKids = numKids;
        this.type = type;
    }

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

    public int getType() {
        return type;
    }

    public double discount(){
        if(this.getType() == 0)
            return 1.0;
        else if (this.getType() == 1)
            return 0.85;
        else
            return 0.75;
    }
}