package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import atm.Bank;
import atm.Client;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTests {

  @Test
  public void testConstructor() {
    // arrange
    String name = "ProjectBank";
    String address = "10178 Berlin, Panorama str. 1A";
    // arrange & act
    Bank bank = new Bank(name, address);
    // assert
    assertEquals(name, bank.getName());
    assertEquals(address, bank.getAddress());
  }

  @ParameterizedTest
  @CsvSource({
      "'ProjectBank', '10178 Berlin, Panorama str. 1A'",
  })
  public void testToString(String name, String address) {
    // arrange
    Bank bank = new Bank(name, address);
    String expected = "Bank name: " + name + ", address: " + address;
    // arrange & act
    String actual = bank.toString();
    // assert
    assertEquals(expected, actual);
  }

  @Test
  public void testReadFromFileMapClientPin() throws IOException {
    //arrange
    HashMap<String, String> expected = new HashMap<>();
    expected.put("Oleksandr", "1111");
    expected.put("Wladimir", "8765");
    expected.put("Kateryna", "9012");
    //act
    HashMap<String, String> actual = Bank.readFromFileMapClientPin();
    //assert
    assertEquals(expected, actual);
    assertEquals(actual, expected);
  }

  @Test
  public void testReadFromFileMapClientPinNotEquals() throws IOException {
    //arrange
    HashMap<String, String> expected = new HashMap<>();
    expected.put("Oleksandr", "1111");
    expected.put("Wladimir", "8765");
    expected.put("Kateryna", "0000");
    //act
    HashMap<String, String> actual = Bank.readFromFileMapClientPin();
    //assert
    assertNotEquals(expected, actual);
    assertNotEquals(actual, expected);
  }

  @Test
  public void testMakeListClients() throws IOException {
    //arrange
    List<Client> expected = new ArrayList<>();
    expected.add(new Client("Oleksandr"));
    expected.add(new Client("Wladimir"));
    expected.add(new Client("Kateryna"));
    //act
    Bank.mapClientPin = Bank.readFromFileMapClientPin();
    List<Client> actual = Bank.makeListClients();
    //assert
    assertEquals(expected, actual);
    assertEquals(actual, expected);
  }

  @Test
  public void testMakeListClientsNotEquals() throws IOException {
    //arrange
    List<Client> expected = new ArrayList<>();
    expected.add(new Client("OlEkSaNdRrRrRrRr"));
    expected.add(new Client("Wladimir"));
    expected.add(new Client("Kateryna"));
    //act
    Bank.mapClientPin = Bank.readFromFileMapClientPin();
    List<Client> actual = Bank.makeListClients();
    //assert
    assertNotEquals(expected, actual);
    assertNotEquals(actual, expected);
  }
}
