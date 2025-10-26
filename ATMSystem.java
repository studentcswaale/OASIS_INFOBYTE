import java.util.*;

public class ATMSystem {
    static Scanner sc = new Scanner(System.in);

    static class Account {
        String userId, pin;
        double balance = 1000;
        List<String> history = new ArrayList<>();

        Account(String userId, String pin) {
            this.userId = userId;
            this.pin = pin;
        }

        void deposit(double amount) {
            balance += amount;
            history.add("Deposited: " + amount);
            System.out.println("Deposited successfully. Balance: " + balance);
        }

        void withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient balance!");
            } else {
                balance -= amount;
                history.add("Withdrawn: " + amount);
                System.out.println("Withdrawn successfully. Balance: " + balance);
            }
        }

        void transfer(Account target, double amount) {
            if (amount > balance) {
                System.out.println("Insufficient balance!");
            } else {
                balance -= amount;
                target.balance += amount;
                history.add("Transferred " + amount + " to " + target.userId);
                target.history.add("Received " + amount + " from " + userId);
                System.out.println("Transfer successful. Balance: " + balance);
            }
        }

        void printHistory() {
            System.out.println("--- Transaction History ---");
            if (history.isEmpty()) System.out.println("No transactions yet.");
            else history.forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        Map<String, Account> accounts = new HashMap<>();
        accounts.put("user1", new Account("user1", "1234"));
        accounts.put("user2", new Account("user2", "4321"));

        System.out.print("Enter User ID: ");
        String id = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        Account current = accounts.get(id);
        if (current == null || !current.pin.equals(pin)) {
            System.out.println("Invalid credentials! Exiting...");
            return;
        }

        int choice;
        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> current.printHistory();
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    current.withdraw(sc.nextDouble());
                }
                case 3 -> {
                    System.out.print("Enter amount to deposit: ");
                    current.deposit(sc.nextDouble());
                }
                case 4 -> {
                    System.out.print("Enter target user ID: ");
                    sc.nextLine(); // consume newline
                    String targetId = sc.nextLine();
                    Account target = accounts.get(targetId);
                    if (target == null) System.out.println("Target account not found!");
                    else {
                        System.out.print("Enter amount to transfer: ");
                        current.transfer(target, sc.nextDouble());
                    }
                }
                case 5 -> System.out.println("Thank you! Exiting...");
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 5);
    }
}
