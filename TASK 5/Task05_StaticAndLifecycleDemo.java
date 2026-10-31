public class Task05_StaticAndLifecycleDemo {
    static class CampusParkingGate {
        // Class-level shared static variable
        private static int globalTicketCounter;
        private static double campusHourlyTariff;

        // Static initialization block
        static {
            globalTicketCounter = 5000;
            campusHourlyTariff = 30.0;
            System.out.println("   [*] Static Block Executed: Tariff preloaded to Rs " + campusHourlyTariff);
        }

        // Static utility method
        public static boolean isValidRegPlate(String plate) {
            return plate != null && plate.matches("^[A-Z]{2}[0-9]{2}[A-Z]{1,2}[0-9]{4}$");
        }

        public static int generateNextTicketNumber() {
            return ++globalTicketCounter;
        }

        // Method demonstrating pass-by-value
        public static void modifyReference(VehicleRefHolder v) {
            v.plate = "MODIFIED-IN-METHOD"; // Changes heap object state
            v = new VehicleRefHolder("REASSIGNED-LOCAL"); // Reassigning local stack pointer has no effect on caller
        }
    }

    static class VehicleRefHolder {
        String plate;
        VehicleRefHolder(String plate) { this.plate = plate; }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 5: STATIC MEMBERS & OBJECT LIFE CYCLE DEMO         ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        System.out.println(">>> 1. STATIC INITIALIZATION & COUNTER SEQUENCING:");
        System.out.println("   [+] North Gate Issued Ticket ID: #" + CampusParkingGate.generateNextTicketNumber());
        System.out.println("   [+] South Gate Issued Ticket ID: #" + CampusParkingGate.generateNextTicketNumber());
        System.out.println("   [+] East Gate  Issued Ticket ID: #" + CampusParkingGate.generateNextTicketNumber());
        System.out.println("   (Single static counter in Metaspace guarantees NO ticket ID collisions!)");

        System.out.println("\n>>> 2. STATIC VALIDATION UTILITY:");
        String plate1 = "TN01AB1111";
        String plate2 = "INVALID_PLATE";
        System.out.printf("   Plate '%s' Valid? -> %b%n", plate1, CampusParkingGate.isValidRegPlate(plate1));
        System.out.printf("   Plate '%s' Valid? -> %b%n", plate2, CampusParkingGate.isValidRegPlate(plate2));

        System.out.println("\n>>> 3. STRICT PASS-BY-VALUE PROOF IN JAVA:");
        VehicleRefHolder original = new VehicleRefHolder("TN09AA9999");
        System.out.println("   Before method call: " + original.plate);
        CampusParkingGate.modifyReference(original);
        System.out.println("   After method call : " + original.plate);
        System.out.println("   (Field mutated on heap, but reassignment did not overwrite caller's reference)");

        System.out.println("\n>>> 4. OBJECT LIFECYCLE & GC ELIGIBILITY:");
        System.out.println("   • Object created on Heap -> Referenced by active ticket registry");
        System.out.println("   • Vehicle exits -> Registry reference set to null -> Zero incoming GC root references");
        System.out.println("   • Eligible for Garbage Collection (Mark-Sweep / G1 GC reclaim)");
        System.out.println("================================================================================");
    }
}
