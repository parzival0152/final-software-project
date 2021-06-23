/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

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
