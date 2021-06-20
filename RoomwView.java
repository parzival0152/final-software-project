

public class RoomwView extends Room {

    public RoomwView(int roomId) {
        super(roomId);
    }

    // Check if item requested item is in menu of guest level
    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            System.out.println("Item not in menu");
            return false;
        }
        return true;
    }

    public void showView()
    {
        String path = "/Views/" + Integer.toString(this.getRoomId()) + ".jpg";
        UI.displayImage(path);
    }
    
}
