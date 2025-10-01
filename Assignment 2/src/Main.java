public class Main {
    public static void main(String[] args) {
        Vehicle v1 = new FourWheeler(123,"Tata","Sumo",1200000);
        System.out.println(v1.toString());
        System.out.println("Insurance is: "+v1.calculateInsurance());
        Vehicle v2 = new TwoWheeler(123,"KTM","Duke",120000);
        System.out.println(v2.toString());
        System.out.println("Insurance is: "+v2.calculateInsurance());
    }
}
