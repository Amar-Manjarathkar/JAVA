public class TwoWheeler extends Vehicle implements Rentable{
    public TwoWheeler(int regNo, String make, String model, double price) {
        super(regNo, make, model, price);
    }

    @Override
    public double calculateInsurance() {
        return this.getPrice() * 1.05;
    }

    public double rent(int hrs){

        if (hrs < 3){
            return 300 * hrs;
        }
        else{
            return (hrs * 300) + ((hrs - 3) * 100);
        }
    }


}
