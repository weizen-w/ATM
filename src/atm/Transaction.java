package atm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Transaction {//TODO read all transaction at start or file params.ini

  private static final SimpleDateFormat formatter =
      new SimpleDateFormat("dd.MM.yyyy,hh:mm:ss");
  private static int numberID = 0;
  private final int currentNumberID;
  private final Date date;
  private final double sum;
  private final String comment;
  private final boolean debitKredit;

  public static void setNumberID(int numberID) {
    Transaction.numberID = numberID;
  }

  public Transaction(Date date, double sum, String comment, boolean debitKredit) {
    this.currentNumberID = numberID;
    numberID++;
    this.date = date;
    this.sum = sum;
    this.comment = comment;
    this.debitKredit = debitKredit;
  }

  public Transaction(int currentNumberID, Date date, double sum, String comment, boolean debitKredit) {
    this.currentNumberID = currentNumberID;
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

  public int getCurrentNumberID() {
    return currentNumberID;
  }

  public String toWrite() {
    String sumStr = String.format("%.2f", sum);
    sumStr = sumStr.replace(',', '.');
    return String.format("%d;%s;%s;%s;%b", numberID, formatter.format(date), sumStr, comment,
        debitKredit);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Transaction that = (Transaction) o;
    return Double.compare(that.sum, sum) == 0 && Objects.equals(date, that.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, sum);
  }

  @Override
  public String toString() {
    return String.format("%d\t| %s\t| %.2f\t| %s\t| %b",
        currentNumberID, date, sum, comment, debitKredit);
  }
}
