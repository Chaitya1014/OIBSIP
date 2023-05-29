import java.util.*;
class Book 
{
    private int bookId;
    private String title;
    private String author;
    private boolean available;
    public Book(int bookId, String title, String author)
    {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.available = true;
    }
    public int getBookId()
    {
        return bookId;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public boolean isAvailable() 
    {
        return available;
    }
    public void setAvailable(boolean available)
    {
        this.available = available;
    }
}
class Member
{
    private int memberId;
    private String name;
    private String email;
    private List<Book> borrowedBooks;
    public Member(int memberId, String name, String email)
    {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.borrowedBooks = new ArrayList<>();
    }
    public int getMemberId() 
    {
        return memberId;
    }
    public String getName()
    {
        return name;
    }
    public String getEmail() 
    {
        return email;
    }
    public List<Book> getBorrowedBooks() 
    {
        return borrowedBooks;
    }
}
class Library 
{
    private List<Book> books;
    private List<Member> members;
    public Library()
    {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
    }
    public void addBook(Book book)
    {
        books.add(book);
    }
    public void addMember(Member member)
    {
        members.add(member);
    }
    public Book findBookById(int bookId)
    {
        for (Book book : books) 
        {
            if (book.getBookId() == bookId)
            {
                return book;
            }
        }
        return null;
    }
    public Member findMemberById(int memberId)
    {
        for (Member member : members) 
        {
            if (member.getMemberId() == memberId) 
            {
                return member;
            }
        }
        return null;
    }
    public List<Book> getAvailableBooks()
    {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books) 
        {
            if (book.isAvailable()) 
            {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }
    public void issueBook(int memberId, int bookId)
    {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);
        if (member == null || book == null)
        {
            System.out.println("Invalid member ID or book ID.");
            return;
        }
        if (!book.isAvailable()) 
        {
            System.out.println("Book is not available for borrowing.");
            return;
        }
        member.getBorrowedBooks().add(book);
        book.setAvailable(false);
        System.out.println("Book issued successfully.");
    }
    public void returnBook(int memberId, int bookId)
    {
        Member member = findMemberById(memberId);
        Book book = findBookById(bookId);
        if (member == null || book == null) 
        {
            System.out.println("Invalid member ID or book ID.");
            return;
        }
        if (!member.getBorrowedBooks().contains(book)) 
        {
            System.out.println("This member did not borrow the book.");
            return;
        }
        member.getBorrowedBooks().remove(book);
        book.setAvailable(true);
        System.out.println("Book returned successfully.");
    }
}
class LibraryManagementSystem 
{
    private Library library;
    public LibraryManagementSystem()
    {
        this.library = new Library();
    }
    public void addBook() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter book ID:");
        int bookId = scanner.nextInt();
        System.out.println("Enter book title:");
        String title = scanner.next();
        System.out.println("Enter book author:");
        String author = scanner.next();
        Book book = new Book(bookId, title, author);
        library.addBook(book);
        System.out.println("Book added successfully.");
    }
    public void addMember()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int memberId = scanner.nextInt();
        System.out.println("Enter member name:");
        String name = scanner.next();
        System.out.println("Enter member email:");
        String email = scanner.next();
        Member member = new Member(memberId, name, email);
        library.addMember(member);
        System.out.println("Member added successfully.");
    }
    public void displayAvailableBooks() 
    {
        List<Book> availableBooks = library.getAvailableBooks();
        if (availableBooks.isEmpty()) 
        {
            System.out.println("No books available.");
        } 
        else 
        {
            System.out.println("Available Books:");
            for (Book book : availableBooks) 
            {
                System.out.println("Book ID: " + book.getBookId());
                System.out.println("Title: " + book.getTitle());
                System.out.println("Author: " + book.getAuthor());
                System.out.println("--------------------");
            }
        }
    }
    public void issueBook() 
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int memberId = scanner.nextInt();
        System.out.println("Enter book ID:");
        int bookId = scanner.nextInt();
        library.issueBook(memberId, bookId);
    }
    public void returnBook()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter member ID:");
        int memberId = scanner.nextInt();
        System.out.println("Enter book ID:");
        int bookId = scanner.nextInt();
        library.returnBook(memberId, bookId);
    }
}
public class LibraryManagementSystemApp 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        LibraryManagementSystem libraryManagementSystem = new LibraryManagementSystem();
        while (true)
        {
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Display Available Books");
            System.out.println("4. Issue Book");
            System.out.println("5. Return Book");
            System.out.println("6. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) 
            {
                case 1:
                    libraryManagementSystem.addBook();
                    break;
                case 2:
                    libraryManagementSystem.addMember();
                    break;
                case 3:
                    libraryManagementSystem.displayAvailableBooks();
                    break;
                case 4:
                    libraryManagementSystem.issueBook();
                    break;
                case 5:
                    libraryManagementSystem.returnBook();
                    break;
                case 6:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
