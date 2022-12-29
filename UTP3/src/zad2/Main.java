/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad2;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*<--
 *  niezbędne importy
 */
public class Main {
  public static void main(String[] args) {
    /*<--
     *  definicja operacji w postaci lambda-wyrażeń:
     *  - flines - zwraca listę wierszy z pliku tekstowego
     *  - join - łączy napisy z listy (zwraca napis połączonych ze sobą elementów listy napisów)
     *  - collectInts - zwraca listę liczb całkowitych zawartych w napisie
     *  - sum - zwraca sumę elmentów listy liczb całkowitych
     */
      Function<String, List<String>> flines = s -> {
          Path path = Paths.get(s);
          Stream<String> fileStream = null;
          try {
              fileStream = Files.lines(path);
          } catch (IOException e) {
              throw new RuntimeException(e);
          }
          List<String> fileLines = fileStream.collect(Collectors.toList());
          fileStream.close();
          return fileLines;
      };

      Function<List<String>,String> join = l -> {
          String concatenated = "";
          for (String s:l) {
              concatenated += s;
          }
          return concatenated;
      };

      Function<String,List<Integer>> collectInts = s ->{
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

      Function<List<Integer>, Integer> sum = integers -> {
          int intSum = 0;
          for (Integer i:integers) {
              if(i !=null) {
                  intSum += i;
              }
          }
          return intSum;
      };

    String fname = System.getProperty("user.home") + "/LamComFile.txt";
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

  }
}
