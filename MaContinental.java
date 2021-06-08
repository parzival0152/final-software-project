import java.util.ArrayList;
import java.util.Iterator;

public class MaContinental implements UIable {
	private Room[][] roomAr;
	private RoomService room_service;
	private Calendar booking_Calendar;

	public MaContinental() {

		roomAr = new Room[5][4];
		// 5 floors 4 room each
		// room numbers start from 100. hundred indicates floor num.
		for (int i = 1; i <= 5; i++) {
			for (int j = 0; j <= 3; j++) {
				if ((j == 0 || j == 3) && i > 1 && i < 5)
					roomAr[i - 1][j] = new RoomwView(100 * i + j);
				else if (i == 5)
					roomAr[i - 1][j] = new SuiteRoom(500 + j);
				else
					roomAr[i - 1][j] = new NormalRoom(100 * i + j);
			}
		}

		this.room_service = new RoomService(this);
		booking_Calendar = new Calendar();
	}

    public void run() {
		String name;
		ArrayList<Integer> roomsList;
		int roomID=0;
        boolean quit = false;
        while (!quit) {
            int option = UIable.askOption("check in", "check out", "calendar", "Room service","Exit");
            switch (option) {
                case 1:
                	name=UIable.askString("please enter guest name");
					checkIn(name);
                    break;
                case 2:
                    while(roomID<100||roomID>500)
					{
                         roomID=UIable.askNum("please enter on of the rooms ID");
					}
                    
			      
					if(roomAr[ roomID / 100][ roomID % 100].getAvailabe()==false)
					  {
                          name=roomAr[ roomID / 100][ roomID % 100]. getOccupants().getName();
						  roomsList= booking_Calendar.findBooking(name).getRooms();
                          checkOut(roomsList);
					  }

                    break;
                case 3:
                    //calendar
                    booking_Calendar.run();
                    break;
                case 4:
                    //Room service
                    room_service.run();
                    break;
                case 5:
                    //exit
                    quit = true;
                    break;
                default:
                    break;
            }
        }
    }

   





	// makes a check string for every room in list. will eventually print.
	// also sets room to available and clears the purchase list.
	public void checkOut(ArrayList<Integer> rooms_num) {
		Double sum = 0.0, value = 0.0;
		String check = "";
		// go over every room's purchase list and add the prices of the items.
		for (int num : rooms_num) {
			check = check.concat("Room " + Integer.toString(num) + ":\n"); // printing check for every room.
			int row = num / 100;
			int column = num % 100;
			Room room = roomAr[row][column];
			for (String[] product : room.getPurchaseList()) {
				value = Double.parseDouble(product[1]) * Double.parseDouble(product[2]);
				check = check.concat(product[0] + " x" + product[1] + " $" + Double.toString(value) + "\n");
				sum += value;
			}
			int stayTime = booking_Calendar.getStay(room.getRoomId(), room.getOccupants());
			if (room instanceof SuiteRoom)
				sum += 300 * stayTime;
			else if (room instanceof RoomwView)
				sum += 180 * stayTime;
			else
				sum += 150 * stayTime;
			check = check.concat("Room total = " + Double.toString(sum) + "\n\n");
			// clearing room for next guest
			room.setAvailabe(true);
			room.emptyPurchaseList();
		}
	}

	// changes room variables to match guest.
	public void checkIn(String name) {
		Booking booking;
		// checks if booking by the guest exists
		booking = booking_Calendar.findBooking(name);
		if (booking == null) {
			UIable.showString("Booking not found.");
			return;
		}
		// if it does change room variables accordingly
		Iterator<Integer> roomNum = booking.getRooms().iterator();
		int number;
		while (roomNum.hasNext()) {
			number = roomNum.next();
			roomAr[number / 100][number % 100].setOccupants(booking.getBooking_Guest());
			roomAr[number / 100][number % 100].setAvailabe(false);
		}
	}

	public void roomService()
	{
		room_service.run();
	}

}