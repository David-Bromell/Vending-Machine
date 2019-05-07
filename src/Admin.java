

public class Admin extends User {
   // This is the super class of user.
    public Admin(String UN, float balance) {
        super(UN, balance);
        this.usertype = "Admin";
    }
}



