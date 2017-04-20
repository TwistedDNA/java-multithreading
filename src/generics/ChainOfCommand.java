package generics;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Lx on 4/18/2017.
 */
public class ChainOfCommand {

    private Object obj;
    private Function executeOnceMatch;

    public ChainOfCommand(Object obj) {
        this.obj = obj;
    }

    public static void main(String[] args) {
        /*int result = whenTypeOf(obj)
            .is(String.class).thenReturn(String::length)
            .is(Integer.class).thenReturn(d -> d)
        execute();*/
    }

    public static ChainOfCommand whenTypeOf(Object obj) {
        return new ChainOfCommand(obj);
    }

    public <T> Typed is(Class<T> clazz) {
        if (executeOnceMatch == null && obj.getClass().equals(clazz)) {
            return new Typed(clazz);
        }
    }

    class Typed<T> {

        private T type;

        public Typed(T type) {
            this.type = type;
        }

        public T thenReturn(Consumer consumer) {
            return null;
        }
    }
}
