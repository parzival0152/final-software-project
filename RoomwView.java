public class RoomwView extends Room{
    
    public RoomwView(int roomId) {
        super(roomId);
    }

    public boolean checkMenu(String name)
    {
        if(rs.ifExist(name,2) == -1)
        {
            System.out.println("Item not in menu");
            return false;
        }
        return true;

    }
}
