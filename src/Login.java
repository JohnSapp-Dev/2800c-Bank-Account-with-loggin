import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class Login {
    static Scanner userInput = new Scanner(System.in);
    static int menuNumber;
    static String userName;
    static String passWord;
    static String loginUserName;
    static String loginPassWord;
    static int beginningCheckingBalance;
    static int beginningSavingBalance;
    // constructor
    public Login(){

    }

    /*loginMessage - This method takes in an AccountInfo object and a FileOut object. The method prompts the
    * user to make a selection and then calls either the login method or the createLogin method. */
    public boolean loginMessage(AccountInfo account,FileOut file) throws IOException {
        boolean isTrue = false;
        System.out.println("Welcome to your bank. ");
        System.out.println("Login or if you're new, Create an Account");
        System.out.println(" 1 - Login");
        System.out.println(" 2 - Create Account");
        String selection = userInput.nextLine();
        try{
            menuNumber = Integer.parseInt(selection);
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Please try again");
            loginMessage(account,file);
        }
        if (menuNumber == 1){
            if(login(account,file)){
                isTrue = true;
            }
        }
        else if (menuNumber == 2){
            if(createLogin(account,file)){
                isTrue = true;
            }
        } return isTrue;
    }

    /*createLogin - The method creates a user login by generating a random amount of money for
    * both their Checking account and Savings account. The method then asks the user to input a username
    * and password. The method then passes this info to the AccountInfo account object. A file is then
    * created with the same information. */
    public boolean createLogin(AccountInfo account,FileOut file) throws IOException {
        boolean loginSuccessful = false;
        Random randomNumber = new Random();
        int savingsAccountBalance = randomNumber.nextInt(1000,25000);
        int checkingAccountBalance = randomNumber.nextInt(1000,10000);

        System.out.println("Enter your Account User Name");
        try{
            userName = userInput.nextLine();
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Please try again");
            createLogin(account,file);
        }
        System.out.println("Enter your Account Password");
        try{
            passWord = userInput.nextLine();
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Please try again");
            createLogin(account,file);
        }
        account.setSavingBalance(savingsAccountBalance);
        account.setCheckingBalance(checkingAccountBalance);
        account.setUserName(userName);
        account.setPassWord(passWord);
        file.createAccountFile(account);
        if (login(account,file)){
            loginSuccessful = true;
        }
        return loginSuccessful;
    }

    /*login - This method allows a user to log into an existing account. The user is asked for their username and
    * password. That info is checked against the account file*/
    public boolean login(AccountInfo account,FileOut file) throws IOException {
        boolean loginSuccessful = false;

        while(!loginSuccessful){
            System.out.println("Please log into your Account\n\n");
            System.out.println("Please enter your User Name");
            try{
                loginUserName = userInput.nextLine();
                account.setUserName(loginUserName);
            }
            catch (NumberFormatException nfe){
                System.out.println("There was an error.");
            }
            System.out.println("Please enter your Password");
            try{
                loginPassWord = userInput.nextLine();
                account.setPassWord(loginPassWord);
            }
            catch (NumberFormatException nfe){
                System.out.println("There was an error.");
            }

            if (loginUserName.equals(file.readAccountFile(account).get(0)) && loginPassWord.equals(file.readAccountFile(account).get(1))) {
                System.out.println("Login successful!");
                account.setSavingBalance(Integer.parseInt((String) file.readAccountFile(account).get(2)));
                account.setCheckingBalance(Integer.parseInt((String) file.readAccountFile(account).get(3)));
                account.setUserName(loginUserName);
                account.setPassWord(loginPassWord);
                this.beginningCheckingBalance = account.getCheckingBalance();
                this.beginningSavingBalance = account.getSavingBalance();
                loginSuccessful = true;
            }
            else {
                System.out.println("Login unsuccessful, Please try again.");
                loginSuccessful = false;
            }
        }
        return loginSuccessful;
    }

}
