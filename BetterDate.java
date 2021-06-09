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
        if(dateString.contains(","))
            for (String s : dateString.split(",")) {
                arr[count]=(Integer.parseInt(s));
                count++;
            }
        else if(dateString.contains("/"))
            for (String s : dateString.split("/")) {
                arr[count]=(Integer.parseInt(s));
                count++;
            }
        else if(dateString.contains(" "))
            for (String s : dateString.split(" ")) {
                arr[count]=(Integer.parseInt(s));
                count++;
            }
        else if(dateString.contains("."))
        {
            for (String s : dateString.split("\\.")) {
                arr[count]=(Integer.parseInt(s));
                count++;
            }
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

}
