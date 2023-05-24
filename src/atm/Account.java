package atm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Account {
  private String accountNumber;
  private double balance;
  private List<Transaction> transactionHistory;

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
    this.balance = 0.0;
    this.transactionHistory = new ArrayList<>();
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public double getBalance() {
    return balance;
  }

  public List<Transaction> getTransactionHistory() {
    return transactionHistory;
  }

  public void payment(Scanner scanner) {
    System.out.print("Enter the payment amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character
    System.out.println("Enter the comment");
    String comment = scanner.nextLine();
    Date date = new Date();
    Transaction transaction = new Transaction(date, amount, comment, false);
    if (amount > 0) {
      balance += amount;
      transactionHistory.add(transaction);
      System.out.println("Payment successful.");
    } else {
      System.out.println("Invalid payment amount. Payment failed.");
    }
  }

  public void transfer(Scanner scanner, Account recipient) {
    System.out.print("Enter the transfer amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    if (amount > 0 && amount <= balance) {
      balance -= amount;
      recipient.balance += amount;
      transactionHistory.add("Transfer of $" + amount + " to Account " + recipient.getAccountNumber());
      System.out.println("Transfer successful.");
    } else {
      System.out.println("Invalid transfer amount. Transfer failed.");
    }
  }

  public void deposit(Scanner scanner) {
    System.out.print("Enter the deposit amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    if (amount > 0) {
      balance += amount;
      transactionHistory.add("Deposit of $" + amount);
      System.out.println("Deposit successful.");
    } else {
      System.out.println("Invalid deposit amount. Deposit failed.");
    }
  }

  public void withdraw(Scanner scanner) {
    System.out.print("Enter the withdrawal amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character

    if (amount > 0 && amount <= balance) {
      balance -= amount;
      transactionHistory.add("Withdrawal of $" + amount);
      System.out.println("Withdrawal successful.");
    } else {
      System.out.println("Invalid withdrawal amount. Withdrawal failed.");
    }
  }

  public void printTransactionHistory() {
    System.out.println("Transaction history for Account " + accountNumber + ":");
    for (String transaction : transactionHistory) {
      System.out.println(transaction);
    }
  }
}