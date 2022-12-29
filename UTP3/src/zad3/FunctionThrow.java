package zad3;


import java.io.IOException;
import java.util.function.Function;

public interface FunctionThrow<T,R,E extends IOException> extends Function<T,R> {

    @Override
    default R apply(T t){
        try {
            return throwFun(t);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    R throwFun(T t) throws E;
}
