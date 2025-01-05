import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FileOut {

    String fileType;
    static String fileName = "AccountInfo";
    private int checkingBalance;
    private int savingBalance;
    private String userName;
    private String passWord;

    // constructor
    public FileOut(int checkingBalance, int savingBalance, String userName, String passWord) {
        this.checkingBalance = checkingBalance;
        this.savingBalance = savingBalance;
        this.userName = userName;
        this.passWord = passWord;
    }

    //default constructor
    FileOut(AccountInfo account) {
        this.fileType = ".txt";
        this.checkingBalance = account.getCheckingBalance();
        this.savingBalance = account.getSavingBalance();
        this.userName = account.getUserName();
        this.passWord = account.getPassWord();
    }

    /* This method will create a ".txt" file that will hold the users account information. This
    * method is called when you create an account and when you update the account.*/
    public void createAccountFile(AccountInfo account) {
        String fileNameType = account.getUserName()+"_"+this.fileName + this.fileType;
        try {
            FileWriter file = new FileWriter(fileNameType);
            file.write(account.getUserName()+ "\n");
            file.write(account.getPassWord() + "\n");
            file.write(Integer.toString(account.getCheckingBalance()) + "\n");
            file.write(Integer.toString(account.getSavingBalance()) + "\n");
            file.close();
        } catch (Exception e) {
            System.out.println("File didn't write");
        }
    }
    /*readAccountFile - This method will read the info in the account file and save the values to an ArrayList*/
    public ArrayList readAccountFile(AccountInfo account) throws IOException {
        File fileName = new File(account.getUserName()+"_"+this.fileName + this.fileType);
        ArrayList value = new ArrayList();

        try {
            Scanner findValue = new Scanner(fileName);
            while (findValue.hasNext()) {
                value.add(findValue.next());
                //System.out.println("scanner "+value);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File error");
        }
        return value;
    }
    /*createReceipt - This method will create a receipt with all the transactions that happened while the
    * user was logged in. */
    public void createReceipt(AccountInfo account) throws IOException {
        String fileNameType = account.getUserName() +"_Receipt"+ this.fileType;
        FileWriter receiptFile = new FileWriter(fileNameType);
        receiptFile.write("Your Bank Receipt\n");
        receiptFile.write("Username:" +account.getUserName()+"\n");
        receiptFile.write("Starting checking Balance: $"+Login.beginningCheckingBalance+"\n");
        receiptFile.write("Starting savings Balance: $"+Login.beginningSavingBalance+"\n");
        for(int i=0;i < account.receiptArray.size();i++){
            receiptFile.append((CharSequence) account.receiptArray.get(i));
        }
        receiptFile.write("Ending Checking Balance: $"+account.getCheckingBalance()+"\n");
        receiptFile.write("Ending Saving Balance: $"+account.getSavingBalance());
        receiptFile.close();
    }
}




