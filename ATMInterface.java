import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class User 
{
    private String userId;
    private String pin;
    private double balance;
    private List<String> transactionHistory;
    public User(String userId, String pin, double balance) 
    {
        this.userId = userId;
        this.pin = pin;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }
    public String getUserId()
    {
        return userId;
    }
    public String getPin() 
    {
        return pin;
    }
    public double getBalance() 
    {
        return balance;
    }
    public void setBalance(double balance) 
    {
        this.balance = balance;
    }
    public List<String> getTransactionHistory()
    {
        return transactionHistory;
    }
    public void addTransaction(String transaction) 
    {
        transactionHistory.add(transaction);
    }
}
public class ATMInterface 
{
    private User currentUser;
    private Scanner scanner;
    public ATMInterface() 
    {
        scanner = new Scanner(System.in);
    }
    public void start()
    {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();
        if (authenticateUser(userId, pin)) 
        {
            System.out.println("Login successful!");
            displayMenu();
            int choice;
            do 
            {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); 
                performOperation(choice);
            } 
            while (choice != 5); 
            	System.out.println("Thank you for using the ATM. Goodbye!");
        } 
        else 
        {
            System.out.println("Invalid user ID or PIN. Access denied.");
        }
    }
    private boolean authenticateUser(String userId, String pin) 
    {
        if (userId.equals("123456") && pin.equals("1234")) 
        {
            currentUser = new User(userId, pin, 1000.0);
            return true;
        }

        return false;
    }
    private void displayMenu() 
    {
        System.out.println("\nATM Menu");
        System.out.println("1. Transactions History");
        System.out.println("2. Withdraw");
        System.out.println("3. Deposit");
        System.out.println("4. Transfer");
        System.out.println("5. Quit");
    }
    private void performOperation(int choice) 
    {
        switch (choice) 
        {
            case 1:
                displayTransactionHistory();
                break;
            case 2:
                performWithdrawal();
                break;
            case 3:
                performDeposit();
                break;
            case 4:
                performTransfer();
                break;
            case 5:
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
    private void displayTransactionHistory()
    {
        System.out.println("\nTransaction History");
        List<String> transactionHistory = currentUser.getTransactionHistory();
        if (transactionHistory.isEmpty()) 
        {
            System.out.println("No transactions found.");
        }
        else 
        {
            for (String transaction : transactionHistory)
            {
                System.out.println(transaction);
            }
        }
    }
    private void performWithdrawal() 
    {
        System.out.print("Enter the amount to withdraw: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        if (amount <= 0) 
        {
            System.out.println("Invalid amount. Withdrawal failed.");
        } 
        else if (amount > currentUser.getBalance()) 
        {
            System.out.println("Insufficient balance. Withdrawal failed.");
        } 
        else 
        {
            double newBalance = currentUser.getBalance() - amount;
            currentUser.setBalance(newBalance);
            currentUser.addTransaction("Withdrawal: $" + amount);
            System.out.println("Withdrawal successful. Current balance: $" + newBalance);
        }
    }
    private void performDeposit() 
    {
        System.out.print("Enter the amount to deposit: ");
        double amount = scanner.nextDouble();
        scanner.nextLine(); 
        if (amount <= 0) 
        {
            System.out.println("Invalid amount. Deposit failed.");
        } 
        else 
        {
            double newBalance = currentUser.getBalance() + amount;
            currentUser.setBalance(newBalance);
            currentUser.addTransaction("Deposit: $" + amount);
            System.out.println("Deposit successful. Current balance: $" + newBalance);
        }
    }
    private void performTransfer() 
    {
        System.out.print("Enter the user ID to transfer funds: ");
        String recipientId = scanner.nextLine();
        if (recipientId.equals("123456")) 
        {
            System.out.print("Enter the amount to transfer: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); 
            if (amount <= 0) 
            {
                System.out.println("Invalid amount. Transfer failed.");
            } 
            else if (amount > currentUser.getBalance())
            {
                System.out.println("Insufficient balance. Transfer failed.");
            } 
            else 
            {
                double newBalance = currentUser.getBalance() - amount;
                currentUser.setBalance(newBalance);
                currentUser.addTransaction("Transfer to " + recipientId + ": $" + amount);
                System.out.println("Transfer successful. Current balance: $" + newBalance);
            }
        } 
        else 
        {
            System.out.println("Recipient user ID not found. Transfer failed.");
        }
    }
    public static void main(String[] args) 
    {
        ATMInterface atmInterface = new ATMInterface();
        atmInterface.start();
    }
}
