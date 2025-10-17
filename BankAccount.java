// Ryan Tagoe
// OOP
// Assignment 1: Testing BankAccount Class
// 17/10/2028
public class BankAccount {

    // Enums
    public enum AccountType {
        CURRENT, SAVINGS
    }
    // Constants
    public static final double CURRENT_ACCT_MIN_BALANCE = 300.0;
    public static final double SAVINGS_ACCT_MIN_BALANCE = 700.0;
    public static final double CURRENT_ACCT_MAINTENANCE_FEE = 50.0;
    public static final double SAVINGS_ACCT_INTEREST_RATE = 0.03;
    public static final int SAVINGS_WITHDRAWAL_LIMIT = 2;

    // Data Fields (Attributes)
    private AccountType acctType;
    private String acctID;
    private double balance;
    private int numWithdrawals;
    private boolean inTheRed;
    private double minBalance;
    private double interestRate;
    private double maintenanceFee;
    private int withdrawalLimit;

    // Constructor 1
    public BankAccount(AccountType acctType, String id) {
        this.acctType = acctType;
        this.acctID = id;
        balance = 0;
        numWithdrawals = 0;

        //Checking validity of account
        if (acctType == AccountType.SAVINGS) {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            inTheRed = balance < minBalance;
        } else {
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
            inTheRed = balance < minBalance;
        }
    }

    //Constructor 2
    public BankAccount(AccountType acctType, String id, double openbalance) {
        this.acctType = acctType;
        this.acctID = id;
        this.balance = openbalance;
        numWithdrawals = 0;
        // Setting accountType
        if (acctType == AccountType.SAVINGS) {
            minBalance = SAVINGS_ACCT_MIN_BALANCE;
            interestRate = SAVINGS_ACCT_INTEREST_RATE;
            maintenanceFee = 0;
            withdrawalLimit = SAVINGS_WITHDRAWAL_LIMIT;
            inTheRed = balance < minBalance;
        } else {
            minBalance = CURRENT_ACCT_MIN_BALANCE;
            interestRate = 0;
            maintenanceFee = CURRENT_ACCT_MAINTENANCE_FEE;
            withdrawalLimit = -1;
            inTheRed = balance < minBalance;
        }
    }

    //Getters for Public view
    public AccountType getAccountType() {
        return acctType;
    }

    public String getAccountID() {
        return acctID;
    }

    public double getMinBalance() {
        return minBalance;
    }

    //setters for public edit
    public void setAccountTyp(AccountType acctType) {
        this.acctType = acctType;
    }

    public void setAccountID(String acctID) {
        this.acctID = acctID;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setNumofwithdrawals(int numWithdrawals) {
        this.numWithdrawals = numWithdrawals;
    }

    //Withdraw method
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("You cannot withdraw");
            return false;
        }

        if (withdrawalLimit != -1 && numWithdrawals >= withdrawalLimit) {
            System.out.println("You cannot withdraw");
            return false;
        }

        if (inTheRed) {
            System.out.println("You cannot withdraw");
            return false;
        }

        if ((this.balance - amount) < minBalance) {
            System.out.println("You cannot withdraw");
            return false;
        }

        balance -= amount;
        numWithdrawals++;
        inTheRed = balance < minBalance;
        return true;
    }

    public double getBalance() {
        return balance;
    }

    //Deposit method and checking validity
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Error");
            return;
        }

        balance += amount;

        if (balance >= minBalance) {
            inTheRed = false;
        }

        double interest = (balance * interestRate) / 12;
        balance += interest;
        balance -= maintenanceFee;

        if (balance < minBalance) {
            inTheRed = true;
        } else {
            inTheRed = false;
        }
        numWithdrawals = 0;

        System.out.println("Bank Account Details");
        System.out.println("Interest earned: " + interest);
        System.out.println("Maintenance fee: " + maintenanceFee);
        System.out.println("Balance: " + balance);
        if (inTheRed) {
            System.out.println("CAUTION: Your account is in the red");
        }
    }
//Transfer
    public boolean transfer(boolean transferTo, BankAccount otheracct, double amount) {
        if (transferTo) {
            if (this.withdraw(amount)) {
                otheracct.deposit(amount);
                return true;
            } else {
                return false;
            }
        } else {
            if (otheracct.withdraw(amount)) {
                this.deposit(amount);
                return true;
            } else {
                return false;
            }
        }
    }

    //Maintenance
    public void performMonthlyMaintenance() {
        double earnedInterest = balance * (interestRate / 2);
        balance += earnedInterest;
        balance -= maintenanceFee;

        if (balance < minBalance) {
            inTheRed = true;
        } else {
            inTheRed = false;
        }
        numWithdrawals = 0;

        System.out.println("Interest earned: " + earnedInterest);
        System.out.println("Earned maintenance: " + maintenanceFee);
        System.out.println("Balance: " + balance);

        if (inTheRed) {
            System.out.println("Your account is in the red");
        }
    }
}
