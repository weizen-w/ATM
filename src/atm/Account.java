package atm;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Account {

  private final String accountNumber;
  private double balance = 0.0;
  private static final String SEP = ";";
  private List<Transaction> transactionHistory;
  private static final SimpleDateFormat formatter =
      new SimpleDateFormat("dd.MM.yyyy,hh:mm:ss");
  private String FILENAME;

  public Account(String accountNumber) {
    this.accountNumber = accountNumber;
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

  public String getFILENAME() {
    return FILENAME;
  }

  public void setBalance(double balance) {
    this.balance = balance;
  }

  public void setTransactionHistory(List<Transaction> transactionHistory) {
    this.transactionHistory = transactionHistory;
  }

  public List<Transaction> readFromFileTransactionList()
      throws IOException, ParseException {
    FILENAME = "res/" + accountNumber + ".csv";
    checkExistFile();
    balance = 0;
    Scanner scanner = new Scanner(new FileReader(FILENAME));
    List<Transaction> transList = new ArrayList<>();
    while (scanner.hasNextLine()) {
      String accString = scanner.nextLine();
      if (!accString.isEmpty()) {
        String[] strAfterSplit = accString.split(SEP);
        int numberID = Integer.parseInt(strAfterSplit[0]); // TODO
        Date date = formatter.parse(strAfterSplit[1]);
        double sum = Double.parseDouble(strAfterSplit[2]);
        String comment = strAfterSplit[3];
        boolean debitKredit = Boolean.parseBoolean(strAfterSplit[4]);
        transList.add(new Transaction(numberID, date, sum, comment, debitKredit));
        if (debitKredit) {
          balance += sum;
        } else {
          balance -= sum;
        }
      }
    }
    return transList;
  }

  private void checkExistFile() throws IOException {
    File file = new File(FILENAME);
    if (!file.exists()) {
      FileWriter fileWriter = new FileWriter(FILENAME);
      fileWriter.close();
    }
  }

  public void payment(Scanner scanner) throws IOException {
    //TODO show balance before and after
    System.out.print("Enter the payment amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character
    System.out.println("Enter the comment");
    String comment = scanner.nextLine();
    Date date = new Date();
    Transaction transaction = new Transaction(date, amount, comment, false);
    if (amount > 0 && amount <= balance) {
      balance += amount;
      transactionHistory.add(transaction);
      writeTransactionToFile(transaction, FILENAME);
      System.out.println("Payment successful.");
    } else {
      System.out.println("Invalid payment amount. Payment failed.");
    }
  }

  public void transfer(Scanner scanner) throws IOException {
    //TODO show balance before and after
    System.out.print("Enter the transfer amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      System.out.print("Enter a description of transfer: ");
      String commentUser = scanner.nextLine();
      System.out.print("Enter an account of recipient: ");
      String accountRecipient = scanner.nextLine();
      //TODO Seek account in this bank, and if it's, than add transaction to recipients account
      String comment = commentUser + " to accNo:" + accountRecipient;
      Transaction transaction = new Transaction(new Date(), amount, comment, false);
      transactionHistory.add(transaction);
      writeTransactionToFile(transaction, FILENAME);
      System.out.println("Transfer successful.");
    } else {
      System.out.println("Invalid transfer amount. Transfer failed.");
    }
  }

  public void deposit(Scanner scanner, Atm atm) throws IOException {
    System.out.print("Enter the deposit amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character
    if (amount > 0) {
      balance += amount;
      String comment = "Cash-in in ATM No." + atm.getNum();
      Transaction transaction = new Transaction(new Date(), amount, comment, true);
      transactionHistory.add(transaction);
      writeTransactionToFile(transaction, FILENAME);
      System.out.println("Deposit successful.");
    } else {
      System.out.println("Invalid deposit amount. Deposit failed.");
    }
  }

  public void withdraw(Scanner scanner, Atm atm) throws IOException {
    //TODO show balance before and after
    System.out.print("Enter the withdrawal amount: ");
    double amount = scanner.nextDouble();
    scanner.nextLine(); // Consume the newline character
    if (amount > 0 && amount <= balance) {
      balance -= amount;
      String comment = "Cash-out in ATM No." + atm.getNum();
      Transaction transaction = new Transaction(new Date(), amount, comment, false);
      transactionHistory.add(transaction);
      writeTransactionToFile(transaction, FILENAME);
      System.out.println("Withdrawal successful.");
    } else {
      System.out.println("Invalid withdrawal amount. Withdrawal failed.");
    }
  }

  public void printTransactionHistory() {
    System.out.println("Transaction history for Account " + accountNumber + ":");
    for (Transaction transaction : transactionHistory) {
      System.out.println(transaction);
    }
  }

  private void writeTransactionToFile(Transaction t, String filename) throws IOException {
    List<String> stringFromFile = new ArrayList<>();
    Scanner scanner = new Scanner(new FileReader(filename));
    while (scanner.hasNextLine()) {
      String str = scanner.nextLine();
      stringFromFile.add(str);
    }
    scanner.close();
    stringFromFile.add(t.toWrite());
    FileWriter fileWriter = new FileWriter(filename);
    for (String str : stringFromFile) {
      fileWriter.write(str + "\n");
    }
    fileWriter.close();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Account account = (Account) o;
    return Objects.equals(accountNumber, account.accountNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accountNumber);
  }

  @Override
  public String toString() {
    return "AccountNumber: " + accountNumber;
  }
}
