/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class Anagrams {
    private List<String> wordsList;

    public Anagrams(String path) {
        this.wordsList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            String line;

            while((line = br.readLine()) != null) {
                this.wordsList.addAll(Arrays.asList(line.split(" ")));
            }
            System.out.println(wordsList);
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean areThoseTwoWordsAnagrams(String word1, String word2){
        char[] word1Arr = word1.toCharArray();
        Arrays.sort(word1Arr);
        char[] word2Arr = word2.toCharArray();
        Arrays.sort(word2Arr);
        String sorted1Word = new String(word1Arr);
        String sorted2Word = new String(word2Arr);
        return sorted1Word.equals(sorted2Word);
    }

    public ArrayList<ArrayList<String>> getSortedByAnQty() {
        ArrayList<ArrayList<String>> retList = new ArrayList<>();
        List<Boolean> isAlreadychecked = new ArrayList<>();
        for (int i = 0; i<this.wordsList.size();i++){
            isAlreadychecked.add(false);
        }

        for (int j =0; j<this.wordsList.size();j++){
            if(!isAlreadychecked.get(j)) {
                ArrayList<String> listToPush = new ArrayList<>();
                listToPush.add(wordsList.get(j));
                String wordToCheck = this.wordsList.get(j);
                isAlreadychecked.set(j,true);
                for (int i = 0; i<wordsList.size(); i++) {
                    String s = wordsList.get(i);
                    if (!isAlreadychecked.get(i) && areThoseTwoWordsAnagrams(wordToCheck, s)) {
                        listToPush.add(s);
                        isAlreadychecked.set(i, true);
                    }
                }
                retList.add(listToPush);
            }
        }
        retList.sort(new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                return o2.size() - o1.size();
            }
        });
        return retList;
    }

    public String getAnagramsFor(String word) {
        String retTxt = word + ": ";
        List<String> retWords = new ArrayList<>();
        for (String s : wordsList) {
            if(!word.equals(s) && areThoseTwoWordsAnagrams(word,s))
                retWords.add(s);
        }
        retTxt += retWords;
        return retTxt;
    }
}
