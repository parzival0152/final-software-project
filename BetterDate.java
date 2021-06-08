import java.util.Date;

public class BetterDate extends Date {

    public BetterDate() {
        super();
        Date temp = new Date(2021, 05, 0, 0, 0);
        this.setDate(temp.getDate());
    }

    public BetterDate(int month, int day) {
        super();
        Date temp = new Date(2021, month, day);
        this.setDate(temp.getDate());
    }

    public BetterDate turnDate(String dateString)
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
            for (String s : dateString.split(".")) {
                arr[count]=(Integer.parseInt(s));
                count++;
            }
        day=arr[0];
        month=arr[1];

        BetterDate tmp = new BetterDate(month, day);
        return tmp;
        
    }

    @Override
    public String toString() {
        return String.valueOf(getDate());
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
