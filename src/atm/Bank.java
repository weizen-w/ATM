package atm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {

  private String name;
  private String address;
  private static final String SEP = ";";
  private static final String CLIENTS = "res/clients.csv";
  public static HashMap<String, String> mapClientPin;

  public Bank(String name, String address) {
    this.name = name;
    this.address = address;
  }

  public String getName() {
    return name;
  }

  public String getAddress() {
    return address;
  }

  public static HashMap<String, String> getMapClientPin() {
    return mapClientPin;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public static void setMapClientPin(HashMap<String, String> mapClientPin) {
    Bank.mapClientPin = mapClientPin;
  }

  public static Client authorization(Scanner scanner) throws IOException {
    boolean authSuccess = false;
    String name;
    if (mapClientPin.isEmpty()) {
      mapClientPin = readFromFileMapClientPin();
    }
    System.out.println();
    System.out.println("*** Authorization ***");
    System.out.println();
    do {//TODO count try auth if than as, return null
      System.out.print("Please input your login:");
      name = scanner.nextLine();
      if (mapClientPin.containsKey(name)) {
        System.out.print("Please input your PIN:");
        String pin = scanner.nextLine();
        if (mapClientPin.get(name).equals(pin)) {
          System.out.println("PIN ok!");
          authSuccess = true;
        } else {
          System.out.println("Wrong PIN!");
        }
      } else {
        System.out.println("Wrong login!");
      }
    } while (!authSuccess);
    return new Client(name);
  }

  public static void changePIN(Client client, Scanner scanner) throws IOException {
    String login = client.getName();
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
    writeToFileMapClientPin(mapClientPin);
  }

  public static HashMap<String, String> readFromFileMapClientPin() throws FileNotFoundException {
    HashMap<String, String> clientPin = new HashMap<>();
    Scanner scanner = new Scanner(new FileReader(CLIENTS));
    String str;
    while (scanner.hasNext()) {
      str = scanner.nextLine();
      String name = str.substring(0, str.indexOf(SEP));
      String pin = str.substring(str.indexOf(SEP) + 1);
      clientPin.put(name, pin);
    }
    scanner.close();
    return clientPin;
  }

  private static void writeToFileMapClientPin(Map<String, String> clientPin) throws IOException {

    FileWriter fileWriter = new FileWriter(CLIENTS);
    for (String key : clientPin.keySet()) {
      fileWriter.write(key + ";" + clientPin.get(key) + "\n");
    }
    fileWriter.close();
  }

  public static List<Client> makeListClients() {
    List<Client> clientList = new ArrayList<>();
    for (String str : mapClientPin.keySet()) {
      Client client = new Client(str);
      clientList.add(client);
    }
    return clientList;
  }

  @Override
  public String toString() {
    return "Bank name: " + name + ", address: " + address;
  }
}
