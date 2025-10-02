public abstract class Account {
    protected int accNo;
    protected String name;
    protected double balance;

    public Account(int accNo, String name, double balance) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accNo=" + accNo +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                '}';
    }

    // --- Abstract Methods ---

    /**
     * Abstract method to withdraw funds. Subclasses must implement their own
     * specific withdrawal logic (e.g., checking limits or minimum balance).
     *
     * @param amount The amount to withdraw.
     */
    public abstract void withdraw(double amount);

    /**
     * Abstract method to deposit funds. Subclasses must implement their own
     * specific deposit logic (e.g., checking deposit limits).
     *
     * @param amount The amount to deposit.
     */
    public abstract void deposit(double amount);
}