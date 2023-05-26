package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import atm.Account;
import atm.Client;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ClientTests {

  @Test
  public void testConstructor() {
    // arrange
    String name = "Jon";
    // arrange & act
    Client client = new Client(name);
    // assert
    assertEquals(name, client.getName());
  }

  @Test
  public void testReadFromFileAccountsList() throws FileNotFoundException {
    // arrange
    Client wladimir = new Client("Wladimir");
    List<Account> wladimirAcc = new ArrayList<>();
    wladimirAcc.add(new Account("12381271213"));
    wladimirAcc.add(new Account("12983737677"));
    wladimirAcc.add(new Account("99876543232"));
    // act
    List<Account> actual = Client.readFromFileAccountsList(wladimir);
    // assert
    assertEquals(wladimirAcc, actual);
    assertEquals(actual, wladimirAcc);
  }

  @Test
  public void testReadFromFileAccountsListNotEquals() throws FileNotFoundException {
    // arrange
    Client wladimir = new Client("Wladimir");
    List<Account> wladimirAcc = new ArrayList<>();
    wladimirAcc.add(new Account("12381271213"));
    wladimirAcc.add(new Account("1298Test37677"));
    // act
    List<Account> actual = Client.readFromFileAccountsList(wladimir);
    // assert
    assertNotEquals(wladimirAcc, actual);
    assertNotEquals(actual, wladimirAcc);
  }

  @Test
  public void testToString() {
    // arrange
    String name = "Jon";
    Client client = new Client(name);
    // arrange & act
    String actual = client.toString();
    // assert
    assertEquals(name, actual);
  }

  @Test
  public void testBalance() {
    //arrange
    Client wladimir = new Client("Wladimir");
    List<Account> wladimirAcc = new ArrayList<>();
    wladimirAcc.add(new Account("12381271213"));
    wladimirAcc.add(new Account("12983737677"));
    wladimirAcc.add(new Account("99876543232"));
    wladimir.setAccounts(wladimirAcc);
    double expectedBalance = 0.0;
    for (Account acc : wladimirAcc) {
      expectedBalance += acc.getBalance();
    }
    // arrange & act
    double actualBalance = wladimir.balance();
    //assert
    assertEquals(expectedBalance, actualBalance);
  }


}
