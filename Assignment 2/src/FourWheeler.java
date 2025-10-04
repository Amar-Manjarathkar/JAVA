public class FourWheeler extends Vehicle implements Rentable{

    public FourWheeler(int regNo, String make, String model, double price) {
        super(regNo, make, model, price);
    }

    @Override
    public double calculateInsurance() {
        return this.getPrice() * 1.10;
    }


    @Override
    public double rent(int hrs) {
        if(hrs < 3){
            return hrs * 500;
        }
        else{
            return hrs * 500 + (hrs - 3) * 200;
        }
    }
}
