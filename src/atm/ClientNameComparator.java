package atm;

import java.util.Comparator;

public class ClientNameComparator implements Comparator<Client> {

  @Override
  public int compare(Client o1, Client o2) {
    String o1Name = o1.getName().toLowerCase();
    String o2Name = o2.getName().toLowerCase();
    if (!o1Name.equals(o2Name)) {
      return o1Name.compareTo(o2Name);
    }
    return 0;
  }
  // TODO can make method reverse()
}
