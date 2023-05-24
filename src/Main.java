import atm.Atm;
import atm.Bank;
import atm.Client;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank();
    Atm atm = new Atm(15299, "10969 Berlin, Moritzstraße 17", bank);
    System.out.println(atm);
    int choiceMain;
    int choiceSub;
    do {
      atm.printMenu(Atm.getMainMenu());
      choiceMain = atm.selectMenu(scanner, Atm.getMainMenu());
      if (choiceMain == 1) {
        Client client = Bank.authorization(scanner);
        do {
          atm.printMenu(Atm.getClientMenu());
          choiceSub = atm.selectMenu(scanner, Atm.getClientMenu());
          switch (choiceSub) {
            case 1 -> client.selectAccount(scanner).payment(scanner);
            case 2 -> client.selectAccount(scanner).transfer(scanner);
            case 3 -> client.selectAccount(scanner).deposit(scanner);
            case 4 -> client.selectAccount(scanner).withdraw(scanner);
            case 5 -> System.out.println(client.balance());
            case 6 -> client.printHistory();
            case 7 -> Bank.changePIN(client);
          }
        } while (choiceSub != 0);
      }
    } while (choiceMain != 0);
    scanner.close();
  }
}
