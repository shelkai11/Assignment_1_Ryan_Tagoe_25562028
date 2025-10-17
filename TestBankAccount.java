public class TestBankAccount {
    public static void main(String[] args){
        // Using objects to test
        BankAccount studentacct = new BankAccount(BankAccount.AccountType.SAVINGS, "12345678");
        BankAccount businessacct = new BankAccount(BankAccount.AccountType.CURRENT, "14255688", 1500.12);

        // Check initial balamce
        System.out.println(studentacct.getBalance());
        System.out.println(businessacct.getBalance());

        //Test deposit
        businessacct.deposit(100);
        studentacct.deposit(100);
        System.out.println("Balance after deposit: " + studentacct.getBalance());
        System.out.println("Balance after deposit: " + businessacct.getBalance());

        //Check withdrawal
        businessacct.withdraw(1000);
        studentacct.withdraw(1000);
        System.out.println("Balance after withdrawal: " + studentacct.getBalance());
        System.out.println("Balance after withdrawal: " + businessacct.getBalance());

        //Test transfer and see
        System.out.println("Testing your transfer");
        boolean succtransfer = businessacct.transfer(true, studentacct, 200);
        System.out.println("Is your transfer successful? " + succtransfer);
        System.out.println("Balance After Withdrawal: " + studentacct.getBalance());
        System.out.println("Balance After Transfer: " + businessacct.getBalance());

        // Do Monthly Maintenace
        System.out.println("Checking Monthly Maintenance");
        businessacct.performMonthlyMaintenance();
        studentacct.performMonthlyMaintenance();



    }


}
