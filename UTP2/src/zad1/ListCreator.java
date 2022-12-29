package zad1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class ListCreator<T>{
    public List<T> list;

    private ListCreator(List<T> list) {
        this.list = list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list) {
        return new ListCreator<T>(list);
    }

    public  ListCreator<T> when(Predicate<T> predicate){
        List<T> helpList = new ArrayList<T>();
        for(T element: this.list){
            if(predicate.test(element)) {
                helpList.add(element);
            }
        }
        this.list = helpList;
        return new ListCreator<>(helpList);
    }
    public <R> List<R> mapEvery(Function<T, R> map) {
        List<R> retList = new ArrayList<R>();
        for (T element : this.list) {
            retList.add(map.apply(element));
        }
        return retList;
    }

}// Uwaga: klasa musi być sparametrtyzowana
