public class Current extends Account {
    public Current(int accNo, String name, double balance) {
        super(accNo, name, balance);
    }

    @Override
    public void withdraw(double amount) {
        // Corrected Logic: Check both the withdrawal limit AND if funds are sufficient.
        if (amount > 0 && amount <= 50000) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Withdrawal failed. Insufficient funds.");
            }
        } else {
            System.out.println("Withdrawal failed. Amount must be between 0 and 50000.");
        }
    }

    @Override
    public void deposit(double amount) {
        // This logic was correct, but feedback is added.
        if (amount > 0 && amount <= 500000) {
            balance += amount;
            System.out.println("Deposit successful. New balance: " + balance);
        } else {
            System.out.println("Deposit failed. Amount must be between 0 and 500000.");
        }
    }
}