package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import atm.Atm;
import atm.Bank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AtmTests {

  @Test
  public void testConstructor() {
    // arrange
    int num = 15566;
    String address = "10969 Berlin, Moritzstr. 17";
    Bank bank = new Bank("ProjectBank", "10178 Berlin, Panoramastr. 1A");
    // arrange & act
    Atm atm = new Atm(num, address, bank);
    // assert
    assertEquals(num, atm.getNum());
    assertEquals(address, atm.getAddress());
    assertEquals(bank, atm.getBank()); // TODO
  }

  @ParameterizedTest
  @CsvSource({
      "15566, '10969 Berlin, Moritzstr. 17', 'ProjectBank', '10178 Berlin, Panoramastr. 1A'",
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
