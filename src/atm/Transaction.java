package atm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
    return String.format("%d\t| %s\t| %f\t| %s\t| %b", numberID, date, sum, comment, debitKredit);
  }

  public String toWrite() {
    return String.format("%d;%s;%f;%s;%b", numberID, date, sum, comment, debitKredit);
  }
}
