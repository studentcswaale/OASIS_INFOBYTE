import java.util.*;

public class LibrarySystem {
    static Scanner sc = new Scanner(System.in);

    static class Book {
        String id, title, author;
        boolean issued = false;

        Book(String id, String title, String author) {
            this.id = id; this.title = title; this.author = author;
        }

        void display() {
            System.out.println(id + ": " + title + " by " + author + (issued ? " [Issued]" : ""));
        }
    }

    static class User {
        String username, password;
        boolean isAdmin;

        User(String u, String p, boolean a) { username=u; password=p; isAdmin=a; }
    }

    static List<Book> books = new ArrayList<>();
    static List<User> users = new ArrayList<>();

    public static void main(String[] args) {
        // Sample data
        books.add(new Book("B001","Java Basics","Author A"));
        books.add(new Book("B002","Data Structures","Author B"));
        users.add(new User("admin","123",true));
        users.add(new User("user1","123",false));

        System.out.print("Username: "); String uname = sc.nextLine();
        System.out.print("Password: "); String pwd = sc.nextLine();

        User current = users.stream().filter(u->u.username.equals(uname) && u.password.equals(pwd))
                            .findFirst().orElse(null);
        if(current==null){ System.out.println("Invalid login!"); return; }

        int choice;
        do{
            if(current.isAdmin){
                System.out.println("\nAdmin Menu: 1.Add Book 2.Remove Book 3.View Books 4.Quit");
                choice = sc.nextInt(); sc.nextLine();
                switch(choice){
                    case 1 -> addBook();
                    case 2 -> removeBook();
                    case 3 -> viewBooks();
                    case 4 -> System.out.println("Bye!");
                    default -> System.out.println("Invalid choice!");
                }
            } else {
                System.out.println("\nUser Menu: 1.View Books 2.Issue Book 3.Return Book 4.Quit");
                choice = sc.nextInt(); sc.nextLine();
                switch(choice){
                    case 1 -> viewBooks();
                    case 2 -> issueBook();
                    case 3 -> returnBook();
                    case 4 -> System.out.println("Bye!");
                    default -> System.out.println("Invalid choice!");
                }
            }
        } while(choice !=4);
    }

    static void viewBooks(){ books.forEach(Book::display); }

    static void addBook(){
        System.out.print("Book ID: "); String id = sc.nextLine();
        System.out.print("Title: "); String title = sc.nextLine();
        System.out.print("Author: "); String author = sc.nextLine();
        books.add(new Book(id,title,author));
        System.out.println("Book added!");
    }

    static void removeBook(){
        System.out.print("Book ID to remove: "); String id = sc.nextLine();
        books.removeIf(b->b.id.equals(id));
        System.out.println("Book removed if existed!");
    }

    static void issueBook(){
        System.out.print("Book ID to issue: "); String id = sc.nextLine();
        Book b = books.stream().filter(book->book.id.equals(id)).findFirst().orElse(null);
        if(b==null) System.out.println("Book not found!");
        else if(b.issued) System.out.println("Already issued!");
        else { b.issued=true; System.out.println("Book issued!"); }
    }

    static void returnBook(){
        System.out.print("Book ID to return: "); String id = sc.nextLine();
        Book b = books.stream().filter(book->book.id.equals(id)).findFirst().orElse(null);
        if(b==null) System.out.println("Book not found!");
        else if(!b.issued) System.out.println("Book wasn't issued!");
        else { b.issued=false; System.out.println("Book returned!"); }
    }
}
