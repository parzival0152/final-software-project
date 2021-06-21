import java.util.ArrayList;
import java.util.Iterator;

/*
the managment system:
allows you to:
check in
check out
access to the Calendar
access to room service system
showing rooms data
room options


*/ 




public class MaContinental implements Printable {
	private Room[][] roomAr;
	private RoomService room_service;
	private Calendar booking_Calendar;

   /*
   the constructor:
    MaContinental contain calendar,roomService and Room[][] of rooms
   */


	public MaContinental() {

		roomAr = new Room[5][4];
		// 5 floors 4 room each
		// room numbers start from 100. hundred indicates floor num.
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j <= 3; j++) {
				if ((j == 0 || j == 3) && i > 0 && i < 4)
					roomAr[i][j] = new RoomwView(100 * (i+1) + j);
				else if (i == 4)
					roomAr[i][j] = new SuiteRoom(500 + j);
				else
					roomAr[i][j] = new NormalRoom(100 * (i+1) + j);
			}
		}

		this.room_service = new RoomService(this);
		booking_Calendar = new Calendar();

		BetterDate start = new BetterDate(1,1);
		BetterDate finish = new BetterDate(1,2);
		ArrayList<Integer> bookedRooms = new ArrayList<>();
		bookedRooms.add(102);
		bookedRooms.add(200);
		bookedRooms.add(503);
		booking_Calendar.bookingWithRooms("or", start, finish, 1, 0, 0, bookedRooms);
		checkIn("or");
	}
    /*
	run method has switch case to all the main functions of the managmant system:
	Check in, Check out, Calendar, Room service,Room status,Room options,Exit
	*/



    public void run() {
		String name,message;
		int roomID=0;
        boolean quit = false;
        while (!quit) {
            int option = UI.askOption("Check in", "Check out", "Calendar", "Room service","Room status","Room options","Exit");
            switch (option) {
                case 1:
				//check in
                	name=UI.askString("Please enter guest name");
					if(name == null)
						break;
					checkIn(name);
                    break;
                case 2:
					//check out
					try
					{
						//getting the room number from the guest who wants to check out
						while(roomID%100 > 3 || roomID > 503 || roomID < 100)
						{
							
							message=UI.askNum("Please enter room Id you would like to check out of");
							if (message==null)
						throw new NullPointerException("demo");
						roomID=Integer.parseInt(message);
						}
					}
						catch(NullPointerException e)
						{
							break;        
						}

					if(roomAr[ roomID / 100 - 1][ roomID % 100].getAvailabe()==false)
					  {
						name=roomAr[roomID / 100 - 1][ roomID % 100].getOccupants().getName();
						checkOut(roomID);
					  }
					  else
					  	UI.showString("Room was not booked.");
					roomID = 0;
                    break;
                case 3:
                    //Calendar
                    booking_Calendar.run();
                    break;
                case 4:
                    //Room service
                    room_service.run();
                    break;
				case 5:
					//Room status
					showData();
					break;
				case 6:
					//Room options
					roomOptions();
					break;
				default:
                    //Exit
                    quit = true;
                    break;
            }
        }
    }

   

	// makes a check string for every room in list. will eventually print.
	// also sets room to available and clears the purchase list.
	public void checkOut(Integer room_num) {
		Double sum = 0.0, value = 0.0;
		String check = "";
		// go over the room's purchase list and add the prices of the items.
		check = check.concat("Room " + Integer.toString(room_num) + ":\n"); // printing check for every room.
		int row = room_num / 100 - 1;
		int column = room_num % 100;
		Room room = roomAr[row][column];
		for (String[] product : room.getPurchaseList()) {
			value = Double.parseDouble(product[1]) * Double.parseDouble(product[2]);
			check = check.concat(product[0] + " x" + product[1] + " = $" + Double.toString(value) + "\n");
			sum += value;
		}
		int stayTime = booking_Calendar.getStay(room.getRoomId(), room.getOccupants());
		int peopleCost = (room.getOccupants().getTotalnumber()-1-room.getOccupants().getNumKids())*20+room.getOccupants().getNumKids()*10;
		Double itemValue = sum;
		if (room instanceof SuiteRoom)
			sum += 300 * stayTime;
		else if (room instanceof RoomwView)
			sum += 180 * stayTime;
		else
			sum += 150 * stayTime;
		check = check.concat("Room cost = " + Double.toString(sum - itemValue) + "$\n");
		check = check.concat("Added cost for number of people staying = " + Integer.toString(peopleCost)+"$\n");
		sum = (sum + peopleCost) * room.getOccupants().discount();
		check = check.concat("Including discount of "+ Double.toString(100 - 100 * room.getOccupants().discount()) + "%\n");
		check = check.concat("Room total = " + Double.toString(sum) + "$\n\n");
		UI.showString(check);
		booking_Calendar.removeRoom(room.getOccupants().getName(), room_num);
		// clearing room for next guest
		room.setAvailabe(true);
		room.emptyPurchaseList();
	}

	// changes room variables to match guest.
	public void checkIn(String name) {
		Booking booking;
		// checks if booking by the guest exists
		booking = booking_Calendar.findBooking(name);
		if (booking == null) {
			UI.showString("Booking not found.");
			return;
		}
		// if it does change room variables accordingly
		Iterator<Integer> roomNum = booking.getRooms().iterator();
		int number;
		while (roomNum.hasNext()) {
			number = roomNum.next() - 100;
			if(roomAr[number / 100][number % 100].getAvailabe()){
				roomAr[number / 100][number % 100].setOccupants(booking.getBooking_Guest());
				roomAr[number / 100][number % 100].setAvailabe(false);
			}
			else
			{
				UI.showString("Sorry, room " + Integer.toString(number) + " is currently occupied. If you had any, you have been checked into your other rooms");
			}
		}
	}
	
    /*
	roomOptions allows you to buy and restock the mini-fridge
	in suite rooms and watch the view in view rooms
	*/


	public void roomOptions()
	{

		int chosenRoom = 0,action;
		ArrayList<String> availableRooms = new ArrayList<>();
		for(Room[] roomArr : roomAr)
		{
			for(Room room:roomArr)
			{
				if(!room.getAvailabe() && !(room instanceof NormalRoom))
					availableRooms.add(Integer.toString(room.getRoomId()));
			}
		}
		try{
			chosenRoom = Integer.parseInt(availableRooms.get(UI.askOption(availableRooms)));
		}
		catch(Exception e)
		{
			return;
		}
		
		if(chosenRoom/100 == 5)
		{
			Boolean exit = false;
			SuiteRoom suite = (SuiteRoom)roomAr[4][chosenRoom%100];
			while(!exit)
			{
				action = UI.askOption("Drink","Restock fridge","Print fridge","Exit");
				switch (action)
				{
					case 1:
						ArrayList<String> fridge = suite.getFridge();
						int drink = UI.askOption(fridge);
						try
						{
							suite.drink(fridge.get(drink));
						}
						catch(Exception e)
						{
							break;
						}
						break;
					case 2:
						suite.restockFridge();
						break;
					case 3:
						suite.printFridge();
						break;
					default:
						exit = true;
						break;
				}
			}
		}
		else
		{
			RoomwView view = (RoomwView)roomAr[chosenRoom/100 -1][chosenRoom%100];
			view.showView();
		}
	}

	public void roomService()
	{
		room_service.run();
	}

	// Returns arrayList<Integer> of available room numbers
	public ArrayList<String> availableRooms()
	{
		ArrayList<String> available = new ArrayList<>();

		for(Room[] roomArr : roomAr)
		{
			for(Room room:roomArr)
			{
				if(!room.getAvailabe())
					available.add(Integer.toString(room.getRoomId()));
			}
		}
		return available;
	}

	/*
	from room service we got which room want to buy what
	we send the information to the buy function in the room and add it there 
	to their check
	 */
    public boolean buyProduct(String name_product,int amount,int row,int column)
      {
		   boolean check;
		   //check is true if the purches was successful
           check= roomAr[row][column].buy(name_product,amount);

           return check;
	  }





	@Override
	public void showData() {
		//prints which rooms are available and which aren't
		String message = "Room Statuses:\n";
		for(Room[] roomArr : roomAr)
		{
			for(Room room:roomArr)
			{
				if(room.getAvailabe() == false)
					message = message.concat(Integer.toString(room.getRoomId())+" - "+room.getOccupants().getName()+"\n");
				else
					message = message.concat(Integer.toString(room.getRoomId())+" - Empty\n");
				if(room.getRoomId()%100 == 3)
					message = message.concat("\n");
			}
		}
		UI.showString(message);
	}

}