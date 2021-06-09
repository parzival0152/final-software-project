public class Product {
    private String name;
    private double price;
    private int amount;
    private int level;

    // Get and set functions
    public String getName() {
        return name;
    }
    // no name setter as item name shouldn't change after construction

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString()
    {
        return("Product name - " + this.getName() + "\nProduct Price - " + Double.toString(this.getPrice()) + "\nIn stock - " + 
        Integer.toString(this.getAmount()));
    }

    public Product(String name, double price, int amount, int level) {
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.level = level;
    }
}
