import java.util.List;

public abstract class Room{
    private boolean availabe;
    private int roomId;
    private Guests occupants;
    private List<String[]> purchaseList;
    
    public Room(int roomId) {
        this.roomId = roomId;
        setAvailabe(true);
    }

    // Get and set functions
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

    public List<String[]> getPurchaseList() {
        return purchaseList;
    }

    // adds an item to the purchase list
    public void buy(String name, int amount)
    {
        boolean flag=false;
        if(checkMenu(name)) // if product is in menu
        {
            for(int i=0; i< purchaseList.size(); i++)
            {
                // check if the product has been bought by the room before
                // if it was, change the amount
                if(purchaseList.get(i)[0]==name)
                {
                    purchaseList.get(i)[1] = String.valueOf(Integer.parseInt(purchaseList.get(i)[1])+amount);
                    flag=true;
                }
            }
            if(!flag) // if the item is new add it to the list
            {
                String[] in = {name,String.valueOf(amount),String.valueOf(RoomService.getPrice(name))};
                purchaseList.add(in);
            }
        }
    }

    public void emptyPurchaseList()
    {
        this.purchaseList.clear();
    }

    public abstract boolean checkMenu(String name);

}