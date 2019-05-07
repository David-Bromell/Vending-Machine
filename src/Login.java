
import java.util.Scanner;

public class Login {

    String UserFile = "C:\\Users\\David\\Documents\\NetBeansProjects\\Vending Machine Assignment Final\\src\\Users.dat";
    String AdminFile = "C:\\Users\\David\\Documents\\NetBeansProjects\\Vending Machine Assignment Final\\src\\Admin.dat";

    public String LoginUser(String username, String password, String usertype) {

       
        //LINK VENDING MACHINE
        VendingMachine vm = new VendingMachine();
        //Scanner opened
        Scanner in;

        in = new Scanner(System.in);
     

        // if the user type is an admin, the admin file is checked to see if username and password are correct
        FileReaderWriter frw;
        if (usertype.equals("Admin")) {
            frw = new FileReaderWriter(AdminFile);
        } else {
            frw = new FileReaderWriter(UserFile);
        }
        //authenticating username and password from file
        String line2 = frw.doesLineExistInFile(username);
        if (line2 != null) {
            //Splits the line to locate the password.
            if (line2.split(",")[1].equals(password)) {
                System.out.println("User " + username + " has been logged in.");
                User loggedIn;
                if (usertype.equals("Admin")) {
                    loggedIn = new Admin(username, 0);
                } else {
                    // if is regular user logging, an extra column for balance is present so this must be accounted for when reaing the file
                    loggedIn = new User(username, Float.parseFloat(line2.split(",")[2]));
                }
                // calls function from user to display what type of user is logged in 
                loggedIn.whoAmI();
                 //returns the mainMenu function (directly below)
                return mainMenu(loggedIn, in, vm, usertype);
                
            }
        }
        //failsafe if incorrect details
        return "Invalid";
    }

    private static String mainMenu(User loggedInUser, Scanner in, VendingMachine vm, String usertype) {
      //set up to keep it running
        String quit = null;
        while (quit == null) {

            // if user is admin displays the admin menu from function below
            if (usertype.equals("Admin")) {
                displayMenu(usertype);
                quit = getInputAdmin(loggedInUser, in, vm, usertype);
                // if user is a regular user displays menu from function below 
            } else if (usertype.equals("User")) {
                displayMenu(usertype);
                quit = getInputUser(loggedInUser, in, vm, usertype);
            }
        }
        return quit;
    }

    // if user in an admin their interactions with the program are dealt with here
    public static String getInputAdmin(User loggedInUser, Scanner in, VendingMachine vm, String usertype) {
        //scanner input used for case switch here
        String option = in.nextLine();
        switch (option) {

            //calls function to update product file on logot to record any changes from vending machine
            case "0": //Logout
                vm.writeDataToProductFile();
                return "Logout";
            case "1": //Display Product.
                 //calls function to display product list from vending machine
                vm.displayProductList();
                break;
            //if the user is an admin the can add a product, this is called from the prouct class
            case "2": //Add Product. //ADMIN ONLY.                   
                if (usertype.equals("Admin")) {
                    System.out.println("Enter your product name:");
                    String name = in.nextLine();
                    System.out.println("Enter product location:");
                    String loc = in.nextLine();
                    System.out.println("Enter product price:");
                    float price = in.nextFloat();
                    System.out.println("Enter product quantity:");
                    int quant = in.nextInt();
                    Product p = new Product(name, loc, price, quant);
                     //calls from vendingmachine class
                    vm.addProduct(p);
                    in.nextLine();
                    break;
                }
            // if user is admin can use this function to shut own the machine, also writes data to product file in order to account for any changes
            case "3": //Shut down machine. //ADMIN ONLY.
                if (usertype.equals("Admin")) {
                       //called from vending machine
                    vm.writeDataToProductFile();
                    System.exit(0);
                }
            default:// if a non-existant option is selected 
                System.out.println("Invalid Option Selected, Please type the number listed which corresponds to your choice");
        }
        return null;
    }

    // deals with regular users program input
    public static String getInputUser(User loggedInUser, Scanner in, VendingMachine vm, String usertype) {
        String option = in.nextLine();
        //scaner input use in the switch, the same as above
        switch (option) {
            case "0": //Logout
                //on logout the user file is reqritten to account for any changes (balance) 
                vm.writeDataToUserFile(loggedInUser);
                // product file updated to do the same, called from vending machine
                vm.writeDataToProductFile();
                return "Logout";
            case "1": //Buy Product.
                // user is allowed to buy a product based on its location
                System.out.println("Enter your products location:");
                String name1 = in.nextLine();
                //checks users balance and takes the product cost away from same
                float bal = loggedInUser.getBalance();
                loggedInUser.setBalance(bal - vm.getProductPrice(name1));
                //accounts for remaining products
                vm.remProduct(name1, 1);
                break;
            case "2": //Display Product.
                //displays available products in the machine 
                vm.displayProductList();
                break;
            default:// displayed if invalid choice is selected 
                System.out.println("Invalid Option Selected, Please type the number listed which corresponds to your choice");
        }
        return null;
    }
//display menu function as calle above, this is user dependant 

    private static void displayMenu(String usertype) {
//printed for both user types
        System.out.println("0 : Log Out");
//must be an admin to access this menu 
        if (usertype.equals("Admin")) {
            System.out.println("1 : Display Product");
            System.out.println("2 : Add Product");
            System.out.println("3 : Shut Down Machine");
            //regular user only 
        } else {
            System.out.println("1 : Buy Product");
            System.out.println("2 : Display Product");
        }

    }
}
