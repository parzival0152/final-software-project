import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;



public class RoomService implements  Printable {
    private static ArrayList<Product> items = new ArrayList<Product>();
    private static ArrayList<String> items_string = new ArrayList<String>();
    private static ArrayList<String> rooms = new ArrayList<String>();
    


    MaContinental managment;
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

    public void run() {

        String name;
        double price=-1;
         int amount=-1;
         int level=-1;
         int check,row,column;
         boolean flag;

           
            boolean quit = false;
            while (!quit) {
                int option=UI.askOption(
                "add product",
                "remove product",
                " product details",
                 "order room service",
                  "add to stock",
                  "sort by price",
                  "show stock",
                  "Exit");
                  
                switch (option) {
                    case 1:
                        name=UI.askString("Enter product name:");
                        while(price<0)
                        {
                            price=UI.askNum("Enter price:");
                        }
                        while(amount<0)
                        {
                            amount=UI.askNum("Enter amount:");
                        }
                        while(level<0)
                        {
                           level=UI.askNum("Enter 1-for regular menue\n 2-for gold menue\n3-for platinum menue:");

                        }
                        check=addProduct( name,  price,  amount,  level);
                        if (check==1)
                        items_string.add(name);
                        price=-1;
                        amount=-1;
                        level=-1;
        

                        break;
                    case 2:
                    name=UI.askString("Enter product name:");
                    check=removeProduct(name);
                    if(check!=-1)
                      items_string.remove(check);

                        break;
                    case 3:
                    name=UI.askString("Enter product name:");
                    int index=ifExist_general(name);
                    if(index!=-1)
                    {
                        items.get(index).toString();
                    }
                    else
                       UI.showString("product not found");
                        break;
                    case 4:
                    name=UI.askString("Enter product name:");
                    index=ifExist_general(name);
                    if(index==-1)
                    {
                       UI.showString("product not found");
                        break;
                    }
                     option=UI.askOption(items_string);
                     amount=UI.askNum("Enter amount:");
                     if(items.get(option-1).getAmount()<amount)
                    {
                     UI.showString("not enough in stock");
                    break;
                    }
                    rooms=managment.availableRooms();
                    option=UI.askOption(managment.availableRooms());
                    row=Integer.parseInt(rooms.get(option-1))/100;
                    column=Integer.parseInt(rooms.get(option-1))%100;
                    flag=managment.buyProduct(name, amount, row, column);
                    amount=-1;
                    if(flag==true)
                       {
                          UI.showString("buy end successfully");
                          items.get(option-1).setAmount(items.get(option-1).getAmount()-amount);

                       }
                    else
                    UI.showString("buy failed,the guest does'nt has access to this menue");
                        break;
                    case 5:
                    name=UI.askString("Enter product name:");
                    if(ifExist_general(name)!=-1)
                       {
                        
                            amount=UI.askNum("Enter amount:");
                            reStock( name,  amount);
                            amount=-1;
                       
                       }
                    else
                       UI.showString("product not found");
                        
                        break;
                    case 6:
                        sortPrice();
                        break;

                    case 7:
                        showData();
                        break;
                   
                    case 8:
                        quit = true;
                        break;
                    default:
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
        return 0;
    }

    // remove a product from the items list
    public int removeProduct(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
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
            if (items.get(i).getName().equals(name) && items.get(i).getLevel() >= level
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
    }

    public void sortAmount() {
        Collections.sort(items, new AmountComperator());
    }

    public void sortPrice() {
        Collections.sort(items, new PriceComperator());
    }

    @Override
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

    class AmountComperator implements Comparator<Product> {

        @Override
        public int compare(Product o1, Product o2) {
            if (o1.getAmount() > o2.getAmount())
                return 1;
            else if (o1.getAmount() == o2.getAmount())
                return 0;
            else
                return -1;
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
