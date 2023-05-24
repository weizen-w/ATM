package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import atm.Bank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class BankTests {

  @Test
  public void testConstructor() {
    // arrange
    String name = "ProjectBank";
    String address = "10178 Berlin, Panoramastr. 1A";
    // arrange & act
    Bank bank = new Bank(name, address);
    // assert
    assertEquals(name, bank.getName()); // TODO
    assertEquals(address, bank.getAddress()); // TODO
  }

  @ParameterizedTest
  @CsvSource({
      "'ProjectBank', '10178 Berlin, Panoramastr. 1A'",
  })
  public void testToString(String name, String address) {
    // arrange
    Bank bank = new Bank(name, address);
    String expected = "Bank{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
    // act
    String actual = bank.toString();
    // assert
    assertEquals(expected, actual);
  }
}
