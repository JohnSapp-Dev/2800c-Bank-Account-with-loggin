import java.lang.reflect.Array;
import java.util.ArrayList;
/*Transactions class is an abstract class that in ensures any child classes implement the two abstract methods*/
public abstract class Transactions {

    int checking;
    int savings;
    ArrayList receiptInfo = new ArrayList<>();
    public abstract void updateChecking(AccountInfo account);
    public abstract void updateSavings(AccountInfo account);


}
