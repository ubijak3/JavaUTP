/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad2;


public class Purchase implements Comparable<Purchase>{
    String id;
    String surname;
    String item;
    double price;
    double quantity;

    public Purchase(String id, String surname, String item, double price, double quantity) {
        this.id = id;
        this.surname = surname;
        this.item = item;
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + ";" + surname + ";" + item + ";" + price + ";" + quantity;
    }

    @Override
    public int compareTo(Purchase o) {
        int res = surname.compareTo(o.surname);
        if(res == 0) {
            res = Integer.parseInt(id.substring(1,6)) - Integer.parseInt(o.id.substring(1,6));
        }
        return res;
    }
}
