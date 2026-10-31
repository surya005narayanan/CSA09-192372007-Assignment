public class Task10_EncapsulationDemo {
    static class SecureParkingSlot {
        private final int slotId;
        private String vehicleNo;
        private int durationMinutes;

        public SecureParkingSlot(int slotId) {
            if (slotId < 0) throw new IllegalArgumentException("Slot ID cannot be negative.");
            this.slotId = slotId;
            this.vehicleNo = null;
            this.durationMinutes = 0;
        }

        public void park(String vehicleNo, int duration) {
            if (vehicleNo == null || vehicleNo.trim().isEmpty()) {
                throw new IllegalArgumentException("Cannot park null/empty vehicle plate.");
            }
            if (duration < 0) {
                throw new IllegalArgumentException("Duration cannot be negative: " + duration);
            }
            this.vehicleNo = vehicleNo.trim().toUpperCase();
            this.durationMinutes = duration;
        }

        public String getVehicleNo() { return vehicleNo; }
        public int getDurationMinutes() { return durationMinutes; }
        public int getSlotId() { return slotId; }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 10: ENCAPSULATION & AUTOBOXING PITFALLS DEMO       ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        System.out.println(">>> 1. DEFENSIVE ENCAPSULATION & DATA VALIDATION:");
        SecureParkingSlot slot = new SecureParkingSlot(101);
        slot.park("TN01AB1111", 120);
        System.out.printf("   [+] Valid Parking: Slot %d holds %s for %d mins%n",
            slot.getSlotId(), slot.getVehicleNo(), slot.getDurationMinutes());

        try {
            System.out.print("   [*] Testing Negative Duration Rejection: ");
            slot.park("TN01AB1111", -45);
        } catch (IllegalArgumentException e) {
            System.out.println("CAUGHT EXPECTED ERROR -> " + e.getMessage());
        }

        System.out.println("\n>>> 2. INTEGER AUTOBOXING '==' REFERENCE IDENTITY TRAP:");
        Integer low1 = 120;
        Integer low2 = 120;
        System.out.printf("   low1 = 120, low2 = 120 -> low1 == low2 ? : %b (CACHED -128..127)%n", (low1 == low2));

        Integer high1 = 1001; // Typical ticket counter ID
        Integer high2 = 1001;
        System.out.printf("   high1 = 1001, high2 = 1001 -> high1 == high2 ? : %b (DANGEROUS TRAP!)%n", (high1 == high2));
        System.out.printf("   high1.equals(high2) ? : %b (CORRECT OBJECT EQUALITY)%n", high1.equals(high2));

        System.out.println("\n>>> 3. ENGINEERING DEFENSE:");
        System.out.println("   • Internal fields are strictly private with validated mutator guards.");
        System.out.println("   • Use primitive 'int' for IDs and counters to eliminate autoboxing identity bugs.");
        System.out.println("================================================================================");
    }
}
