/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class CustomersPurchaseSortFind {
    List<Purchase> purchaseList = new ArrayList<>();

    public void readFile(String fname) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fname));
            String line;
            while((line = br.readLine()) != null) {
                String[] lineArr = line.split(";");
                purchaseList.add(new Purchase(lineArr[0],lineArr[1],lineArr[2],Double.parseDouble(lineArr[3]),Double.parseDouble(lineArr[4])));
//                this.wordsList.addAll(Arrays.asList(line.split(" ")));
            }
//            System.out.println(purchaseList);
//            System.out.println(wordsList);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showSortedBy(String sortBy) {
        switch (sortBy) {
            case "Nazwiska":
                Collections.sort(purchaseList);
                System.out.println("Nazwiska");
                for (Purchase p:purchaseList) {
                    System.out.println(p);
                }
                break;
            case "Koszty":
                System.out.println();
                System.out.println("Koszty");
                purchaseList.sort(new Comparator<Purchase>() {
                    @Override
                    public int compare(Purchase o1, Purchase o2) {
                        int res = (int) ((o2.price*o2.quantity) - (o1.price*o1.quantity));
                        if(res == 0) res = Integer.parseInt(o1.id.substring(1,6)) - Integer.parseInt(o2.id.substring(1,6));
                        return res;
                    }
                });
                for (Purchase p :purchaseList) {
                    System.out.println(p + "(koszt: "+ p.quantity*p.price + ")");
                }
                break;
        }
    }

    public void showPurchaseFor(String id) {
        System.out.println();
        System.out.println("Klient "+ id);
        for (Purchase p :purchaseList) {
            if(p.id.equals(id)){
                System.out.println(p);
            }
        }
    }
}
