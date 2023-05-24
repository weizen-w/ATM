package atm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.naming.SizeLimitExceededException;

public class Client {

  private String name;
  private List<Account> accounts;

  public Client(String name) throws FileNotFoundException {
    this.name = name;
    this.accounts = readFromFileAccountsList(name);

  }

  private static List<Account> readFromFileAccountsList(String name) throws FileNotFoundException {
    String filename = "res/" + name + "_Acc.csv";
    Scanner scanner = new Scanner(new FileReader(filename));
    List<Account> accList = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String accString = scanner.nextLine();

      accList.add(new Account(accString));
    }
    return accList;
  }

  public String getName() {
    return name;
  }

  public Account selectAccount(Scanner scanner) {
    if (accounts.isEmpty()) {
      System.out.println("No accounts found for this client.");
      return null;
    }

    System.out.println("Select an account:");
    for (int i = 0; i < accounts.size(); i++) {
      System.out.println((i + 1) + ". " + accounts.get(i));
    }

    int choice = getIntInput(scanner, 1, accounts.size());
    return accounts.get(choice - 1);
  }

  public double balance() {
    double totalBalance = 0.0;
    for (Account account : accounts) {
      totalBalance += account.getBalance();
    }
    return totalBalance;
  }

  public void printHistory() {
    for (Account account : accounts) {
      System.out.println("Account: " + account);
      account.printTransactionHistory();
      System.out.println();
    }
  }

//  public static Client getByName(String name) {
//    for (Client client : Bank.getClients()) {
//      if (client.getName().equals(name)) {
//        return client;
//      }
//    }
//    return null;
//  }

  private static int getIntInput(Scanner scanner, int min, int max) {
    int choice;
    do {
      System.out.print("Enter a number between " + min + " and " + max + ": ");
      while (!scanner.hasNextInt()) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.next();
      }
      choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character
    } while (choice < min || choice > max);
    return choice;
  }
}
