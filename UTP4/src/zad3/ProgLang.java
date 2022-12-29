package zad3;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Predicate;

public class ProgLang {

    LinkedHashMap<String, List<String>> progMap = new LinkedHashMap<>();
    LinkedHashMap<String, List<String>> langMap = new LinkedHashMap<>();

    public ProgLang(String fileName) {
        Path path = Path.of(fileName);
        try {
            Files.readAllLines(path).stream().forEach(s -> {
                LinkedHashSet<String> srcLine = new LinkedHashSet<>(Arrays.asList(s.split("\t")));
                String [] line = new String[srcLine.size()];
                srcLine.toArray(line);
                List<String> progs = new ArrayList<>();
                progs.addAll(Arrays.asList(line).subList(1,line.length));
                langMap.put(line[0], progs);

                for (String p: progs) {
                    if(!progMap.containsKey(p)){
                        progMap.put(p, new ArrayList<String>(Collections.singleton(line[0])));
                    }else{
                        if(!progMap.get(p).contains(line[0]))
                            progMap.get(p).add(line[0]);
                    }
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, List<String>> getLangsMap() {
        return langMap;
    }
    public Map<String, List<String>> getProgsMap() {
        return progMap;
    }
    public Map<String, List<String>> getLangsMapSortedByNumOfProgs(){
        return  sorted(langMap, (e1,e2) -> {
            int compare = Integer.compare(e2.getValue().size(),e1.getValue().size());
            return compare;
        });
    }
    public Map<String, List<String>> getProgsMapSortedByNumOfLangs(){
        return  sorted(progMap, (e1,e2) -> {
            int compare = Integer.compare(e2.getValue().size(),e1.getValue().size());
            if(compare == 0){
                compare = e1.getKey().compareTo(e2.getKey());
            }
            return compare;
        });
    }
    private static <K,V> Map<K,V> sorted(Map<K,V> mapToSort, Comparator<Map.Entry<K,V>> comp) {
        List<Map.Entry<K,V>> entries = new ArrayList<>(mapToSort.entrySet());
        entries.sort(comp);
        LinkedHashMap<K,V> retMap = new LinkedHashMap<>();
        entries.forEach(e -> retMap.put(e.getKey(),e.getValue()));
        return retMap;
    }
    public Map<String, List<String>> getProgsMapForNumOfLangsGreaterThan(int n){
        return filtered(progMap, p -> p.getValue().size() > n);
    }
    private <K,V> LinkedHashMap <K,V> filtered(Map<K,V> mapToFilter, Predicate<Map.Entry<K,V>> pred){
        LinkedHashMap<K,V> retMap = new LinkedHashMap<>();
        mapToFilter.entrySet()
                .stream()
                .filter(pred)
                .forEach(s -> retMap.put(s.getKey(),s.getValue()));
        return retMap;
    }


}
