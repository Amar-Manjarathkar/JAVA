public class Policy {


    public void displayInsurance(Vehicle v) {
        double insurance = 0.0;
        if (v instanceof TwoWheeler) {
            insurance = v.getPrice() * 0.05;
        }
        if (v instanceof FourWheeler) {
            insurance = v.getPrice() * 0.08;
        }
        System.out.println("The insurance for the" + v.getModel() + " is " + insurance);

    }
}

