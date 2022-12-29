/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad1;


import java.util.*;
public class ListCreator<T> {
    public List<T> list;

    private ListCreator(List<T> list) {
        this.list=list;
    }

    public static <T> ListCreator<T> collectFrom(List<T> list){
        return new ListCreator<>(list);
    }// Uwaga: klasa musi być sparametrtyzowana

    public ListCreator<T> when(Selector<T> sel){
        List<T> retList = new ArrayList<>();
        for (T element:this.list) {
            if(sel.select(element)){
                retList.add(element);
            }
        }
        this.list = retList;
        return this;
    }
    public <V> List<V> mapEvery(Mapper<T,V> map){
        List<V> retList = new ArrayList<>();
        for (T element : this.list) {
            retList.add(map.map(element));
        }
        return retList;
    }
}
