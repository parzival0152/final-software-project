/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

import java.util.Date;

public class BetterDate extends Date {

    public BetterDate() {
        super();
        this.setYear(2021);
        this.setDay(0);
        this.setMonth(0);
    }

    public BetterDate(int month, int day) {
        super();
        this.setYear(2021);
        this.setDay(day);
        this.setMonth(month);
    }

    public void turnDate(String dateString)
    {
        int arr[]= new int[2];
        int day, month, count=0;

        for (String s : dateString.split("/")) {
            arr[count]=(Integer.parseInt(s));
             count++;
        }
        day=arr[0];
        month=arr[1];

        this.setDay(day);
        this.setMonth(month); 
    }

    @Override
    public int compareTo(Date anotherDate) {
        int thisTime = this.getMonth()*30 +this.getDay();
        int anotherTime = anotherDate.getMonth()*30 +anotherDate.getDay();
        return (thisTime<anotherTime ? -1 : (thisTime==anotherTime ? 0 : 1));
    }

    @Override
    public String toString() {
        return (this.getDay()+"/"+this.getMonth());
    }

    @Override
    public int getDay() {
        return super.getDate();
    }

    public void setDay(int day) {
        super.setDate(day);
    }

    @Override
    public int getMonth() {
        return super.getMonth();
    }

    @Override
    public void setMonth(int month) {
        super.setMonth(month);
    }

    public boolean set_Month(int month) {
        if(month<1||month>31)
            return false;
        super.setMonth(month);
        return true;
        
    }

    public boolean set_Day(int day) {
        if(day<1||day>31)
            return false;
        super.setDate(day);
        return true;
    }

}
