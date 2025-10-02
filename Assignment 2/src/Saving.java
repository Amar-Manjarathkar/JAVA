public class Saving extends Account {
    public Saving(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public void withdraw(double amount) {
        // Corrected Logic: Check if the balance AFTER withdrawal will be >= 10000
        if ((balance - amount) >= 10000) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            // Provide feedback to the user
            System.out.println("Withdrawal failed. Minimum balance of 10000 must be maintained.");
        }
    }

    @Override
    public void deposit(double amount) {
        // This logic was correct, but feedback is added.
        if (amount > 0 && amount <= 100000) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit failed. Amount must be between 0 and 100000.");
        }
    }
}