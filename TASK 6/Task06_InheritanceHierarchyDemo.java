public class Task06_InheritanceHierarchyDemo {
    interface Chargeable {
        double chargingFee(int kWh);
    }

    static abstract class Vehicle {
        private final String regNo;

        public Vehicle(String regNo) {
            System.out.println("      [Constructor] 2. Vehicle constructor executed (regNo = " + regNo + ")");
            this.regNo = regNo;
        }

        public String getRegNo() { return regNo; }
        public abstract double ratePerHour();
    }

    static class Car extends Vehicle {
        public Car(String regNo) {
            super(regNo);
            System.out.println("      [Constructor] 3. Car constructor executed");
        }

        @Override
        public double ratePerHour() { return 30.0; }
    }

    static class ElectricCar extends Car implements Chargeable {
        public ElectricCar(String regNo) {
            super(regNo);
            System.out.println("      [Constructor] 4. ElectricCar constructor executed");
        }

        @Override
        public double chargingFee(int kWh) { return kWh * 12.0; }
    }

    static class TwoWheeler extends Vehicle {
        public TwoWheeler(String regNo) {
            super(regNo);
            System.out.println("      [Constructor] 3. TwoWheeler constructor executed");
        }

        @Override
        public double ratePerHour() { return 10.0; }
    }

    public static void main(String[] args) {
        
        System.out.println("   SIMATS ENGINEERING - TASK 6: INHERITANCE HIERARCHY & CONSTRUCTOR TRACE      ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        
        
        System.out.println(">>> 1. CONSTRUCTOR EXECUTION ORDER TRACE FOR: new ElectricCar(\"TN03C3\"):");
        System.out.println("   (Notice: java.lang.Object (1) -> Vehicle (2) -> Car (3) -> ElectricCar (4))");
        ElectricCar ev = new ElectricCar("TN03C3");

        System.out.println("\n>>> 2. INHERITANCE ARCHITECTURE VERIFICATION:");
        System.out.println("   • ev is-a ElectricCar : " + (ev instanceof ElectricCar));
        System.out.println("   • ev is-a Car         : " + (ev instanceof Car));
        System.out.println("   • ev is-a Vehicle     : " + (ev instanceof Vehicle));
        System.out.println("   • ev is-a Chargeable  : " + (ev instanceof Chargeable));

        System.out.println("\n>>> 3. HIERARCHY MEMBERSHIP:");
        System.out.println("   [+] Registration (Inherited from Vehicle) : " + ev.getRegNo());
        System.out.println("   [+] Hourly Rate  (Inherited from Car)     : Rs " + ev.ratePerHour() + "/hr");
        System.out.println("   [+] Charging Fee (Implemented Chargeable) : 10 kWh -> Rs " + ev.chargingFee(10));
        System.out.println("================================================================================");
    }
}
