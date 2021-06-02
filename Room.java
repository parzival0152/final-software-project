import java.util.ArrayList;
import java.util.Set;

public abstract class Room{
    private boolean availabe;
    private int roomId;
    private Guests occupants;
    protected RoomService rs;
    private Set<String[]> purchaseList;
    //add purchesed items
    
    public Room(int roomId) {
        this.roomId = roomId;
        setAvailabe(true);
    }

    public boolean getAvailabe() {
        return availabe;
    }
    public void setAvailabe(boolean availabe) {
        this.availabe = availabe;
    }

    public int getRoomId() {
        return roomId;
    }
    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public Guests getOccupants() {
        return occupants;
    }
    public void setOccupants(Guests occupants) {
        this.occupants = occupants;
    }

    public Set<String[]> getPurchaseList() {
        return purchaseList;
    }

    public void buy(String name, int amount)
    {
        if(checkMenu(name)) //if product is in menu
        {
            String[] in = {name,String.valueOf(amount),String.valueOf(rs.getPrice(name))};
            purchaseList.add(in);
        }
    }

    public abstract boolean checkMenu(String name);

}