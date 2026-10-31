import java.time.LocalTime;

public class Task04_ConstructorsMemoryDemo {
    static class ParkingTicket {
        private static int counter = 1000;
        private final int ticketId;
        private final String regNo;
        private final int floor;
        private final int slot;
        private final LocalTime entryTime;
        private final boolean hasPass;

        // 1. Primary Parameterized Constructor
        public ParkingTicket(String regNo, int floor, int slot, LocalTime entryTime, boolean hasPass) {
            if (regNo == null || regNo.trim().isEmpty()) {
                throw new IllegalArgumentException("Registration plate cannot be null or empty.");
            }
            this.ticketId = ++counter;
            this.regNo = regNo.trim().toUpperCase();
            this.floor = floor;
            this.slot = slot;
            this.entryTime = entryTime;
            this.hasPass = hasPass;
        }

        // 2. Overloaded Constructor with Chaining ('this(...)')
        public ParkingTicket(String regNo, int floor, int slot) {
            this(regNo, floor, slot, LocalTime.of(9, 0), false); // Constructor Chaining
        }

        // 3. Copy Constructor for Duplicate Ticket / Re-print Audit
        public ParkingTicket(ParkingTicket original) {
            this.ticketId = original.ticketId; // Re-uses original unique sequence
            this.regNo = original.regNo;
            this.floor = original.floor;
            this.slot = original.slot;
            this.entryTime = original.entryTime;
            this.hasPass = original.hasPass;
        }

        // 4. Static Factory Method
        public static ParkingTicket issueAtGate(String regNo, int floor, int slot) {
            return new ParkingTicket(regNo, floor, slot);
        }

        @Override
        public String toString() {
            return String.format("Ticket #%d [Vehicle: %-10s | Pos: F%d:S%d | Entry: %s | Pass: %b]",
                ticketId, regNo, floor, slot, entryTime, hasPass);
        }
    }

    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 4: CONSTRUCTORS & MEMORY ALLOCATION DEMO           ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        System.out.println(">>> 1. CONSTRUCTOR CHAINING (this(...)):");
        ParkingTicket t1 = new ParkingTicket("TN01AB1111", 0, 1);
        System.out.println("   [+] Overloaded Chained Ticket : " + t1);

        ParkingTicket t2 = new ParkingTicket("TN02CD2222", 1, 0, LocalTime.of(10, 15), true);
        System.out.println("   [+] Parameterized Full Ticket : " + t2);

        System.out.println("\n>>> 2. COPY CONSTRUCTOR (Duplicate Re-print):");
        ParkingTicket tDuplicate = new ParkingTicket(t1);
        System.out.println("   [+] Duplicate Re-issued Ticket: " + tDuplicate);
        System.out.println("   [+] Separate Heap References  : " + (t1 != tDuplicate) + " (Independent objects)");
        System.out.println("   [+] Field Equality Verification: " + (t1.ticketId == tDuplicate.ticketId));

        System.out.println("\n>>> 3. STATIC FACTORY METHOD INSTANTIATION:");
        ParkingTicket tFactory = ParkingTicket.issueAtGate("TN03EF3333", 0, 2);
        System.out.println("   [+] Factory Generated Ticket  : " + tFactory);

        System.out.println("\n>>> 4. STACK VS HEAP MEMORY ARCHITECTURE:");
        System.out.println("   • Thread Stack: Local variables 't1', 't2', 'tDuplicate', 'tFactory' store 64-bit heap addresses");
        System.out.println("   • JVM Heap    : Encapsulates ticketId, regNo String, immutable timestamp values");
        System.out.println("================================================================================");
    }
}
