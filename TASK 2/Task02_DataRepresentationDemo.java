public class Task02_DataRepresentationDemo {
    public static void main(String[] args) {
        System.out.println("================================================================================");
        System.out.println("   SIMATS ENGINEERING - TASK 2: DATA REPRESENTATION & NUMERICAL INTEGRITY      ");
        System.out.println("   Student: Surya Narayanan Muthu Kumar  |  Register No: 192372007  |  Java: 25.0.2             ");
        System.out.println("================================================================================\n");

        System.out.println(">>> 1. JAGGED 2D ARRAY FACILITY REPRESENTATION:");
        int[] slotsPerFloor = { 20, 50, 100 }; // Asymmetric floor capacities
        String[][] jaggedSlots = new String[slotsPerFloor.length][];
        int totalSlots = 0;
        for (int f = 0; f < slotsPerFloor.length; f++) {
            jaggedSlots[f] = new String[slotsPerFloor[f]];
            totalSlots += slotsPerFloor[f];
            System.out.printf("   Floor %d allocated: %3d physical slots (Index: 0 to %d)%n", 
                f, jaggedSlots[f].length, jaggedSlots[f].length - 1);
        }
        System.out.printf("   Total Allocated Slots: %d | Wasted Memory Cells: 0%n", totalSlots);
        System.out.println("   (Rectangular 3x100 matrix would waste (100-20)+(100-50) = 130 unused references!)");

        System.out.println("\n>>> 2. INTEGER DIVISION TRUNCATION BUG vs CEILING RECOVERY FORMULA:");
        int[] testDurations = { 1, 45, 60, 61, 65, 120, 125, 130 };
        double carRatePerHour = 30.0;

        System.out.printf("   %-10s | %-12s | %-12s | %-12s | %-12s | %-10s%n",
            "Duration", "Flawed Hours", "Flawed Fee", "Ceil Hours", "Correct Fee", "Discrepancy");
        System.out.println("   " + "-".repeat(74));

        for (int mins : testDurations) {
            // Flawed integer division:
            int flawedHours = mins / 60;
            double flawedFee = flawedHours * carRatePerHour;

            // Correct integer ceiling formula:
            int ceilHours = (mins == 0) ? 0 : (mins + 59) / 60;
            double correctFee = ceilHours * carRatePerHour;

            double diff = correctFee - flawedFee;
            String status = (diff > 0) ? String.format("UNDERCHARGE (Rs %.0f loss)", diff) : "MATCH";

            System.out.printf("   %3d mins   | %2d hrs       | Rs %6.1f    | %2d hrs       | Rs %6.1f    | %s%n",
                mins, flawedHours, flawedFee, ceilHours, correctFee, status);
        }

        System.out.println("\n>>> 3. KEY TAKEAWAYS:");
        System.out.println("   • Formula: '(minutes + 59) / 60' strictly satisfies 'every started hour billed in full'");
        System.out.println("   • Avoids floating-point precision drift of Math.ceil((double)mins / 60.0)");
        System.out.println("================================================================================");
    }
}
