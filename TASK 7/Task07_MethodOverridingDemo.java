import java.util.Objects;

public class Task07_MethodOverridingDemo {
    static abstract class Vehicle {
        private final String regNo;

        public Vehicle(String regNo) { this.regNo = regNo; }
        public String getRegNo() { return regNo; }

        public abstract double ratePerHour();

        // final template method: subclasses cannot alter billing ceiling formula
        public final double calculateFee(int minutes) {
            int hours = (minutes + 59) / 60;
            return hours * ratePerHour();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            return o instanceof Vehicle other && regNo.equals(other.regNo);
        }

        @Override
        public int hashCode() {
            return Objects.hash(regNo);
        }

        @Override
        public String toString() {
            return String.format("[%s | Reg: %s | Base Rate: Rs %.1f/hr]",
                getClass().getSimpleName(), regNo, ratePerHour());
        }
    }

    static class TwoWheeler extends Vehicle {
        public TwoWheeler(String regNo) { super(regNo); }
        @Override public double ratePerHour() { return 10.0; }
    }

    static class Car extends Vehicle {
        public Car(String regNo) { super(regNo); }
        @Override public double ratePerHour() { return 30.0; }
    }

    static class ElectricCar extends Car {
        public ElectricCar(String regNo) { super(regNo); }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 7: METHOD OVERRIDING & OBJECT CONTRACT DEMO        ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        Vehicle v1 = new TwoWheeler("TN01AA1111");
        Vehicle v2 = new Car("TN02BB2222");
        Vehicle v3 = new ElectricCar("TN02BB2222"); // Identical registration plate

        System.out.println(">>> 1. METHOD OVERRIDING & TOSTRING():");
        System.out.println("   [+] " + v1 + " -> 120 mins fee = Rs " + v1.calculateFee(120));
        System.out.println("   [+] " + v2 + " -> 120 mins fee = Rs " + v2.calculateFee(120));

        System.out.println("\n>>> 2. EQUALS() & HASHCODE() CONTRACT VERIFICATION:");
        System.out.println("   • v2: Car [TN02BB2222]");
        System.out.println("   • v3: ElectricCar [TN02BB2222]");
        System.out.println("   • v2.equals(v3) ? -> " + v2.equals(v3));
        System.out.println("   • v3.equals(v2) ? -> " + v3.equals(v2));
        System.out.println("   • HashCode Match? -> " + (v2.hashCode() == v3.hashCode()));
        System.out.println("   (Identical plate treated as same physical vehicle, preventing double gate entry)");

        System.out.println("\n>>> 3. FINAL TEMPLATE METHOD ENFORCEMENT:");
        System.out.println("   • calculateFee(minutes) is marked 'final' in Vehicle superclass");
        System.out.println("   • Prevents unauthorized tampering with billing ceiling formula");
        System.out.println("================================================================================");
    }
}
