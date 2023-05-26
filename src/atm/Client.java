package atm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Client {

  private String name;
  private List<Account> accounts;

  public Client(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<Account> getAccounts() {
    return accounts;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAccounts(List<Account> accounts) {
    this.accounts = accounts;
  }

  public static List<Account> readFromFileAccountsList(Client client) throws FileNotFoundException {
    String filename = "res/" + client.getName() + "_Acc.csv";
    Scanner scanner = new Scanner(new FileReader(filename));
    List<Account> accList = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String accString = scanner.nextLine();
      accList.add(new Account(accString));
    }
    return accList;
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
    int choice = getIntInput(scanner);
    return accounts.get(choice - 1);
  }

  public double balance() { // TODO
    double totalBalance = 0.0;
    for (Account account : accounts) {
      totalBalance += account.getBalance();
    }
    return totalBalance;
  }

  public void printHistory() { // TODO
    for (Account account : accounts) {
      System.out.println("Account: " + account);
      account.printTransactionHistory();
      System.out.println();
    }
  }

  public int getIntInput(Scanner scanner) {
    int choice;
    do {
      System.out.print("Enter a number between 1 and " + accounts.size() + ": ");
      while (!scanner.hasNextInt()) {
        System.out.println("Invalid input. Please enter a valid number.");
        scanner.next();
      }
      choice = scanner.nextInt();
      scanner.nextLine(); // Consume the newline character
    } while (choice < 1 || choice > accounts.size());
    return choice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Client client = (Client) o;
    return Objects.equals(name, client.name);
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }

  @Override
  public String toString() {
    return name;
  }
}
