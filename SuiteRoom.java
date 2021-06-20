import java.util.ArrayList;
import java.util.Collections;

public class SuiteRoom extends Room {
    private ArrayList<String> miniFridge = new ArrayList<String>();
    private String[] startingDrinks = {"Irish cream","vodka","gin","rum","liquer","whisky"};

    public SuiteRoom(int roomId) {
        super(roomId);
        restockFridge();
    }

    public ArrayList<String> getFridge()
    {
        return this.miniFridge;
    }

    public void drink(String drink)
    {
        this.miniFridge.remove(drink);
    }

    public void restockFridge()
    {
        for(String drink: startingDrinks)
        {
            if(!miniFridge.contains(drink))
                this.miniFridge.add(drink);
        }
        Collections.sort(miniFridge);
    }

    public void printFridge()
    {
        String message = "";
        for(String drink:miniFridge)
        {
            message = message.concat(drink+"\n");
        }
        UI.showString(message);
    }

    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            System.out.println("Item not in menu");
            return false;
        }
        return true;
    }
}