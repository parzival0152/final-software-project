import java.util.ArrayList;

public abstract class Room{
    private boolean availabe;
    private int roomId;
    private Guests occupants;
    private ArrayList<Product> purchaseList = new ArrayList<Product>();
    //add purchesed items

    public boolean isAvailabe() {
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
}