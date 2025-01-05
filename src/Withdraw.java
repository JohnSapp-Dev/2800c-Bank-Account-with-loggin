import java.util.Scanner;

public class Withdraw extends Transactions{
    static Scanner userInput = new Scanner(System.in);
    int checking;
    int savings;
    int  withdrawChecking;
    int withdrawSavings;

    //constructor
    public Withdraw(AccountInfo account){
        this.checking = account.getCheckingBalance();
        this.savings = account.getCheckingBalance();
    }
    /*updateChecking - This method takes in the amount to withdraw, updates the amount in checking and adds the
    * transaction to the receipt array*/
    @Override
    public void updateChecking(AccountInfo account) {
        System.out.println("How much do you want to withdraw from checking? Max amount: $"+ account.getCheckingBalance());
        String selection = userInput.nextLine();
        try{
            withdrawChecking = Math.abs(Integer.parseInt(selection));
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error.");

        }
        if (withdrawChecking <= account.getCheckingBalance()) {
            account.setCheckingBalance(account.getCheckingBalance()-withdrawChecking);
            account.addReceiptArray("Withdraw from Checking: $",String.valueOf(withdrawChecking));
            System.out.println("Your new Checking balance is: $"+account.getCheckingBalance());
        }
        else{
            System.out.println("Insufficient funds ");
        }
    }
    /*updateSavings - This method takes in the amount to withdraw, updates the amount in Savings and adds the
     * transaction to the receipt array*/
    @Override
    public void updateSavings(AccountInfo account) {
        System.out.println("How much do you want to withdraw from savings? Max amount: $"+account.getSavingBalance());
        String selection = userInput.nextLine();
        try{
            withdrawSavings = Math.abs(Integer.parseInt(selection));
        }
        catch (NumberFormatException nfe){
            System.out.println("There was an error.");

        }
        if (withdrawSavings <= account.getSavingBalance()) {
            account.setSavingBalance(account.getSavingBalance()-withdrawSavings);
            account.addReceiptArray("Withdraw from Savings: $",String.valueOf(withdrawSavings));
            System.out.println("Your new Savings balance is: $"+account.getSavingBalance());
        }
        else{
            System.out.println("Insufficient funds ");
        }
    }
}
