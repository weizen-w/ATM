package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import atm.Transaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TransactionTests {


  @Test
  public void testConstructorNumberId() {
    //arrange
    Date expectedDate = new Date();
    double expectedSum = 999.87;
    String expectedComment = "some text to test";
    boolean expectedDebitKredit = false;
    Transaction.setNumberID(0);
    //arrange in act
    Transaction transaction = new Transaction(expectedDate, expectedSum, expectedComment,
        expectedDebitKredit);
    //assert
    assertEquals(1, Transaction.getNumberID());
    assertNotEquals(2, Transaction.getNumberID());

    //arrange in act #2
    Transaction transaction2 = new Transaction(expectedDate, expectedSum, expectedComment,
        expectedDebitKredit);
    //assert #2
    assertEquals(2, Transaction.getNumberID());
    assertNotEquals(1, Transaction.getNumberID());
  }

  @Test
  public void testToString() {
    //arrange
    Date expectedDate = new Date();
    double expectedSum = 999.87;
    String expectedComment = "some text to test";
    boolean expectedDebitKredit = false;
    Transaction transaction = new Transaction(expectedDate, expectedSum, expectedComment,
        expectedDebitKredit);
    //arrange in act
    String expected = String.format("%d\t| %s\t| %f\t| %s\t| %b", Transaction.getNumberID(),
        expectedDate, expectedSum, expectedComment, expectedDebitKredit);
    String actual = transaction.toString();

    //assert
    assertEquals(expected, actual);
  }

  @Test
  public void testToWrite() {
    //arrange
    Date expectedDate = new Date();
    double expectedSum = 999.87;
    String expectedSumStr = "999.87";
    String expectedComment = "some text to test";
    boolean expectedDebitKredit = false;
    Transaction transaction = new Transaction(expectedDate, expectedSum, expectedComment,
        expectedDebitKredit);
    SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
    //arrange in act
    String expected = String.format("%d;%s;%s;%s;%b", Transaction.getNumberID(),
        formatter.format(expectedDate), expectedSumStr, expectedComment,
        expectedDebitKredit);
    String actual = transaction.toWrite();

    //assert
    assertEquals(expected, actual);
  }

  @Test
  public void testConstructor() {
    //arrange
    Date expectedDate = new Date();
    double expectedSum = 999.87;
    String expectedComment = "some text to test";
    boolean expectedDebitKredit = false;
    Transaction.setNumberID(0);
    //arrange in act
    Transaction transaction = new Transaction(expectedDate, expectedSum, expectedComment,
        expectedDebitKredit);
    //assert
    assertEquals(1, Transaction.getNumberID());
    assertEquals(expectedDate, transaction.getDate());
    assertEquals(expectedSum, transaction.getSum());
    assertEquals(expectedComment, transaction.getComment());
    assertEquals(expectedDebitKredit, transaction.isDebitKredit());

  }
}
