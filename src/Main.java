import java.util.*;

public class Main {

    static LinkedList<BankAccount> accounts = new LinkedList<>();
    static Stack<String> history = new Stack<>();
    static Queue<String> billQueue = new LinkedList<>();
    static Queue<BankAccount> requests = new LinkedList<>();

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        accounts.add(new BankAccount(1, "Ali", 150000));
        accounts.add(new BankAccount(2, "Sara", 220000));

        menu();
    }

    // menu
    public static void menu() {
        while (true) {
            System.out.println("\n1 – Bank\n2 – ATM\n3 – Admin\n4 – Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: bankMenu(); break;
                case 2: atmMenu(); break;
                case 3: adminMenu(); break;
                case 4: return;
            }
        }
    }

    // bank
    public static void bankMenu() {
        System.out.println("\n1 – Deposit\n2 – Withdraw\n3 – Request Account\n4 – Pay Bill");
        int c = sc.nextInt();

        if (c == 1) {
            System.out.print("Username: ");
            String name = sc.next();
            System.out.print("Amount: ");
            double amt = sc.nextDouble();

            deposit(name, amt);
            history.push("Deposit " + amt + " to " + name);

        } else if (c == 2) {
            System.out.print("Username: ");
            String name = sc.next();
            System.out.print("Amount: ");
            double amt = sc.nextDouble();

            withdraw(name, amt);
            history.push("Withdraw " + amt + " from " + name);

        } else if (c == 3) {
            System.out.print("New username: ");
            String name = sc.next();

            requests.add(new BankAccount(0, name, 0));
            System.out.println("Request added!");

        } else if (c == 4) {
            System.out.print("Enter bill name: ");
            String bill = sc.next();

            billQueue.add(bill);
            System.out.println("Bill added!");
        }
    }

    // atm
    public static void atmMenu() {
        System.out.print("Username: ");
        String name = sc.next();

        for (BankAccount acc : accounts) {
            if (acc.username.equals(name)) {
                System.out.println("Balance: " + acc.balance);

                System.out.print("Withdraw amount: ");
                double amt = sc.nextDouble();
                withdraw(name, amt);
                return;
            }
        }

        System.out.println("Account not found");
    }

    // admin
    public static void adminMenu() {
        System.out.println("\n1 – Process Account Requests\n2 – Process Bills\n3 – Show Last Transaction\n4 – Show Bills Queue\n5 – Show Account Requests");
        int c = sc.nextInt();

        if (c == 1) {
            if (!requests.isEmpty()) {
                BankAccount acc = requests.poll();
                accounts.add(acc);
                System.out.println("Account created: " + acc.username);
            } else {
                System.out.println("No requests");
            }

        } else if (c == 2) {
            if (!billQueue.isEmpty()) {
                System.out.println("Processing: " + billQueue.poll());
            } else {
                System.out.println("No bills");
            }

        } else if (c == 3) {
            if (!history.isEmpty()) {
                System.out.println("Last: " + history.peek());
            } else {
                System.out.println("No transactions");
            }

        } else if (c == 4) {
            System.out.println("Bills Queue:");
            for (String bill : billQueue) {
                System.out.println(bill);
            }
        }
        else if (c == 5) {
            System.out.println("Pending requests:");
            for (BankAccount acc : requests) {
                System.out.println(acc.username);
            }
        }
    }

    // logic
    public static void deposit(String username, double amount) {
        for (BankAccount acc : accounts) {
            if (acc.username.equals(username)) {
                acc.balance += amount;
                System.out.println("New balance: " + acc.balance);
                return;
            }
        }
        System.out.println("User not found");
    }

    public static void withdraw(String username, double amount) {
        for (BankAccount acc : accounts) {
            if (acc.username.equals(username)) {
                if (acc.balance >= amount) {
                    acc.balance -= amount;
                    System.out.println("New balance: " + acc.balance);
                } else {
                    System.out.println("Not enough money");
                }
                return;
            }
        }
        System.out.println("User not found");
    }

    // array
    public static void arrayExample() {
        BankAccount[] arr = new BankAccount[3];

        arr[0] = new BankAccount(1, "Ali", 100000);
        arr[1] = new BankAccount(2, "Sara", 200000);
        arr[2] = new BankAccount(3, "John", 300000);

        for (BankAccount acc : arr) {
            acc.display();
        }
    }
}