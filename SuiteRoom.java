import java.util.ArrayList;

public class SuiteRoom extends Room {
    private ArrayList<String> miniFridge = new ArrayList<String>();
    private String restockList;
    private String[] startingDrinks = {"vodka","gin","rum","liquer","whisky"};

    public SuiteRoom(int roomId) {
        super(roomId);
        this.restockList = "Irish Cream";
        for(String drink:startingDrinks)
            this.restockList.concat(","+drink);
        restockFridge();
    }

    public void drink(String drink)
    {
        miniFridge.remove(drink);
    }

    private void restockFridge()
    {
        String[] split = restockList.split(",");
        for(String drink: split)
        {
            miniFridge.add(drink);
        }
    }

    public void printFridge()
    {
        for(String drink:miniFridge)
        {
            System.out.println(drink);
        }
    }

    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, 3)) {
            System.out.println("Item not in menu");
            return false;
        }
        return true;
    }
}