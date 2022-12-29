package zad4;

import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Maybe<T> {

    T value;

    public Maybe(T value) {
        this.value=value;
    }

    public Maybe() {

    }

    public static <T>Maybe<T> of(T x) {
        if (x != null){
            return new Maybe<T>(x);
    }else{
            return new Maybe<>();
        }
    }

    public void ifPresent(Consumer<T> cons){
        if(value != null){
            cons.accept(value);
        }
    }

    public <R>Maybe<R> map(Function<T,R> func){
        if(value!=null) {
            R apply = func.apply(value);
            if (apply == null) {
                return new Maybe<>();
            }else{
                return new Maybe<>(apply);
            }
        }
        return new Maybe<>();
    }

    public T get() throws NoSuchElementException {
        if(value!=null){
            return value;
        }
        throw new NoSuchElementException(" maybe is empty");
    }

    public boolean isPresent(){
        return value != null;
    }

    public T orElse(T defVal){
        if(value==null){
            return defVal;
        }
        else {
            return value;
        }
    }

    public Maybe<T> filter(Predicate<T> pred){
        if(pred.test(value) || value == null){
            return new Maybe<T>(value);
        }
        return new Maybe<>();
    }

    @Override
    public String toString() {
        if(value != null){
            return "maybe has value " +value;
        }else{
            return "maybe is empty";
        }
    }
}
