
/**
 * A product in a vending machine.
 */
public class Product {
// 4 criteria required for a product are defined
    private final String name;
    private final String location;
    private float price;
    private int quantity;

    //constructor
    public Product(String name, String location, float price, int quantity) {
        //objects being invoked
        this.name = name;
        this.location = location;
        this.price = price;
        this.quantity = quantity;
    }

    //Getters and Setters.
    public String getName() {
        return this.name;
    }

    public String getLocation() {
        return this.location;
    }

    public float getPrice() {
        return this.price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setQuantity(int q) {
        this.quantity = q;
    }
}
