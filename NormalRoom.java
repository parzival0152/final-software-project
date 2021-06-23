public class NormalRoom extends Room {

    public NormalRoom(int roomId) {
        super(roomId);
    }

    // Check if item requested item is in menu of guest level
    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            return false;
        }
        return true;

    }
}
