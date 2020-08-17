import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class linkedList {
    List head;

    public static void main(String[] args) {
        List data1 = new List(1, new List(2, null));
        List data2 = new List(4, new List(5, null));

        data1.getLastList().next = data2;

        List p = data1;
        while( p != null) {
            System.out.println(p.data);
            p = p.next;
        }
    }
}
