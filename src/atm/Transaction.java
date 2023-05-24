package atm;

import java.util.Date;

public class Transaction {

  private static int numberID = 1;
  private final Date date;
  private final double sum;
  private final String comment;
  private final boolean debitKredit;

  public Transaction(Date date, double sum, String comment, boolean debitKredit) {
    numberID++;
    this.date = date;
    this.sum = sum;
    this.comment = comment;
    this.debitKredit = debitKredit;
  }

  public static int getNumberID() {
    return numberID;
  }

  public Date getDate() {
    return date;
  }

  public double getSum() {
    return sum;
  }

  public String getComment() {
    return comment;
  }

  public boolean isDebitKredit() {
    return debitKredit;
  }

  @Override
  public String toString() {
    return String.format("%d\t| %s\t| %f\t| %s\t| %b", numberID, date, sum, comment,
        debitKredit);
  }
}
