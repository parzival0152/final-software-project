/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/
import java.util.ArrayList;
import java.util.Collections;

// Extends Room. Suite has an alchohol fridge
public class SuiteRoom extends Room {
    private ArrayList<String> miniFridge = new ArrayList<String>();
    private String[] startingDrinks = {"Irish cream","vodka","gin","rum","liquer","whisky"};

    public SuiteRoom(int roomId) {
        super(roomId);
        restockFridge();
    }

    //returns the frodge
    public ArrayList<String> getFridge()
    {
        return this.miniFridge;
    }
    //removing a drink from fridge
    public void drink(String drink)
    {
        this.miniFridge.remove(drink);
    }

    // Makes sure fridge has the starting drinks
    public void restockFridge()
    {
        for(String drink: startingDrinks)
        {
            if(!miniFridge.contains(drink))
                this.miniFridge.add(drink);
        }
        Collections.sort(miniFridge);
    }
    //printing the fridge
    public void printFridge()
    {
        String message = "";
        for(String drink:miniFridge)
        {
            message = message.concat(drink+"\n");
        }
        UI.showString(message);
    }

    // Check if item requested item is in menu of guest level
    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            return false;
        }
        return true;
    }
}