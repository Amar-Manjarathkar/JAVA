public class FourWheeler extends Vehicle{

    public FourWheeler(int regNo, String make, String model, double price) {
        super(regNo, make, model, price);
    }

    @Override
    public double calculateInsurance() {
        return this.getPrice() * 1.10;
    }


}
