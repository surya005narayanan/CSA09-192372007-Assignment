public class Task01_ProblemAnalysisDemo {
    // Model 1: Object-Oriented Vehicle Entity
    static class Vehicle {
        private final String regNo;
        private final String type;
        private final int entryTime; // in minutes from 00:00

        public Vehicle(String regNo, String type, int entryTime) {
            this.regNo = regNo;
            this.type = type;
            this.entryTime = entryTime;
        }

        public String getRegNo() { return regNo; }
        public String getType() { return type; }
        public int getEntryTime() { return entryTime; }

        @Override
        public String toString() {
            return String.format("Vehicle[regNo=%s, type=%s, entryTime=%02d:%02d]", 
                regNo, type, entryTime / 60, entryTime % 60);
        }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 1: PROBLEM ANALYSIS & OBJECT MODEL VALIDATION     ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        // 1. Object-Oriented Collection
        Vehicle[] oopVehicles = {
            new Vehicle("TN01AB1111", "TwoWheeler", 480), // 08:00
            new Vehicle("TN02CD2222", "Car", 510),        // 08:30
            new Vehicle("TN03EF3333", "ElectricCar", 540) // 09:00
        };

        // 2. Alternative Concept: Parallel Primitive Arrays
        String[] regNos = { "TN01AB1111", "TN02CD2222", "TN03EF3333" };
        String[] types = { "TwoWheeler", "Car", "ElectricCar" };
        int[] entryTimes = { 480, 510, 540 };

        System.out.println(">>> 1. OBJECT-ORIENTED MODEL REPRESENTATION:");
        for (Vehicle v : oopVehicles) {
            System.out.println("   [+] " + v);
        }

        System.out.println("\n>>> 2. PARALLEL ARRAYS REPRESENTATION:");
        for (int i = 0; i < regNos.length; i++) {
            System.out.printf("   [Index %d] regNo: %-10s | type: %-12s | entryTime: %02d:%02d%n",
                i, regNos[i], types[i], entryTimes[i] / 60, entryTimes[i] % 60);
        }

        System.out.println("\n>>> 3. SEARCH COMPARISON (Finding 'TN02CD2222'):");
        String target = "TN02CD2222";
        // Search in OOP model
        Vehicle foundOop = null;
        for (Vehicle v : oopVehicles) {
            if (v.getRegNo().equals(target)) { foundOop = v; break; }
        }
        System.out.println("   OOP Linear Search Result      : Found -> " + foundOop);

        // Search in Parallel Arrays
        int foundIdx = -1;
        for (int i = 0; i < regNos.length; i++) {
            if (regNos[i].equals(target)) { foundIdx = i; break; }
        }
        System.out.printf("   Parallel Array Search Result  : Found at index %d -> (%s, %s, %d mins)%n",
            foundIdx, regNos[foundIdx], types[foundIdx], entryTimes[foundIdx]);

        System.out.println("\n>>> 4. BIG-O COMPLEXITY & EXTENSIBILITY EVALUATION:");
        System.out.println("   • Search by Reg No   : O(n) for both (OOP can be O(1) via Map without signature change)");
        System.out.println("   • Adding Attribute   : OOP modifies 1 class; Parallel arrays require new array + all methods modified");
        System.out.println("   • Integrity Risk     : Parallel arrays suffer from index de-synchronization vulnerability");
        System.out.println("================================================================================");
    }
}
