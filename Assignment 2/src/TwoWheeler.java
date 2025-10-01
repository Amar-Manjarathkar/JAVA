public class TwoWheeler extends Vehicle{
    public TwoWheeler(int regNo, String make, String model, double price) {
        super(regNo, make, model, price);
    }

    @Override
    public double calculateInsurance() {
        return this.getPrice() * 1.05;
    }


}
