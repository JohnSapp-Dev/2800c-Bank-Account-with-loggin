import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Bank {

    static Scanner userInput = new Scanner(System.in);
    static AccountInfo myAccount;
    static FileOut accountFile;
    static Login accountLogin;
    static Withdraw myWithdraw;
    static Deposit myDeposit;
    static ArrayList receiptArray;
    static int firstMenu=0;
    static int menuNumber;

    static boolean banking = true;

    /*Displays a menu for the user to choose an option from*/
    public static int MainMenu(){
        if (firstMenu==0){
            System.out.println("\n\nWelcome to Your Account");
            firstMenu=1;
        }
        System.out.println("\t1 - View Balance");
        System.out.println("\t2 - Withdraw");
        System.out.println("\t3 - Deposit");
        System.out.println("\t4 - logout");
        String selection = userInput.nextLine();
        try{
            menuNumber = Integer.parseInt(selection);
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Please try again");
            MainMenu();
        }
        return menuNumber;

    }
    /*userSelection - This method takes the users menu chose and handles displaying the proper follow-up selections then
    * it call the proper function*/
    public static void usersSelection(int optionNumber,AccountInfo account,Withdraw myWithdraw,Deposit myDeposit) throws IOException {

        switch (optionNumber) {
            case 1:
                accountBalance();
                break;
            case 2:
                System.out.println("1 - Withdraw from Checking?");
                System.out.println("2 - Withdraw from Savings?");
                System.out.println("3 - Main Menu");
                String selection = userInput.nextLine();
                try{
                    menuNumber = Integer.parseInt(selection);
                }
                catch (NumberFormatException nfe){
                    System.out.println("There was an error. Returning to the Main Menu.");
                    MainMenu();
                }
                if (menuNumber ==1){
                    myWithdraw.updateChecking(account);
                }else if (menuNumber ==2){
                    myWithdraw.updateSavings(account);
                }else{
                    usersSelection(MainMenu(),myAccount,myWithdraw,myDeposit);
                }
                break;
            case 3:
                System.out.println("1 - Deposit into Checking?");
                System.out.println("2 - Deposit into Savings?");
                System.out.println("3 - Main Menu");
                selection = userInput.nextLine();
                try{
                    menuNumber = Integer.parseInt(selection);
                }
                catch (NumberFormatException nfe){
                    System.out.println("There was an error. returning to the Main Menu.");
                    MainMenu();
                }
                if (menuNumber ==1){
                    myDeposit.updateChecking(account);
                }else if (menuNumber ==2){
                    myDeposit.updateSavings(account);
                }else{
                    if(menuNumber>3){
                        System.out.println("There was an error. returning to the Main Menu.");
                    }
                    usersSelection(MainMenu(),myAccount,myWithdraw,myDeposit);
                }
                break;

            case 4:
                System.out.println("Thank you for banking with us.");
                System.out.println("1 - print receipt");
                System.out.println("2 - Quit");
                selection = userInput.nextLine();
                accountFile.createAccountFile(myAccount);
                try{
                    menuNumber = Integer.parseInt(selection);
                }
                catch (NumberFormatException nfe){
                    System.out.println("There was an error. returning to the Main Menu.");
                    MainMenu();
                }
                if (menuNumber ==1){
                    accountFile.createReceipt(myAccount);
                }
                banking = false;

        }
        if (optionNumber<=0 || optionNumber>4){
            System.out.println("Invalid option. Try again.");
        }
    }

    /*accountBalance - prints to the terminal the current balance of both accounts*/
    public static void accountBalance() throws IOException {
        System.out.println("Checking Account: "+myAccount.getCheckingBalance());
        System.out.println("Savings Account: "+myAccount.getSavingBalance()+"\n");
        System.out.println("1 - Return to Menu");
        System.out.println("2 - Logout");
        String selection = userInput.nextLine();
        try{
            menuNumber = Integer.parseInt(selection);
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error. Returning to the Main Menu.");
            MainMenu();
        }
        if (menuNumber ==1){
            usersSelection(MainMenu(),myAccount,myWithdraw,myDeposit);
        }
        else{
            usersSelection(4,myAccount,myWithdraw,myDeposit);
        }

    }

    public static void main(String[] args) throws IOException {
        // creates most of the objects needed
       myAccount = new AccountInfo();
       accountFile = new FileOut(myAccount);
       accountLogin = new Login();
       myWithdraw = new Withdraw(myAccount);
       myDeposit = new Deposit(myAccount);
       receiptArray = new ArrayList<>();

       boolean loggedIn;
       loggedIn = accountLogin.loginMessage(myAccount,accountFile);

        while(banking){
            if(loggedIn){
                usersSelection(MainMenu(),myAccount,myWithdraw,myDeposit);
            }
        }
    }


}