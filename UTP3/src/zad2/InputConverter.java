package zad2;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;

public class InputConverter<T>{
    T arg;

    public InputConverter(T arg) {
        this.arg = arg;
    }
    public <L>L convertBy(Function... functions){
        Object call = arg;
        for (Function f:functions) {
            call = f.apply(call);
        }
        return (L)call;
    };





}
