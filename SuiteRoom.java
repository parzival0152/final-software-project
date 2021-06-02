public class SuiteRoom extends Room{
    
    public SuiteRoom(int roomId) {
        super(roomId);
    }

    public boolean checkMenu(String name)
    {
        if(!rs.ifExist(name,3))
        {
            System.out.println("Item not in menu");
            return false;
        }
        return true;

    }
}