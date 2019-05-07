
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A vending machine.
 */
public class VendingMachine {

    //product and user file location, admin not required as they cannot buy products
    public String product_location = "C:\\Users\\David\\Documents\\NetBeansProjects\\Vending Machine Assignment Final\\src\\Products.dat";
    String UserFile = "C:\\Users\\David\\Documents\\NetBeansProjects\\Vending Machine Assignment Final\\src\\Users.dat";
   
    //array list created to input proucts from .dat file
    public List<Product> productList = new ArrayList<>();

    //variable list assigned to data read from the product file
    public VendingMachine() {
        this.productList = this.readDataFromProductFile();
    }

    //goes through product.dat file in order to find price of product
    public float getProductPrice(String productLocation) {
        for (int i = 0; i < this.productList.size(); i++) {
            if (this.productList.get(i).getLocation().equals(productLocation)) {
                return this.productList.get(i).getPrice();
            }
        }
        return 0;
    }

    //reads data from product file and converts it to array list, this is comma delimited
    private List<Product> readDataFromProductFile() {
        List<String> rawData = new FileReaderWriter(this.product_location).readDataFromFile();
        List<Product> tempProductList = new ArrayList<>();
        for (String i : rawData) {
            Product p = new Product(i.split(",")[0], i.split(",")[1], Float.parseFloat(i.split(",")[2]), Integer.parseInt(i.split(",")[3]));
            tempProductList.add(p);
        }
        return tempProductList;
    }
//writes the data to the product file and updates it

    public void writeDataToProductFile() {
        try {
            FileReaderWriter frw = new FileReaderWriter(this.product_location);
            List<String> temp = new ArrayList<>();
            for (Product p : productList) {
                //uses the getter and setter methods from  product class, also adds a comma in between each.
                temp.add(p.getName() + "," + p.getLocation() + "," + p.getPrice() + "," + p.getQuantity());
            }
            frw.writeDataToFile(temp);
        } catch (IOException ex) {
             //getLogger method from java, used to log to file 
            Logger.getLogger(VendingMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //writes data to user file to update it 
    public void writeDataToUserFile(User u) {
        try {
            FileReaderWriter frw = new FileReaderWriter(this.UserFile);
            String temp = frw.doesLineExistInFile(u.getName());
            if (temp != null) {
                frw.appendDataToFile(u.getName(), u.getName() + "," + temp.split(",")[1] + "," + u.getBalance());
            }
        } catch (IOException ex) {
             //getLogger method from java, used to log to file 
            Logger.getLogger(VendingMachine.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //displays the products from array list
    public void displayProductList() {
        for (Product p : productList) {
             //uses getters an setters in product class
            System.out.println("Name : " + p.getName() + ",  Price : " + p.getPrice() + ",  Location : " + p.getLocation());
        }
    }

    //allows products to be added to the machine 
    public void addProduct(Product p) {
        //adds to arraylist
        productList.add(p);
    }

    //accounts for remaining products 
    public void remProduct(String location, int quantity) {
        for (Product p : productList) {
             //finds by location then subtracts quantity bought 
            if (p.getLocation().equals(location)) {
                int orig = p.getQuantity();
                p.setQuantity(orig - quantity);
            }
        }
    }
}
