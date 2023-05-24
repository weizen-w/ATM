package atm;

import java.util.Scanner;

public class Atm {

  private static final String[] mainMenu = {"--- Main Menu ---","1. Authorization", "0. Exit"};
  private static final String[] clientMenu = {
      "--- Client Menu ---",
      "1. Pay",
      "2. Transfer",
      "3. Cash deposit",
      "4. Cash withdraw",
      "5. Balance",
      "6. History transactions",
      "7. Change PIN-code",
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
      scanner.nextLine();
      System.out.print("Invalid input. Enter the number: ");
    }
    choice = scanner.nextInt();
    while (choice < 0 || choice > args.length - 1) { // -1(Название меню)
      System.out.println();
      while (!scanner.hasNextInt()) {
        scanner.nextLine();
        System.out.print("Invalid input. Enter the number: ");
      }
      choice = scanner.nextInt();
    }
    return choice;
  }

  @Override
  public String toString() {
    return String.format("===  ATM No.%d  ===\n%s\n***  %s  ***", num, address, bank);
  }
}
