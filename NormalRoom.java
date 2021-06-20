public class NormalRoom extends Room {

    public NormalRoom(int roomId) {
        super(roomId);
    }

    public boolean checkMenu(String name) {
        if (!RoomService.ifExist(name, this.getOccupants().getType()+1)) {
            System.out.println("Item not in menu");
            return false;
        }
        return true;

    }
}
