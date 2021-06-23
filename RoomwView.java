
//extending Room view rooms has the option to show the view
public class RoomwView extends Room {

    public RoomwView(int roomId) {
        super(roomId);
    }

    // Check if item requested item is in menu of guest level
    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            return false;
        }
        return true;
    }

    //showing the view from the room
    public void showView()
    {
        String path = "/Views/" + Integer.toString(this.getRoomId()) + ".jpg";
        UI.displayImage(path);
    }
    
}
