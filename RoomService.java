import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoomService implements UIable {
    private static ArrayList<Product> items = new ArrayList<Product>();

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
    }

    public void run() {

        String name;
        double price=-1;
         int amount=-1;
         int level=-1;
           
            boolean quit = false;
            while (!quit) {
                int option=UIable.askOption(
                  "add product",
                "remove product",
                " product details",
                 "order room service",
                  "add to stock",
                  "sort by price",
                  "Exit");
                  
                switch (option) {
                    case 1:
                        name=UIable.askString("Enter product name:");
                        while(price<0)
                        {
                            price=UIable.askNum("Enter price:");
                        }
                        while(amount<0)
                        {
                            price=UIable.askNum("Enter amount:");
                        }
                        while(level<0)
                        {
                           level=UIable.askNum("Enter 1-for regular menue\n 2-for gold menue\n3-for platinum menue:");

                        }
                        addProduct( name,  price,  amount,  level);
                        price=-1;
                        amount=-1;
                        level=-1;
        

                        break;
                    case 2:
                    name=UIable.askString("Enter product name:");
                    removeProduct(name);

                        break;
                    case 3:
                    name=UIable.askString("Enter product name:");
                    
                       
                        break;
                    case 4:
                        
                        break;
                    case 5:
                        
                        break;
                    case 6:
                        
                        break;
                   
                    case 7:
                        quit = true;
                        break;
                    default:
                        break;
                }
              
            }
        





		
    }

   









    // add new product to items list
    public void addProduct(String name, double price, int amount, int level) {
        if (!isDup(name)) {
            Product p = new Product(name, price, amount, level);
            items.add(p);
        }
    }

    // remove a product from the items list
    public void removeProduct(String name) {
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getName().equals(name)) {
                items.remove(i);
                break;
            }
        }

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
                items.get(i).setAmount(amount);
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
