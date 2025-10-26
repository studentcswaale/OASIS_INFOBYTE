import java.util.*;

public class OnlineReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = Map.of("admin", "1234");
    static List<Map<String, String>> reservations = new ArrayList<>();
    static int pnrCounter = 1000;

    public static void main(String[] args) {
        if (!login()) return;

        while (true) {
            System.out.println("\n1. Reserve Ticket  2. Cancel Ticket  3. Exit");
            System.out.print("Choice: ");
            int ch = sc.nextInt(); sc.nextLine();
            switch (ch) {
                case 1 -> reserve();
                case 2 -> cancel();
                case 3 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static boolean login() {
        System.out.print("Username: "); String u = sc.nextLine();
        System.out.print("Password: "); String p = sc.nextLine();
        if (users.getOrDefault(u, "").equals(p)) { System.out.println("Login Successful!"); return true; }
        System.out.println("Login Failed!"); return false;
    }

    static void reserve() {
        Map<String, String> res = new HashMap<>();
        res.put("PNR", String.valueOf(pnrCounter++));
        System.out.print("Name: "); res.put("Name", sc.nextLine());
        System.out.print("Train No: "); res.put("TrainNo", sc.nextLine());
        System.out.print("Train Name: "); res.put("TrainName", sc.nextLine());
        System.out.print("From: "); res.put("From", sc.nextLine());
        System.out.print("To: "); res.put("To", sc.nextLine());
        System.out.print("Date: "); res.put("Date", sc.nextLine());
        System.out.print("Class: "); res.put("Class", sc.nextLine());
        reservations.add(res);
        System.out.println("Reservation Successful! PNR: " + res.get("PNR"));
    }

    static void cancel() {
        System.out.print("Enter PNR: "); String pnr = sc.nextLine();
        Map<String, String> res = reservations.stream()
                .filter(r -> r.get("PNR").equals(pnr)).findFirst().orElse(null);
        if (res != null) {
            res.forEach((k,v)-> System.out.println(k + ": " + v));
            System.out.print("Confirm cancellation? (yes/no): ");
            if (sc.nextLine().equalsIgnoreCase("yes")) { reservations.remove(res); System.out.println("Cancelled!"); }
            else System.out.println("Aborted.");
        } else System.out.println("PNR not found!");
    }
}
