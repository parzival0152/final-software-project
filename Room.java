import java.util.List;

public abstract class Room{
    private boolean availabe;
    private int roomId;
    private Guests occupants;
    private List<String[]> purchaseList;
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

    public List<String[]> getPurchaseList() {
        return purchaseList;
    }

    public void buy(String name, int amount)
    {
        boolean flag=false;
        if(checkMenu(name)) //if product is in menu
        {
            for(int i=0; i< purchaseList.size(); i++)
            {
                //check if there is already a product with this name
                //if there is just change the amount
                if(purchaseList.get(i)[0]==name)
                {
                    int newAmount=Integer.parseInt(purchaseList.get(i)[1])+amount;
                    purchaseList.get(i)[1]=String.valueOf(newAmount);
                    flag=true;
                }
            }
            if(!flag)
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