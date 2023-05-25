package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import atm.Atm;
import atm.Bank;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AtmTests {

  @ParameterizedTest
  @CsvSource({
      "15566, '10969 Berlin, Moritz str. 17', 'ProjectBank', '10178 Berlin, Panorama str. 1A'",
  })
  public void testConstructor(int num, String address, String nameBank, String addressBank) {
    // arrange
    Bank bank = new Bank(nameBank, addressBank);
    // arrange & act
    Atm atm = new Atm(num, address, bank);
    // assert
    assertEquals(num, atm.getNum());
    assertEquals(address, atm.getAddress());
    assertEquals(bank, atm.getBank());
  }

  @ParameterizedTest
  @CsvSource({
      "15566, '10969 Berlin, Moritz str. 17', 'ProjectBank', '10178 Berlin, Panorama str. 1A'",
  })
  public void testToString(int num, String address, String nameBank, String addressBank) {
    // arrange
    Bank bank = new Bank(nameBank, addressBank);
    Atm atm = new Atm(num, address, bank);
    String expected = String.format("===  ATM No.%d  ===\n%s\n***  %s  ***", num, address, bank);
    // act
    String actual = atm.toString();
    // assert
    assertEquals(expected, actual);
  }
}
