public class Task09_AbstractionDemo {
    // Abstract Class: Represents Core Identity & Encapsulates State
    static abstract class Vehicle {
        private final String regNo;

        public Vehicle(String regNo) {
            this.regNo = regNo;
        }

        public String getRegNo() { return regNo; }
        public abstract double ratePerHour();
    }

    // Interface 1: Chargeable Capability
    interface Chargeable {
        double chargingFee(int kWh);

        // Static helper method in interface (Java 8+)
        static boolean isValidChargingPower(int kW) {
            return kW >= 7 && kW <= 50;
        }
    }

    // Interface 2: Pass/Discount Capability
    interface Discountable {
        double applyDiscount(double rawFee);

        // Default method in interface
        default double calculateGST(double amount) {
            return amount * 0.18; // 18% GST
        }
    }

    // Multi-Interface Implementation
    static class CampusElectricShuttle extends Vehicle implements Chargeable, Discountable {
        public CampusElectricShuttle(String regNo) { super(regNo); }

        @Override public double ratePerHour() { return 25.0; }
        @Override public double chargingFee(int kWh) { return kWh * 10.0; }
        @Override public double applyDiscount(double rawFee) { return rawFee * 0.50; } // 50% campus subsidy
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 9: ABSTRACTION & MULTIPLE INTERFACES DEMO          ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        CampusElectricShuttle shuttle = new CampusElectricShuttle("TN01CAMPUS01");

        System.out.println(">>> 1. MULTIPLE INTERFACE CAPABILITIES:");
        System.out.println("   [+] Vehicle Identity       : " + shuttle.getRegNo());
        System.out.println("   [+] Base Hourly Tariff     : Rs " + shuttle.ratePerHour() + "/hr");
        System.out.println("   [+] EV Charging (20 kWh)   : Rs " + shuttle.chargingFee(20));

        double rawFee = 100.0;
        double netFee = shuttle.applyDiscount(rawFee);
        double gst = shuttle.calculateGST(netFee);
        System.out.println("   [+] Raw Fee: Rs " + rawFee + " -> Discounted Net: Rs " + netFee + " -> GST (18%): Rs " + gst);

        System.out.println("\n>>> 2. STATIC INTERFACE METHOD VALIDATION:");
        System.out.println("   • 22 kW Charger Valid? -> " + Chargeable.isValidChargingPower(22));
        System.out.println("   • 3 kW Charger Valid?  -> " + Chargeable.isValidChargingPower(3));

        System.out.println("\n>>> 3. ARCHITECTURAL JUSTIFICATION:");
        System.out.println("   • Abstract Class: Mandates core 'is-a' entity state (regNo) and constructor.");
        System.out.println("   • Interfaces    : Provide pluggable 'can-do' contracts (Chargeable, Discountable).");
        System.out.println("================================================================================");
    }
}
