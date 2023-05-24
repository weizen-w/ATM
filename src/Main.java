import atm.Atm;
import atm.Bank;
import atm.Client;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Atm atm = new Atm();
    int choiceMain;
    int choiceSub;
    do {
      atm.printMenu();
      choiceMain = atm.selectMenu(scanner);
      if (choiceMain == 1) {
        Client client = Bank.authorization(scanner);
        do {
          atm.printMenu();
          choiceSub = atm.selectMenu(scanner);
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
