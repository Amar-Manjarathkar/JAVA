public class Bank {
    public static void main(String[] args) {
        Account sav= new Saving(7726,"Amar",50000);
        System.out.println(sav.toString());
        sav.withdraw(40000);
        sav.deposit(12000);
        sav.withdraw(4000);

        Account sav1 = new Current(7726,"Amar",50000);
        System.out.println(sav1.toString());
        sav1.withdraw(40000);
        sav1.deposit(1200000);
        sav1.withdraw(4000);
    }
}
