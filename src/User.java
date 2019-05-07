
public class User {

    //the username 
    protected String userName;
    //users balance
    protected float balance = 0;
    //the usertype 
    public String usertype = "Normal";

    
    //constructor  which en
    public User(String un, float balance) {
        this.userName = un;
        this.balance = balance;
    }
//getter
    public float getBalance() {
        return this.balance;
    }
//getter
    public String getName() {
        return this.userName;
    }
// balance setter
    public void setBalance(float balance) {
        this.balance = balance;
    }
// this is called by login to display what type of user is logged in 
    public void whoAmI() {
        System.out.println("You Are Logged in as a  " + this.usertype + " User");
    }

}
