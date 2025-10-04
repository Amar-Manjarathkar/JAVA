public class Admin {
    Policy policy = new Policy();

    public void displayInsuranceForVehicle(Vehicle v){
        policy.displayInsurance(v);
    }

    public void generateInsuranceReport(Vehicle[] vehicles){
        System.out.println("--- Insurance Report ---");
        for (Vehicle v : vehicles){
            policy.displayInsurance(v);
        }
        System.out.println("---End of Report---");

    }

}
