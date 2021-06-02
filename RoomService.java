import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class RoomService{
    private static ArrayList<Product> items = new ArrayList<Product>();

    RoomService()
    {
        addProduct("Water", 10, 30, 1);
        addProduct("Chocolate", 15, 30, 1);
        addProduct("Chips", 15, 30, 1);
        addProduct("Fruit Basket", 45, 15, 2);
        addProduct("Wine", 55, 10, 2);
        addProduct("Champagne", 70, 10, 3);
        addProduct("Caviar", 50, 5, 3);
    }

    //functions
    public void addProduct(String name, double price, int amount, int level)
    {
        if(!isDup(name))
        {
            Product p = new Product(name, price, amount, level);
            items.add(p);
        }
    }

    public void removeProduct(String name)
    {
        for(int i=0; i<items.size(); i++)
        {
            if (items.get(i).getName()==name)
            {
                items.remove(i);
                break;
            }
        }

    }

    public static int getAmount(String name)
    {
        for(int i=0; i<items.size(); i++)
        {
            if (items.get(i).getName()==name)
            {
                int tmp = items.get(i).getAmount();
                return tmp;
            }
        }
        return -1;
    }

    public static boolean exists(String name, int level)
    {
        for(int i=0; i<items.size(); i++)
        {
            if (items.get(i).getName()==name && items.get(i).getLevel()>=level && items.get(i).getAmount()!=0)
            {
                return true;
            }
        }
        return false;
    }

    public static double getPrice(String name)
    {
        for(int i=0; i<items.size(); i++)
        {
            if (items.get(i).getName()==name)
            {
                return items.get(i).getPrice();
            }
        }
        return -1;
    }

    public boolean isDup(String name)
    {
        for(int i=0; i<items.size(); i++)
        {
            if (items.get(i).getName()==name)
            {
                return true;
            }
        }
        return false;
    }

    //sorting
    public void sortName()
    {
        Collections.sort(items, new NameComperator());
    }
    public void sortAmount()
    {
        Collections.sort(items, new AmountComperator());

    }
    public void sortPrice()
    {
        Collections.sort(items, new PriceComperator());

    }

    //comperators
    class NameComperator implements Comparator<Product>{

        @Override
        public int compare(Product o1, Product o2) {
            return o1.getName().compareTo(o2.getName());
        }
    
    }

    class AmountComperator implements Comparator<Product>{

        @Override
        public int compare(Product o1, Product o2) {
            if(o1.getAmount()>o2.getAmount())
                return 1;
            else if(o1.getAmount()==o2.getAmount())
                return 0;
            else
                return -1;
        }
    }

    class PriceComperator implements Comparator<Product>{

        @Override
        public int compare(Product o1, Product o2) {
            if(o1.getPrice()>o2.getPrice())
                return 1;
            else if(o1.getPrice()==o2.getPrice())
                return 0;
            else
                return -1;
        }
    }


}
