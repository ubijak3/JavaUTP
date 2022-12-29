/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad3;

import zad2.InputConverter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*<-- niezbędne import */
public class Main {
  public static void main(String[] args) throws IOException {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
    FunctionThrow<String, List<String>, IOException> flines = s -> {
      Path path = Paths.get(s);
      Stream<String> fileStream = null;
      fileStream = Files.lines(path);
      List<String> fileLines = fileStream.collect(Collectors.toList());
      fileStream.close();
      return fileLines;
    };

    FunctionThrow<List<String>,String,IOException> join = l -> {
      String concatenated = "";
      for (String s:l) {
        concatenated += s;
      }
      return concatenated;
    };

    FunctionThrow<String,List<Integer>,IOException> collectInts = s ->{
      List<Integer> integersList = new ArrayList<>();
      for (int i=0;i<s.length();i++) {
        if(Character.isDigit(s.charAt(i))){
          StringBuilder val = new StringBuilder();
          while (i < s.length() && Character.isDigit(s.charAt(i))){
            val.append(s.charAt(i++));
          }
          integersList.add(Integer.parseInt(val.toString()));
        }
      }
      return integersList;
    };

    FunctionThrow<List<Integer>, Integer,IOException> sum = integers -> {
      int intSum = 0;
      for (Integer i:integers) {
        if(i !=null) {
          intSum += i;
        }
      }
      return intSum;
    };

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
    System.out.println(fname);
    InputConverter<String> fileConv = new InputConverter<>(fname);
    List<String> lines = fileConv.convertBy(flines);
    String text = fileConv.convertBy(flines, join);
    List<Integer> ints = fileConv.convertBy(flines, join, collectInts);
    Integer sumints = fileConv.convertBy(flines, join, collectInts, sum);

    System.out.println(lines);
    System.out.println(text);
    System.out.println(ints);
    System.out.println(sumints);

    List<String> arglist = Arrays.asList(args);
    InputConverter<List<String>> slistConv = new InputConverter<>(arglist);
    sumints = slistConv.convertBy(join, collectInts, sum);
    System.out.println(sumints);

    // Zadania badawcze:
    // Operacja flines zawiera odczyt pliku, zatem może powstac wyjątek IOException
    // Wymagane jest, aby tę operację zdefiniowac jako lambda-wyrażenie
    // Ale z lambda wyrażeń nie możemy przekazywac obsługi wyjatków do otaczającego bloku
    // I wobec tego musimy pisać w definicji flines try { } catch { }
    // Jak spowodować, aby nie było to konieczne i w przypadku powstania wyjątku IOException
    // zadziałała klauzula throws metody main
  }

}
