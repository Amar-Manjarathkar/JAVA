public abstract class Vehicle {
    int regNo;
    String make;
    String model;
    double price;

    public Vehicle(int regNo, String make, String model, double price) {
        this.regNo = regNo;
        this.make = make;
        this.model = model;
        this.price = price;
    }

    public int getRegNo() {
        return regNo;
    }

    public void setRegNo(int regNo) {
        this.regNo = regNo;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "regNo=" + regNo +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                '}';
    }
    public abstract double calculateInsurance();



}
