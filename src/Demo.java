import java.util.Iterator;

import util.Buffer;

public class Demo {
    public static void main(String[] args) {
        Buffer<Integer> buffer = new Buffer<>(5);
        buffer.addFront(5);
        buffer.addFront(2);
        buffer.addRear(4);
        buffer.addRear(4);
        buffer.addRear(3);
        buffer.removeFront();
        buffer.removeRear();
        buffer.removeFront();

        

        Iterator<Integer> iterador = buffer.iterator();
        while (iterador.hasNext()) {
            System.out.println(iterador.next());
        }
    }
}
