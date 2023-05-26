package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import atm.Account;
import atm.Transaction;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class AccountTests {

  @Test
  public void testConstructor() {
    // arrange
    String accountNumber = "052458798";
    // arrange & act
    Account account = new Account(accountNumber);
    // assert
    assertEquals(accountNumber, account.getAccountNumber());
  }

  @Test
  public void testReadFromFileTransactionList() throws IOException, ParseException {
    // arrange
    List<Transaction> expectedList = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    Transaction transaction = new Transaction(formatter.parse("25.05.2023"), 9999.99,
        "Cash-in in ATM No.15299", true);
    expectedList.add(transaction);
    double balanceExpected = 9999.99;
    Account account = new Account("99876543232");
    // arrange & act
    List<Transaction> actual = account.readFromFileTransactionList();
    // assert
    assertEquals(expectedList, actual);
    assertEquals(balanceExpected, account.getBalance());
  }

  @Test
  public void testReadFromFileTransactionListNotEquals() throws IOException, ParseException {
    // arrange
    List<Transaction> expectedList = new ArrayList<>();
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    Transaction transaction = new Transaction(formatter.parse("10.05.2023"), 9999.99,
        "Cash-in in ATM No.15299", true);
    expectedList.add(transaction);
    double balanceExpected = 999.99;
    Account account = new Account("99876543232");
    // arrange & act
    List<Transaction> actual = account.readFromFileTransactionList();
    // assert
    assertNotEquals(expectedList, actual);
    assertNotEquals(balanceExpected, account.getBalance());
  }
}
