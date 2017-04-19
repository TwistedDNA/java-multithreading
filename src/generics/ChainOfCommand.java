package generics;

import java.util.function.Consumer;

/**
 * Created by Lx on 4/18/2017.
 */
public class ChainOfCommand {

    private Object obj;

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

    public <T> T is(Class<T> clazz){
        return new Typed(T);
    }

    class Typed<T> {
        private T type;

        public Typed(T type) {
            this.type = type;
        }

        public  thenReturn(Consumer consumer){

        }
    }
}
