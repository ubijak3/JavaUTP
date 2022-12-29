/**
 *
 *  @author Kubiak Marcin S26100
 *
 */

package zad1;


public interface Mapper<T,V> {
    V map(T arg);
    // Uwaga: interfejs musi być sparametrtyzowany
}  
