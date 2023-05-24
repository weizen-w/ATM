import atm.Account;
import atm.Atm;
import atm.Bank;
import atm.Client;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank("ProjectBank", "10178 Berlin, Panoramastr. 1A");
    Atm atm = new Atm(15299, "10969 Berlin, Moritzstr. 17", bank);
    Bank.mapClientPin = Bank.readFromFileMapClientPin();
    System.out.println(atm);
    int choiceMain;
    int choiceSub;
    do {
      atm.printMenu(Atm.getMainMenu());
      choiceMain = atm.selectMenu(scanner, Atm.getMainMenu());
      if (choiceMain == 1) {
        Client client = Bank.authorization(scanner);
        List<Account> clientAccountList = Client.readFromFileAccountsList(client);
        client.setAccounts(clientAccountList);
        do {
          atm.printMenu(Atm.getClientMenu());
          choiceSub = atm.selectMenu(scanner, Atm.getClientMenu());
          switch (choiceSub) {
            case 1: {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(
                  account.readFromFileTransactionList(client, account.getAccountNumber()));
              account.payment(scanner);
            }
            case 2: {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(
                  account.readFromFileTransactionList(client, account.getAccountNumber()));
              account.transfer(scanner);
            }
            case 3: {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(
                  account.readFromFileTransactionList(client, account.getAccountNumber()));
              account.deposit(scanner, atm);
            }
            case 4: {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(
                  account.readFromFileTransactionList(client, account.getAccountNumber()));
              account.withdraw(scanner, atm);
            }
//            case 5 -> System.out.println(client.balance());
//            case 6 -> client.printHistory();
//            case 7 -> Bank.changePIN(client, scanner);
          }
        } while (choiceSub != 0);
      }
    } while (choiceMain != 0);
    scanner.close();
  }
}
