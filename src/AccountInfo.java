import java.util.ArrayList;

public class AccountInfo {

    private static int checkingBalance;
    private static int savingBalance;
    private String userName;
    private String passWord;
    static ArrayList receiptArray;

    //constructor
    public AccountInfo(int checkingBalance,int savingBalance,String userName, String passWord){
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
        this.userName = userName;
        this.passWord = passWord;
    }

    //default constructor
    public AccountInfo(){
        this.checkingBalance = 0;
        this.savingBalance = 0;
        this.userName = "NaN";
        this.passWord = "NaN";
        receiptArray = new ArrayList<>();
    }
    /*addReceiptArray - This method adds the passed values into the receipt Array*/
    public void addReceiptArray(String type, String amount){
        receiptArray.add(type);
        receiptArray.add(amount+"\n");
    }

    //Getters & Setters
    public int getCheckingBalance(){
        return this.checkingBalance;
    }

    public void setCheckingBalance(int balance){
        this.checkingBalance = balance;
    }
    public int getSavingBalance(){
        return this.savingBalance;
    }
    public void setSavingBalance(int balance) {
        this.savingBalance = balance;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getPassWord(){
        return this.passWord;
    }
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }


}
