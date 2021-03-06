/*****************************
 Grop 4
 Omri Baron 314838210
 Ilay Tzuberi 211873286
 Or Reginiano 315995845
 Eliya Bronshtein 207379348
 *****************************/

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/*
RoomService allows rooms to order room service
and manges the stock:
Add product
Remove product
Product details
Order room service
Add to stock
Sort by price
Show stock
Exit
*/


public class RoomService implements  Printable {
    private static ArrayList<Product> items = new ArrayList<Product>();
    private static ArrayList<String> items_string = new ArrayList<String>();
    private static ArrayList<String> rooms = new ArrayList<String>();
    


    MaContinental managment;

    /*
    the constructor we start with some product in stock.

     */
    public RoomService(MaContinental managment1) {
        managment=managment1;
        addProduct("Water", 10, 30, 1);
        addProduct("Chocolate", 15, 30, 1);
        addProduct("Chips", 15, 30, 1);
        addProduct("Fruit Basket", 45, 15, 2);
        addProduct("Wine", 55, 10, 2);
        addProduct("Champagne", 70, 10, 3);
        addProduct("Caviar", 50, 5, 3);
        items_string.add("Water");
        items_string.add("Chocolate");
        items_string.add("Chips");
        items_string.add("Fruit Basket");
        items_string.add("Wine");
        items_string.add("Champagne");
        items_string.add("Caviar");
    }

   /*
	run method has switch case to all the main functions of the Room service:
	
	*/



    public void run() {

        String name,message;
        double price=-1;
        int amount=-1;
        int level=-1;
        int check,row,column,choice;
        boolean flag;

           
            boolean quit = false;
            while (!quit) {
                int option=UI.askOption(
                "Add product",
                "Remove product",
                "Product details",
                "Order room service",
                "Add to stock",
                "Sort by price",
                "Sort by name",
                "Show stock",
                "Exit");
                  
                switch (option) {
                    case 1:
                    	//add product
                       //getting information about the new product from the user
                        try{
                            name=UI.askString("Enter product name:");
                            if (name==null)
                                throw new NullPointerException("demo");
                                while(price<0)
                                {
                                    message=UI.askNum("Enter price:");
                                    if (message==null)
                                throw new NullPointerException("demo");
                                    price=Double.parseDouble(message);
                                }
                                while(amount<0)
                                {
                                    message=UI.askNum("Enter amount:");
                                    if (message==null)
                                throw new NullPointerException("demo");
                                amount=Integer.parseInt(message);
                                    
                                }
                                while(level<1 || level >3)
                                {
                                    message=UI.askNum("Enter:\n 1-for regular menu\n 2-for gold menu\n3-for platinum menu:");
                                    if (message==null)
                                   throw new NullPointerException("demo");
                                      level=Integer.parseInt(message);
                                }
                                //creating new product
                                check=addProduct( name,  price,  amount,  level);
                                if (check==1)
                                items_string.add(name);
                                price=-1;
                                amount=-1;
                                level=-1;
                        }
                        catch(NullPointerException e)
                        {
                            price=-1;
                             amount=-1;
                             level=-1;
                            break;        
                        }
                        break;
                    case 2:
	                    //Remove product
	                    try
	                    {
	                        if(items.isEmpty())
	                            throw new NullPointerException("No products.");
	                    }
	                    catch(NullPointerException e)
	                    {
	                        UI.showString(e.getMessage());
	                        break;
	                    }
	                    option=UI.askOption(items_string);
	                    if(option!=-1)
	                    {
	                        removeProduct(items.get(option).getName());
	                    }
                        
                        
                        break;
                    case 3:
	                    //Product details
	                    //printing product information
	                    try
	                    {
	                        if(items.isEmpty())
	                            throw new NullPointerException("No products.");
	                    }
	                    catch(NullPointerException e)
	                    {
	                        UI.showString(e.getMessage());
	                        break;
	                    }	
	                    option=UI.askOption(items_string);
	                    if(option!=-1)
	                    {
	                        message=items.get(option).toString();
	                        UI.showString( message);
	                    }
                        break;
                    case 4:
	                    //Order room service
	                    //getting information from user
	                    try
	                    {
	                        if(items.isEmpty())
	                            throw new NullPointerException("No products.");
	                    }
	                    catch(NullPointerException e)
	                    {
	                        UI.showString(e.getMessage());
	                        break;
	                    }
	                    option=UI.askOption(items_string);
	                    if(option==-1)
	                    {
	                        break;
	                    }
	                    
	                    try{
	                    while(amount<0)
	                    {
	                        message=UI.askNum("Enter amount:");
	                        if (message==null)
	                            throw new NullPointerException("demo");
	                        amount=Integer.parseInt(message);
	                        if(amount < 0)
	                        UI.showString("Please enter a positive amount.");
	                    }
	                
	                     if(items.get(option).getAmount()<amount)
	                    {
	                     UI.showString("Not enough in stock.");
	                     amount=-1;
	                    break;
	                    }
	                    rooms=managment.availableRooms();
	                    choice=UI.askOption(rooms);
	                    if(choice == -1)
	                        throw new NullPointerException("demo");
	                    row=Integer.parseInt(rooms.get(choice))/100 -1;
	                    column=Integer.parseInt(rooms.get(choice))%100;
	                    //sending to the room through managment.checking if purchase was successful
	                    flag=managment.buyProduct(items_string.get(option), amount, row, column);
	                    if(flag==true)
	                        {
	                            UI.showString("Purchase successful.");
	                            items.get(option).setAmount(items.get(option).getAmount()-amount);
	                        }
	                    else
	                    UI.showString("Purchase failed, guest doesn't have access to this menu.");
	                    }
	                    catch(NullPointerException e)
	                    {
	                        price=-1;
	                        amount=-1;
	                        level=-1;
	                        break;        
	                    }
	                        price=-1;
	                        amount=-1;
	                        level=-1;
	                        break;

                    case 5:
	                    //Add to stock
	                    //re-stock products
	                    	
	                    try
	                    {
	                        if(items.isEmpty())
	                            throw new NullPointerException("No products.");
	                    }
	                    catch(NullPointerException e)
	                    {
	                        UI.showString(e.getMessage());
	                        break;
	                    }
	                    
	                    try
	                    {
	                    	
	                        option=UI.askOption(items_string);
	                        if(option!=-1)
	                        {
	                            message=UI.askNum("Enter amount:");
	                        if (message==null)
	                            throw new NullPointerException("Null exception.");
	                        if(Integer.parseInt(message)<0)
	                            throw new NumberFormatException("Negative number.");
	
	                        amount=Integer.parseInt(message);
	                        reStock( items_string.get(option),  amount);
	                        amount=-1;
	                        }
	
	                       
	                    }
	                        catch(NumberFormatException e)
	                        {
	                            UI.showString("Can't enter negative number.");
	                        }
	                        
	                        catch(NullPointerException e)
	                    {
	                        price=-1;
	                         amount=-1;
	                         level=-1;
	                        break;        
	                    }
                        
                        break;
                    case 6:
                    //sort by price
                        try
                        {
                            if(items.isEmpty())
                                throw new NullPointerException("No products.");
                        }
                        catch(NullPointerException e)
                        {
                            UI.showString(e.getMessage());
                            break;
                        }
                        sortPrice();
                        break;

                    case 7:
                        //showing all stock details
                        try
                        {
                            if(items.isEmpty())
                                throw new NullPointerException("No products.");
                        }
                        catch(NullPointerException e)
                        {
                            UI.showString(e.getMessage());
                            break;
                        }
                        sortName();
                        break;

                    case 8:
                    //showing all stock details
                        showData();
                        break;
                   
                    default:
                        quit = true;
                        break;
                }
              
            }
		
    }

   

    // add new product to items list
    public int addProduct(String name, double price, int amount, int level) {
        if (!isDup(name)) {
            Product p = new Product(name, price, amount, level);
            items.add(p);
            return 1;
        }
        UI.showString("Prodcut by this name already exists.");
        return 0;
    }

    // remove a product from the items list
    public int removeProduct(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                items_string.remove(i);
                return i;
                
            }
        }
        return -1;
    }

    // return the amount of a certain item in stock
    public static int getAmount(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return items.get(i).getAmount();
            }
        }
        return -1;
    }

    // return the price of an item
    public static double getPrice(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return items.get(i).getPrice();
            }
        }
        return -1;
    }

    // check if an item exists in stock
    public static boolean ifExist(String name, int level) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name) && items.get(i).getLevel() <= level
                    && items.get(i).getAmount() != 0) {
                return true;
            }
        }
        return false;
    }

     // check if an item exists in stock
     public static int ifExist_general(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    // c check for dupes in the items list
    public boolean isDup(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    //changing the amount in stock
    public void reStock(String name_product, int amount) {

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name_product)) {
                items.get(i).setAmount( items.get(i).getAmount()+amount);
            }
        }

    }

    // sorting
    public void sortName() {
        Collections.sort(items, new NameComperator());
        items_string.clear();
        for(Product p: items)
        {
            items_string.add(p.getName());
        }
    }

    public void sortPrice() {
        Collections.sort(items, new PriceComperator());
        items_string.clear();
        for(Product p: items)
        {
            items_string.add(p.getName());
        }
    }

    @Override
    //showing all detail of the stock
    public void showData()
    {
        Iterator<Product> itemIt = items.iterator();
        Product item;
        String message = "Products:\n";
        while(itemIt.hasNext())
        {
            item = itemIt.next();
            message = message.concat(item.toString() + "\n\n");
        }
        UI.showString(message);
    }

    // comperators
    class NameComperator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }

    }

    class PriceComperator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            if (o1.getPrice() > o2.getPrice())
                return 1;
            else if (o1.getPrice() == o2.getPrice())
                return 0;
            else
                return -1;
        }
    }
}
