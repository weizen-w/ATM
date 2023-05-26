import atm.Account;
import atm.Atm;
import atm.Bank;
import atm.Client;
import atm.ClientNameComparator;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException, ParseException {
    Scanner scanner = new Scanner(System.in);
    Bank bank = new Bank("ProjectBank", "10178 Berlin, Panorama str. 1A");
    Atm atm = new Atm(15299, "10969 Berlin, Moritz str. 17", bank);
    Bank.mapClientPin = Bank.readFromFileMapClientPin();
    System.out.println(atm);
    int choiceMain, choiceSub, choiceAdmin;
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
            case 1 -> {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(account.readFromFileTransactionList());
              account.payment(scanner);
            }
            case 2 -> {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(account.readFromFileTransactionList());
              account.transfer(scanner);
            }
            case 3 -> {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(account.readFromFileTransactionList());
              account.deposit(scanner, atm);
            }
            case 4 -> {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(account.readFromFileTransactionList());
              account.withdraw(scanner, atm);
            }
            case 5 -> {
              Account account = client.selectAccount(scanner);
              account.setTransactionHistory(account.readFromFileTransactionList());
              System.out.println(account.getBalance());
            }

//            case 6 -> client.printHistory();
            case 7 -> Bank.changePIN(client, scanner);
          }
        } while (choiceSub != 0);
      }
      if (choiceMain == 1001) {
        List<Client> clientList = Bank.makeListClients();
        do {
          atm.printMenu(Atm.getAdminMenu());
          choiceAdmin = atm.selectMenu(scanner, Atm.getAdminMenu());
          switch (choiceAdmin) {
            case 1 -> {
              clientList.sort(new ClientNameComparator());
              for (Client client : clientList) {
                List<Account> clientAccountList = Client.readFromFileAccountsList(client);
                System.out.printf("Client: %s\n%s\n", client, clientAccountList);
              }
            }
//            case 2 -> {}
//            case 3 -> {}
//            case 4 -> {}
          }
        } while (choiceAdmin != 0);
      }
    } while (choiceMain != 0);
    scanner.close();
  }
}
