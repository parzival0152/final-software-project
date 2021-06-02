public class RoomwView extends Room{
    
    public RoomwView(int roomId) {
        super(roomId);
    }

    public boolean checkMenu(String name)
    {
        if(!RoomService.ifExist(name,2))
        {
            System.out.println("Item not in menu");
            return false;
        }
        return true;

    }
}
