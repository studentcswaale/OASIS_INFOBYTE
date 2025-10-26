import java.util.*;

public class OnlineExamSystem {
    static Scanner sc = new Scanner(System.in);

    static class User {
        String username;
        String password;
        String email;

        User(String u, String p, String e) { username=u; password=p; email=e; }
    }

    static Map<String, User> users = new HashMap<>();
    static Map<String, String[]> questions = new LinkedHashMap<>();
    static Map<String, String[]> options = new LinkedHashMap<>();

    public static void main(String[] args) {
        // Sample user
        users.put("student", new User("student", "1234", "student@email.com"));

        // Sample questions
        questions.put("Q1: Java is ___?", new String[]{"OOP Language","OS","Database","Hardware"});
        questions.put("Q2: JVM stands for?", new String[]{"Java Virtual Machine","Java Visual Model","Just Virtual Method","None"});
        
        System.out.print("Enter username: "); String uname = sc.nextLine();
        System.out.print("Enter password: "); String pwd = sc.nextLine();

        User current = users.get(uname);
        if(current==null || !current.password.equals(pwd)) { System.out.println("Invalid login!"); return; }

        int choice;
        do {
            System.out.println("\n1. Update Profile/Password  2. Start Exam  3. Logout");
            System.out.print("Choice: "); choice = sc.nextInt(); sc.nextLine();
            switch(choice) {
                case 1 -> updateProfile(current);
                case 2 -> startExam();
                case 3 -> System.out.println("Logged out. Bye!");
                default -> System.out.println("Invalid choice!");
            }
        } while(choice != 3);
    }

    static void updateProfile(User user) {
        System.out.print("Enter new email: "); user.email = sc.nextLine();
        System.out.print("Enter new password: "); user.password = sc.nextLine();
        System.out.println("Profile updated!");
    }

    static void startExam() {
        Map<String, String> answers = new HashMap<>();
        System.out.println("\nExam Started! You have 30 seconds to complete.");
        
        Thread timer = new Thread(() -> {
            try { Thread.sleep(30000); } catch(Exception e) {}
            System.out.println("\nTime's up! Auto submitting...");
            printResults(answers);
            System.exit(0);
        });
        timer.start();

        for(String q : questions.keySet()) {
            System.out.println("\n" + q);
            String[] opts = options.getOrDefault(q, questions.get(q));
            for(int i=0;i<opts.length;i++) System.out.println((i+1)+". "+opts[i]);
            System.out.print("Enter option number: ");
            int ans = sc.nextInt(); sc.nextLine();
            if(ans>=1 && ans<=opts.length) answers.put(q, opts[ans-1]);
        }

        printResults(answers);
    }

    static void printResults(Map<String, String> answers) {
        System.out.println("\n--- Exam Submitted ---");
        answers.forEach((q,a)-> System.out.println(q + " -> " + a));
    }
}
