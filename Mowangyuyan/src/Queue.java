import java.util.ArrayList;
import java.util.List;

/**
 * Created by 江婷婷 on 2017/10/21.
 */
public class Queue {

    public static List<Character> queue = new ArrayList<>();

    public static boolean inQueue(char c) {
        return queue.add(c);
    }

    public static Character outQueue() {
        if (queue.isEmpty()) {
            return null;
        } else {
            Character ch = queue.get(0);
            queue.remove(0);
            return ch;
        }
    }

    public static Character getTail() {
        return queue.get(queue.size() - 1);
    }

}
