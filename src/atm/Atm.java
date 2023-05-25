package atm;

import java.util.Scanner;

public class Atm {

  private static final String[] mainMenu = {"--- Main Menu ---", "1. Authorization", "0. Exit"};
  private static final String[] clientMenu = {
      "--- Client Menu ---",
      "1. Pay",
      "2. Transfer",
      "3. Cash deposit",
      "4. Cash withdraw",
      "5. Balance",
      "6. History transactions (in development)",
      "7. Change PIN-code (in test's)",
      "0. Back to main menu"
  };
  private static final String[] adminMenu = {
      "--- Admin Menu ---",
      "1. List of clients (sort by name)",
      "2. List of clients (sort by balance) (in development)",
      "3. List of clients (sort by date) (in development)",
      "4. Closing an account (in development)",
      "0. Back to main menu"
  };
  private int num;
  private String address;
  private final Bank bank;

  public Atm(int num, String address, Bank bank) {
    this.num = num;
    this.address = address;
    this.bank = bank;
  }

  public static String[] getMainMenu() {
    return mainMenu;
  }

  public static String[] getClientMenu() {
    return clientMenu;
  }

  public static String[] getAdminMenu() {
    return adminMenu;
  }

  public int getNum() {
    return num;
  }

  public String getAddress() {
    return address;
  }

  public Bank getBank() {
    return bank;
  }

  public void setNum(int num) {
    this.num = num;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void printMenu(String[] args) {
    for (String str : args) {
      System.out.println(str);
    }
  }

  public int selectMenu(Scanner scanner, String[] args) {
    int choice;
    System.out.print("Please, select menu number: ");
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.print("Invalid input. Enter the number: ");
    }
    choice = scanner.nextInt();
    while (choice < 0 || choice >= args.length - 1) { // -1(Название меню)
      if (choice == 1001) {
        return choice;
      }
      System.out.print("Incorrect menu number. Enter the number: ");
      while (!scanner.hasNextInt()) {
        scanner.next();
        System.out.print("Invalid input. Enter the number: ");
      }
      choice = scanner.nextInt();
    }
    scanner.nextLine();
    return choice;
  }

  @Override
  public String toString() {
    return String.format("===  ATM No.%d  ===\n%s\n***  %s  ***", num, address, bank);
  }
}
