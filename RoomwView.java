

public class RoomwView extends Room implements UIable {

    public RoomwView(int roomId) {
        super(roomId);
    }

    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, 2)) {
            System.out.println("Item not in menu");
            return false;
        }
        return true;
    }

    public void showView()
    {
        String path = "/Views/" + Integer.toString(this.getRoomId()) + ".jpg";
        UIable.displayImage(path);
    }
    
}
