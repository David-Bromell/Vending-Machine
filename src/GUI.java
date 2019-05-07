
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javax.swing.JOptionPane.showMessageDialog;

public class GUI extends Application {

    public StackPane stackPane;
    Scene scene, scene1, scene2, scene3, scene4, scene5, scene6, scene7;
    Login login = new Login();
    public String TypeOfUser = "";

  
    public static void main(String[] args) {
        launch(args);
    }

    //   @Override
    @Override
    public void start(Stage window) {

        stackPane = new StackPane();
        VBox opt = new VBox();

        //Buttons for user and admin login screen
        Button userButton = new Button("User Login Screen");
        Button adminButton = new Button("Admin Login Screen");

        opt.getChildren().addAll(userButton, adminButton);
        scene = new Scene(opt, 600, 400);
//if the user type is a regular user brings them to scene 1 (login screen) 
        userButton.setOnAction((ActionEvent e) -> {
            TypeOfUser = "User";
            window.setScene(scene1);
        });
        // if usertype is admin brings them to scene 1 (login screen) 
        adminButton.setOnAction((ActionEvent e) -> {
            TypeOfUser = "Admin";
            window.setScene(scene1);
        });

        //SCENE1 Screen Layout - USER LOGIN 
        VBox userLayout = new VBox();
        //username label
        Label labelUserName = new Label("Username");
        //where user enters username
        TextField userLoginField = new TextField();
        //password label
        Label labelPassword = new Label("Password");
        //where user enters password
        TextField userPasswordField = new TextField();
        //login button
        Button loginButton = new Button("Login");
          // username and password input are taken from input field
        loginButton.setOnAction((ActionEvent e) -> {
            String username = userLoginField.getText();
            String password = userPasswordField.getText();
               // sets the textfields to be empty after login
            userLoginField.setText("");
            userPasswordField.setText("");
            window.setScene(scene1);
            window.close();
            //here username, password and usertype are examined from login
            String quit = login.LoginUser(username, password, TypeOfUser);
            //failsafe if wrong username or password are entered 
            if (quit.equals("Invalid")) {
                showMessageDialog(null, "Invalid Credentials.");
            }
            window.show();
        });

        //adds all of the above to the scene
        userLayout.getChildren().addAll(labelUserName, userLoginField, labelPassword, userPasswordField, loginButton);
        //lets scene 1 = to the corresponing userLayout
        scene1 = new Scene(userLayout, 600, 400);

        //UNFORTUNATELY I DID NOT HAVE TIME TO FINISH IMPLEMENTING THE FUNCTIONS TO MY GUI BUTTONS, HOWEVER THE BELOW CODE IS HOW I WANTED THE ENTIRE GUI TO LOOK LIKE
        //SCENE3 LAYOUT - SCREEN USER SELECT SCREEN
        //User clicks to logout of application
        HBox userSelect = new HBox();
        userSelect.setAlignment(Pos.CENTER);
        userSelect.setSpacing(15);
        Button userLogOutButton = new Button("Logout");
        //userLogOutButton.setOnAction(e -> window.setScene(scene));

        //SCENE2 Screen Layout
        //User clicks to buy product an is brought to new screen
        Button buy = new Button("Buy Product");
        buy.setOnAction(e -> window.setScene(scene5));

        //User clicks to display prouct and is brought to new screen
        Button displayUser = new Button("Display Products");

        displayUser.setOnAction(e
                -> window.setScene(scene6));

        //adds all of the above to the scene
        userSelect.getChildren()
                .addAll(userLogOutButton, buy, displayUser);
        //lets scene 3  = to the corresponing userLayout
        scene3 = new Scene(userSelect, 600, 400);

        //SCENE 4 LAYOUT - SCREEN ADMIN SELECT SCREEN 
        //sets out button layout
        HBox adminSelect = new HBox();

        adminSelect.setAlignment(Pos.CENTER);

        adminSelect.setSpacing(
                15);
        // logout button
        Button adminLogOutButton = new Button("Logout");
        //userLogOutButton.setOnAction(e -> window.setScene(scene));

        //User clicks to display prouct and is brought to new screen
        Button displayAdmin = new Button("Display Products");

        displayAdmin.setOnAction(e
                -> window.setScene(scene6));
        //Admin clicks to add a new product an is brought to a new screen
        Button addProduct = new Button("Add Product");

        addProduct.setOnAction(e
                -> window.setScene(scene7));
        //Admin clicks to shut down the machine
        Button shutDown = new Button("Shutdown Machine");
        //adds all of the above to the scene

        adminSelect.getChildren()
                .addAll(adminLogOutButton, displayAdmin, addProduct, shutDown);
        //lets scene 4 = to the corresponing userLayout
        scene4 = new Scene(adminSelect, 600, 400);

        //SCENE 5 LAYOUT SCREEN - USER BUY SCREEN
        //admin login buttonc
        HBox userBuy = new HBox();

        userBuy.setAlignment(Pos.CENTER);

        userBuy.setSpacing(
                15);
        Label buyLabel = new Label("Please type the products location");
        //where user enters username
        TextField locationInputField = new TextField();
        // logout button
        Button confirmBuy = new Button("Buy");
        confirmBuy.setOnAction((ActionEvent e) -> {
            String in = locationInputField.getText();
        });
        userLogOutButton.setOnAction(e
                -> window.setScene(scene3));

        //adds all of the above to the scene
        userBuy.getChildren()
                .addAll(buyLabel, locationInputField, confirmBuy);
        //lets scene 4 = to the corresponing userLayout
        scene5 = new Scene(userBuy, 600, 400);

        //SCENE 6 LAYOUT SCREEN -DISPLAY PRODUCT
        //admin login buttonc
        HBox display = new HBox();

        display.setAlignment(Pos.CENTER);

        display.setSpacing(
                15);

        //adds all of the above to the scene
        //lets scene 4 = to the corresponing userLayout
        scene6 = new Scene(display, 600, 400);

        //SCENE 7 Screen Layout - ADD PRODUCt
        VBox addLayout = new VBox();

        //username label
        Label labelProductName = new Label("Product Name");
        //where user enters username
        TextField productNameField = new TextField();
        //password label
        Label labelProductLocation = new Label("Product Location");
        //where user enters password
        TextField productLocationField = new TextField();
        //login button
        Label labelProductPrice = new Label("Product Price");
        //where user enters username
        TextField productPrice = new TextField();
        //password label
        Label labelProductQuantity = new Label("Product Quantity to add");
        //where user enters password
        TextField productQuantityField = new TextField();

        Button adminAdd = new Button("Add Product");

        adminAdd.setOnAction(e
                -> window.setScene(scene4));
        //backbutton
        Button adminBackAdd = new Button("Back");

        adminAdd.setOnAction(e
                -> window.setScene(scene4));
        //adds all of the above to the scene
        addLayout.getChildren()
                .addAll(labelProductName, productNameField, labelProductLocation, productLocationField, labelProductPrice, productPrice, labelProductQuantity, productQuantityField, adminAdd, adminBackAdd);
        //lets scene 2 = to the corresponing userLayout
        scene7 = new Scene(addLayout, 600, 400);

        //  scene = new  Scene(vbox,600,400); 
        VBox vbox = new VBox(600 / 250);

        vbox.setSpacing(
                20); // 20 is the spacing between elements in the VBox
        vbox.setAlignment(Pos.CENTER);

        vbox.getChildren()
                .addAll(userButton, adminButton);

        stackPane.getChildren()
                .add(vbox);
        StackPane.setAlignment(vbox, Pos.CENTER);

        window.setScene(
                new Scene(stackPane));
        window.show();

        window.setTitle(
                "Vending Machine Login");

    }

}
