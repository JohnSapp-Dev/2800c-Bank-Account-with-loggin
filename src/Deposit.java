import java.util.Scanner;

public class Deposit extends Transactions{

    static Scanner userInput = new Scanner(System.in);
    int checking;
    int savings;
    int  depositChecking;
    int depositSavings;

    //constructor
    public Deposit(AccountInfo account){
        this.checking = account.getCheckingBalance();
        this.savings = account.getCheckingBalance();
    }
    /*updateChecking - This method takes in the amount to deposit, updates the amount in checking and adds the
     * transaction to the receipt array*/
    @Override
    public void updateChecking(AccountInfo account) {
        System.out.println("How much do you want to deposit into checking?");
        String selection = userInput.nextLine();
        try{
            depositChecking = Math.abs(Integer.parseInt(selection));
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error.");

        }
        account.setCheckingBalance(account.getCheckingBalance()+depositChecking);
        account.addReceiptArray("Deposit into Checking: $", String.valueOf(depositChecking));
        System.out.println("Your new Checking balance is: $"+account.getCheckingBalance());
    }
    /*updateSavings - This method takes in the amount to deposit, updates the amount in savings and adds the
     * transaction to the receipt array*/
    @Override
    public void updateSavings(AccountInfo account) {
        System.out.println("How much do you want to deposit into savings?");
        String selection = userInput.nextLine();
        try{
            depositSavings = Math.abs(Integer.parseInt(selection));
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error.");

        }
        account.setSavingBalance(account.getSavingBalance()+depositSavings);
        account.addReceiptArray("Deposit into Saving: $", String.valueOf(depositSavings));
        System.out.println("Your new Savings balance is: $"+account.getSavingBalance());
    }
}
