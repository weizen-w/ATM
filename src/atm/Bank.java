package atm;

import javax.xml.namespace.QName;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Bank {

  private String name;
  private String address;

  public Bank(String name, String address) {
    this.name = name;
    this.address = address;
  }

  private static final String CLIENTS = "res/clients.csv";
  public static HashMap<String, String> mapClientPin;

  public static Client authorization(Scanner scanner) throws IOException {

    if (mapClientPin.isEmpty()) {
      mapClientPin = readFromFileMapClientPin();
    }

    // TODO
    System.out.print("Please input your login:");
    String name = scanner.nextLine();

    System.out.print("Please input your PIN:");
    String pin = scanner.nextLine();

    if (mapClientPin.containsKey(name)) {
      if (mapClientPin.get(name).equals(pin)) {
        return Client.getByName(name);// TODO
      }
      System.out.println("Wrong PIN!");
    }
    System.out.println("Wrong login!");
    return null;
  }

  public static void changePIN(Client client, Scanner scanner) {
    String login = client.getName();// TODO
    String oldPin = mapClientPin.get(login);

    System.out.print("Input old PIN:");
    String oldPinUser = scanner.nextLine();

    if (!oldPinUser.equals(oldPin)) {
      System.out.println("Wrong PIN!");
      return;
    }

    System.out.print("Input new PIN:");
    String newPinUser1 = scanner.nextLine();

    System.out.print("Input new PIN (for check):");
    String newPinUser2 = scanner.nextLine();

    if (!newPinUser1.equals(newPinUser2)) {
      System.out.println("Mismatch entered PINs!");
      return;
    }

    mapClientPin.put(login, newPinUser1);
    System.out.println("PIN successfully changed!");

  }

  private static HashMap<String, String> readFromFileMapClientPin() throws IOException {
    HashMap<String, String> clientPin = new HashMap<>();
    Scanner scanner = new Scanner(new FileReader(CLIENTS));

    String str;

    while (scanner.hasNext()) {
      str = scanner.nextLine();

      String name = str.substring(0, str.indexOf(";"));
      String pin = str.substring(str.indexOf(";") + 1);

      clientPin.put(name, pin);
    }

    scanner.close();
    return clientPin;
  }



  @Override
  public String toString() {
    return "Bank{" +
        "name='" + name + '\'' +
        ", address='" + address + '\'' +
        '}';
  }

}